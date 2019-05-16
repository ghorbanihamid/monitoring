package com.soshiant.server.action.file;

import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soshiant.common.util.FileUtils;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

public class FileDownloadAction extends ActionSupport implements ServletRequestAware {

    Logger log = Logger.getLogger(FileDownloadAction.class);

    public static final String CONTENT_TYPE_ZIP = "application/zip";

    public static final String CONTENT_TYPE_PDF = "application/pdf";

    public static final String CONTENT_TYPE_HTML = "application/html";

    public static final String CONTENT_TYPE_CSV = "application/csv";

    public static final String CONTENT_TYPE_EXCEL = "application/excel";


    private static final String FILE_LOCATION_FOLDER = "export";

    private HttpServletRequest servletRequest;

    private InputStream fileInputStream;

    private String contentType = null;

    private String fileName = null;


    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String execute() throws Exception {

//        response.setHeader("Cache-Control", "no-cache");
//        response.setHeader("Content-Disposition","attachment; filename=\"" + example.pdf+ "\"");
//        response.setHeader("Expires", "0");
//        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
//        response.setHeader("Pragma", "public");
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        response.setContentLength(baos.size());
//        ByteArrayInputStream bis=new ByteArrayInputStream(baos.toByteArray());
//        fileInputStream = bis;
        try {
            if(FileUtils.getFileExtension(this.fileName).length() == 0 ) {
                switch (contentType){
                    case CONTENT_TYPE_ZIP : fileName += FileUtils.FILE_EXTENSION_ZIP;
                        break;
                    case CONTENT_TYPE_PDF : fileName += FileUtils.FILE_EXTENSION_PDF;
                        break;
                    case CONTENT_TYPE_CSV : fileName += FileUtils.FILE_EXTENSION_CSV;
                        break;
                    case CONTENT_TYPE_EXCEL : fileName += FileUtils.FILE_EXTENSION_EXCEL;
                        break;
                    case CONTENT_TYPE_HTML : fileName += FileUtils.FILE_EXTENSION_HTML;
                        break;
                }
            }
            String filePath = getRealPath(FILE_LOCATION_FOLDER);
            String completeFileName = filePath + this.fileName;
            if (!FileUtils.fileExists(completeFileName)){
                log.error("File not found, fileName : " + completeFileName);
//                if (ServerConstants.isAppLogEnabled)
//                    appLogFacade.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.DAILY_CLASS_SEARCH_ACTION_PROCESS_CODE, "", "DailyClass", this.actionName, ServerConstants.NoSqlText, this.logMessage, e.getMessage(), "");
                addActionError(getText("error.file.fileNotExists"));
                return ERROR;
            }
            fileInputStream = new FileInputStream(new File(completeFileName));
            return SUCCESS;
        }
        catch (FileNotFoundException e) {
            log.error("File not found, fileName : " + this.fileName);
            addActionError(getText("error.file.fileNotExists"));
            return ERROR;
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("Exception while downloading file :  " + e.getMessage());
            addActionError(getText("error.common.systemCouldNotRespond2"));
            return ERROR;
        }
    }

//    /**
//     * Returns the information on the file, or other stream, to be downloaded
//     * by this action.
//     *
//     * @param mapping  The ActionMapping used to select this instance.
//     * @param form     The optional ActionForm bean for this request (if any).
//     * @param request  The HTTP request we are processing.
//     * @param response The HTTP response we are creating.
//     * @return The information for the file to be downloaded.
//     * @throws Exception if an exception occurs.
//     */
//    protected StreamInfo getStreamInfo(ActionMapping mapping,
//                                       ActionForm form, HttpServletRequest request,
//                                       HttpServletResponse response)
//
//
//            throws Exception {
//
//        FileDownloadForm fileFrom = (FileDownloadForm) form;
//        String fileName = fileFrom.getFilePath();
//        System.out.println("Download File:" + fileName);
//        if (StringFormatter.isContainCharacter(fileName, ServerConstants.INVALID_INPUTS) || fileName.indexOf("..") != -1) {
//            log.fatal("Invalid File Name Entered that is :'" + fileName + "'");
//            fileName = "UnAuthorizedAccess.txt";
//        }
//
//
//        //FileDownloadActionMapping m = (FileDownloadActionMapping)mapping;
//        String filePath = getRealPath("/export") + fileName;
//        String contentType = fileFrom.getContentType();
//        if (request.getParameter("filePath") != null) {
//            filePath = getRealPath("/export") + fileName;
//        }
//        if (request.getParameter("contentType") != null) {
//            contentType = (String) request.getParameter("contentType");
//        }
//        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
//        return new SI(contentType, new File(filePath));
//    }
//
//    /**
//     * This class represents the pertinent details about the file to be
//     * downloaded.
//     */
//    public static class SI implements StreamInfo {
//
//        private String contentType;
//        private File file;
//
//        public SI(String contentType, File file) {
//            this.contentType = contentType;
//            this.file = file;
//        }
//
//        public String getContentType() {
//            return this.contentType;
//        }
//
//        /**
//         * Method to get a stream on the file to download
//         *
//         * @return The InputStream wrapping the file to download
//         */
//        public InputStream getInputStream() throws IOException {
//            FileInputStream fis = new FileInputStream(file);
//            BufferedInputStream bis = new BufferedInputStream(fis);
//            return bis;
//        }
//
//    }
//
    public String getRealPath(String relativePath) {
        return servletRequest.getSession().getServletContext().getRealPath(relativePath)+ FileUtils.getPathSeparator();
    }
}
