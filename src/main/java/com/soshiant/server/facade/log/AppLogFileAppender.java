package com.soshiant.server.facade.log;

import org.apache.log4j.Layout;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.QuietWriter;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * CustomFileAppender appends log events to a file.This class add some new
 * Features to FileAppender .it automatically enumerate number of lines (Number
 * of all log Events )
 * <p/>
 * <p/>
 * Support for <code>java.io.Writer</code> and console appending has been
 * deprecated and then removed. See the replacement solutions:
 * {@link org.apache.log4j.WriterAppender} and {@link org.apache.log4j.ConsoleAppender}.
 *
 * @author Ceki G&uuml;lc&uuml;
 */
public class AppLogFileAppender extends WriterAppender {

    /**
     * Controls file truncatation. The default value for this variable is
     * <code>true</code>, meaning that by default a <code>FileAppender</code>
     * will append to an existing file and not truncate it.
     * <p/>
     * <p/>
     * This option is meaningful only if the FileAppender opens the file.
     */
    protected boolean fileAppend = true;
    /**
     * The name of the log file.
     */
    protected static String fileName = null;

    /**
     * Do we do bufferedIO?
     */
    protected boolean bufferedIO = false;
    protected int fileCount;

    /**
     * Determines the size of IO buffer be. Default is 8K.
     */
    protected int bufferSize = 8 * 1024;
    protected static int currentFileSize;
    protected int maxFileSize;
    protected String filePath;
    protected String fileNamePrefix;
    protected String fileExtension;

    public AppLogFileAppender() {
    }

    /**
     * Instantiate a <code>FileAppender</code> and open the file designated by
     * <code>filename</code>. The opened filename will become the output
     * destination for this appender.
     * <p/>
     * <p/>
     * If the <code>append</code> parameter is true, the file will be appended
     * to. Otherwise, the file designated by <code>filename</code> will be
     * truncated before being opened.
     * <p/>
     * <p/>
     * If the <code>bufferedIO</code> parameter is <code>true</code>, then
     * buffered IO will be used to write to the output file.
     */
    public AppLogFileAppender(Layout layout, String filename, boolean append,
                              boolean bufferedIO, int bufferSize) throws IOException {
        this.layout = layout;
        this.setFile(filename, append, bufferedIO, bufferSize);
    }

    /**
     * Instantiate a FileAppender and open the file designated by
     * <code>filename</code>. The opened filename will become the output
     * destination for this appender.
     * <p/>
     * <p/>
     * If the <code>append</code> parameter is true, the file will be appended
     * to. Otherwise, the file designated by <code>filename</code> will be
     * truncated before being opened.
     */
    public AppLogFileAppender(Layout layout, String filename, boolean append)
            throws IOException {
        this.layout = layout;
        this.setFile(filename, append, false, bufferSize);
    }

    /**
     * Instantiate a FileAppender and open the file designated by
     * <code>filename</code>. The opened filename will become the output
     * destination for this appender.
     * <p/>
     * <p/>
     * The file will be appended to.
     */
    public AppLogFileAppender(Layout layout, String filename)
            throws IOException {
        this(layout, filename, true);
    }

    /**
     * The <b>File</b> property takes a string value which should be the name
     * of the file to append to.
     * <p/>
     * <p/>
     * <font color="#DD0044"><b>Note that the special values "System.out" or
     * "System.err" are no longer honored.</b></font>
     * <p/>
     * <p/>
     * Note: Actual opening of the file is made when {@link #activateOptions} is
     * called, not when the options are set.
     */
    public void setFile(String file) {
        String val = file.trim();
        fileName = val;
    }

    /**
     * Returns the value of the <b>Append</b> option.
     */
    public boolean getAppend() {
        return fileAppend;
    }

    public String getFileName() {
        return fileName;
    }

    /**
     * If the value of <b>File</b> is not <code>null</code>, then {@link
     * #setFile} is called with the values of <b>File</b> and <b>Append</b>
     * properties.
     *
     * @since 0.8.1
     */
    public void activateOptions() {

        if (fileName == null)
            fileName = getNewFileName();

        try {
            setFile(fileName, fileAppend, bufferedIO, bufferSize);
        } catch (IOException e) {
            errorHandler.error("setFile(" + fileName + "," + fileAppend
                    + ") call failed.", e, ErrorCode.FILE_OPEN_FAILURE);
        }
    }

    public void append(LoggingEvent event) {

        if (!checkEntryConditions()) {
            return;
        }
        subAppend(event);
    }

    /**
     * Actual writing occurs here.
     * <p/>
     * <p/>
     * Most subclasses of <code>WriterAppender</code> will need to override
     * this method.
     *
     * @since 0.9.0
     */
    protected synchronized void subAppend(LoggingEvent event) {
        this.qw.write(this.layout.format(event));
        if (layout.ignoresThrowable()) {
            String[] s = event.getThrowableStrRep();
            if (s != null) {
                int len = s.length;
                for (int i = 0; i < len; i++) {
                    this.qw.write(s[i]);
                    this.qw.write(Layout.LINE_SEP);
                }
            }
        }

        if (this.immediateFlush) {
            this.qw.flush();
        }
        //setCurrentFileSize(getNumberOfLines(fileName));
        currentFileSize++;
        if (currentFileSize >= maxFileSize) {
            ManageBackupAndNewFile();
            currentFileSize = 0;
        }
    }

    private void ManageBackupAndNewFile() {

        closeFile();

        // this method rename generated file with .log extension
        renameFile();

        fileName = getNewFileName();

        try {
            setFile(fileName, fileAppend, bufferedIO, bufferSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean renameFile() {
        File file = new File(fileName);
        String newName = fileName.substring(0, fileName.length() - 4);
        newName += ".log";
        File file2 = new File(newName);
        return file.renameTo(file2);

    }

    /**
     * Closes the previously opened file.
     */
    protected void closeFile() {
        if (this.qw != null) {
            try {
                this.qw.close();
            } catch (IOException e) {
                // Exceptionally, it does not make sense to delegate to an
                // ErrorHandler. Since a closed appender is basically dead.
                LogLog.error("Could not close " + qw, e);
            }
        }
    }

    /**
     * Get the value of the <b>BufferedIO</b> option.
     * <p/>
     * <p/>
     * BufferedIO will significatnly increase performance on heavily loaded
     * systems.
     */
    public boolean getBufferedIO() {
        return this.bufferedIO;
    }

    /**
     * Get the size of the IO buffer.
     */
    public int getBufferSize() {
        return this.bufferSize;
    }

    /**
     * The <b>Append</b> option takes a boolean value. It is set to
     * <code>true</code> by default. If true, then <code>File</code> will be
     * opened in append mode by {@link #setFile setFile} (see above). Otherwise,
     * {@link #setFile setFile} will open <code>File</code> in truncate mode.
     * <p/>
     * <p/>
     * Note: Actual opening of the file is made when {@link #activateOptions} is
     * called, not when the options are set.
     */
    public void setAppend(boolean flag) {
        fileAppend = flag;
    }

    /**
     * The <b>BufferedIO</b> option takes a boolean value. It is set to
     * <code>false</code> by default. If true, then <code>File</code> will
     * be opened and the resulting {@link java.io.Writer} wrapped around a
     * {@link java.io.BufferedWriter}.
     * <p/>
     * BufferedIO will significatnly increase performance on heavily loaded
     * systems.
     */
    public void setBufferedIO(boolean bufferedIO) {
        this.bufferedIO = bufferedIO;
        if (bufferedIO) {
            immediateFlush = false;
        }
    }

    /**
     * Set the size of the IO buffer.
     */
    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    /**
     * <p/>
     * Sets and <i>opens</i> the file where the log output will go. The
     * specified file must be writable.
     * <p/>
     * <p/>
     * If there was already an opened file, then the previous file is closed
     * first.
     * <p/>
     * <p/>
     * <b>Do not use this method directly. To configure a FileAppender or one of
     * its subclasses, set its properties one by one and then call
     * activateOptions.</b>
     *
     * @param fileName The path to the log file.
     * @param append   If true will append to fileName. Otherwise will truncate
     *                 fileName.
     */
    public synchronized void setFile(String fileName, boolean append,
                                     boolean bufferedIO, int bufferSize) throws IOException {
        LogLog.debug("setFile called: " + fileName + ", " + append);

        // It does not make sense to have immediate flush and bufferedIO.
        if (bufferedIO) {
            setImmediateFlush(false);
        }

        reset();
        FileOutputStream ostream = null;
        try {
            // attempt to create file
            ostream = new FileOutputStream(fileName, append);
        } catch (FileNotFoundException ex) {

            // if parent directory does not exist then attempt to create it and try to create file
            String parentName = new File(fileName).getParent();
            if (parentName != null) {
                File parentDir = new File(parentName);
                if (!parentDir.exists() && parentDir.mkdirs()) {
                    ostream = new FileOutputStream(fileName, append);
                } else {
                    throw ex;
                }
            } else {
                throw ex;
            }
        }
        Writer fw = createWriter(ostream);
        if (bufferedIO) {
            fw = new BufferedWriter(fw, bufferSize);
        }
        this.setQWForFiles(fw);
        this.fileName = fileName;
        this.fileAppend = append;
        this.bufferedIO = bufferedIO;
        this.bufferSize = bufferSize;
        writeHeader();
        LogLog.debug("setFile ended");
    }

    /**
     * Sets the quiet writer being used.
     * This method is overriden by {@link org.apache.log4j.RollingFileAppender}.
     */
    protected void setQWForFiles(Writer writer) {
        this.qw = new QuietWriter(writer, errorHandler);
    }

    /**
     * Sets the quiet writer being used.
     * This method is overriden by {@link org.apache.log4j.RollingFileAppender}.
     */
    protected String getNewFileName() {

        Calendar calander = new GregorianCalendar();

        int date = calander.get(Calendar.YEAR) * 10000
                + (calander.get(Calendar.MONTH) + 1) * 100
                + calander.get(Calendar.DAY_OF_MONTH);

        int time = calander.get(Calendar.HOUR) * 10000
                + (calander.get(Calendar.MINUTE) + 1) * 100
                + calander.get(Calendar.SECOND);

        String ip = "0";
        try {
            String addr = InetAddress.getLocalHost().getHostAddress();
            ip = addr.substring(addr.lastIndexOf(".") + 1);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return getFilePath() + getFileNamePrefix() + "_" + ip + "_" + date + "_" + time + getFileExtension();
    }

    /**
     * Close any previously opened file and call the parent's <code>reset</code>.
     */
    protected void reset() {
        closeFile();
//		this.fileName = null;
        super.reset();
    }

    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public int getCurrentFileSize() {
        return currentFileSize;
    }

    public void setCurrentFileSize(int currentFileSize) {
        this.currentFileSize = currentFileSize;
    }

    public int getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(int maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileNamePrefix() {
        return fileNamePrefix;
    }

    public void setFileNamePrefix(String fileNamePrefix) {
        this.fileNamePrefix = fileNamePrefix;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
}
