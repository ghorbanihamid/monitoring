package com.soshiant.common.util;

import com.soshiant.common.dateTime.DateTimeUtil;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Vector;

public class FileUtils {

    static Logger log = Logger.getLogger(FileUtils.class.getName());

    public static final String FILE_EXTENSION_ZIP = ".zip";

    public static final String FILE_EXTENSION_PDF = ".pdf";

    public static final String FILE_EXTENSION_HTML = ".html";

    public static final String FILE_EXTENSION_CSV = ".csv";

    public static final String FILE_EXTENSION_EXCEL = ".xlsx";

    public final static String JASPER_REPORT_REAL_PATH = "/jasperreport";

    public final static String EXPORT_REAL_PATH = "/export";

    private static String jasperReportRealPath = "";

    private static String exportRealPath = "";

    private static String fontRealPath = "";

//======================================================================================================================

    public static String getJasperReportRealPath() {
        return jasperReportRealPath;
    }

    public static void setJasperReportRealPath(String jasperReportRealPath) {
        FileUtils.jasperReportRealPath = jasperReportRealPath;
    }

//======================================================================================================================
    public static String getExportRealPath() {
        return exportRealPath;
    }

    public static void setExportRealPath(String exportRealPath) {
        FileUtils.exportRealPath = exportRealPath;
    }
//======================================================================================================================

    public static String getFontRealPath() {
        return fontRealPath;
    }

    public static void setFontRealPath(String fontRealPath) {
        FileUtils.fontRealPath = fontRealPath;
    }

//======================================================================================================================



    public static String getPathSeparator() {
        String sys = System.getProperty("os.name");
        if (sys.startsWith("Windows"))
            return "\\";

        return "/";
    }

    public static boolean isExistsDirectory(String directory) throws Exception {
        File file = new File(directory);
        return file.exists();
    }

    public static boolean fileExists(String fileName) throws Exception {

        File file = new File(fileName);
        return file.exists();
    }

    public synchronized static boolean makeDirectory(String directory) throws Exception {
        File file = new File(directory);
        return !file.exists() && file.mkdir();

    }

    public static void cleanUpDirectory(String directory, String filter) {
        File file;
        File dir = new File(directory);
        ExtensionFilter extensionFilter = new ExtensionFilter(filter);
        String[] list = dir.list(extensionFilter);
        if (list.length == 0) return;
        for (int i = 0; i < list.length; i++) {
            file = new File(directory + list[i]);
            file.delete();
        }
    }

    public static void cleanUpFiles(String directory, Vector files) {
        if (files == null || files.size() == 0) return;
        File file;

        try {

            for (int i = 0; i < files.size(); i++) {
                file = new File(directory + files.elementAt(i).toString());
                log.debug("file(" + i + "):" + (directory + file.getName()));
                file.deleteOnExit();
            }

        } catch (Exception e) {
            log.error("cleanUpFiles Exception:" + e.getMessage());
        }
    }

    static class ExtensionFilter implements FilenameFilter {
        private String extension;

        public ExtensionFilter(String extension) {
            this.extension = extension;
        }

        public boolean accept(File dir, String name) {
            return (name.endsWith(extension));
        }
    }

    public static String getFileExtension(String fileName) {
        int dotPosition = fileName.indexOf(".");
        String fileExt = "";
        if(dotPosition > 0)
            fileExt = fileName.substring(dotPosition);
        return fileExt;
    }

    public static void writeToFile(String fileContent, String path, String fileName, String fileExtention) throws IOException {
        fileName = fileName + fileExtention;
        FileWriter fp = new FileWriter(path + fileName, false);
        System.out.println("\n========>>> file opened . file name is : " + fileName);
        fp.write(fileContent);
        fp.close();
    }

    public static void appendRowToFile(String row, String path, String fileName, String fileExtention) throws IOException {
        fileName = fileName + fileExtention;
        FileWriter fp = new FileWriter(path + fileName, true);
        System.out.println("\n========>>> file opened . file name is : " + fileName);
        fp.write(row);
        fp.close();
    }

    public static String getRow(int rowNumber, String path, String fileFullName) throws IOException {

        String currentLine = null;
        try {

            File file = new File(path + fileFullName);

            if (file.exists()) {
                FileReader fileReader = new FileReader(file);
                BufferedReader lineNumberReader = new BufferedReader(fileReader);
//                LineNumberReader lineNumberReader = new LineNumberReader(fileReader);

                int currentLineNumber = 1;
                currentLine = lineNumberReader.readLine();

                while ((currentLine != null) && (currentLineNumber < rowNumber)) {
                    currentLine = lineNumberReader.readLine();
                    currentLineNumber++;
                }

                lineNumberReader.close();

            } else {
                System.out.println("File does not exists!");
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        return currentLine;

    }

    public static int getFileTotalRowCount(String path, String fileFullName) throws IOException {

        int count = 0;
        try {

            File file = new File(path + fileFullName);

            if (file.exists()) {

                FileReader fr = new FileReader(file);

                LineNumberReader ln = new LineNumberReader(fr);

                while (ln.readLine() != null) {

                    count++;

                }

                System.out.println("Total line no: " + count);

                ln.close();

            } else {

                System.out.println("File does not exists!");

            }

        }

        catch (IOException e) {

            e.printStackTrace();

        }
        return count;
    }

    private static void moveFile(File source, File destination) throws IOException {
        String startTime = DateTimeUtil.getCurrentTime();
        try {
            FileChannel sourceChannel = new FileInputStream(source).getChannel();
            FileChannel destinationChannel = new FileOutputStream(destination).getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);
            sourceChannel.close();
            destinationChannel.close();
            source.delete();
        } catch (Exception e) {
            System.out.println("Exception Occured while moving file : " + source.getName() +
                    "\n Exception : " + e.getStackTrace());
        }
//        System.out.println("*** archive file at " + (((DateTimeUtil.getCurrentTime() - startTime) / 1000) + " second . File Name : " + source.getName() + " Finished.");
    }

    public static void main(String[] args) {
        try {
            FileUtils.getRow(3, "C:\\payment\\", "ELFL13880119.PAY");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


}
