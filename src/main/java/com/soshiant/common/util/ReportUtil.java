package com.soshiant.common.util;

import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.model.monitoring.MainFrame.RMF.ConfiguredMetricsView;
import com.soshiant.server.model.parameters.KeyValueObject;
import com.soshiant.server.model.user.UserPermissionsView;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRProperties;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

//        http://www.javatips.net/blog/2011/09/call-jasper-report-ireport-from-java-application
//        http://jasperreports.sourceforge.net/sample.reference/printservice/index.html
//        http://jasperreports.sourceforge.net/sample.reference.html
//        http://jasperreports.sourceforge.net/sample.reference/alterdesign/index.html#alterdesign

public abstract class ReportUtil {

    private static Logger reportUtilLog = Logger.getLogger(ReportUtil.class);
    private static String LIGHT_BLUE_COLOR  = "AFD8F8";
    private static String GREEN_COLOR       = "8BBA00";
    private static String RED_COLOR         = "FF0000";
    private static String ORANGE_COLOR      = "FFA500";
    private static String PINK_COLOR        = "F984A1";

    //    JRXML file is a JasperReports Document. JRXML is the XML file format of JasperReport,
    //    which can be coded manually, generated, or created using a tools like IReport, JasperAssistant etc
    //    Execution of report from JRXML file will be very slow, as it need to compile before the execution

    public static void generatePdfFromJRXmlFile (String jrXmlFileName,String pdfFileName,HashMap reportParameters, List reportVector) throws JRException {
        try {
             /* JasperReport is the object that holds our compiled JRXML file */
            JasperReport jasperReport = JasperCompileManager.compileReport(jrXmlFileName);
            Object[] reportVectorArray = reportVector.toArray();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,reportParameters, new JRBeanArrayDataSource(reportVectorArray));
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFileName);
        } catch (JRException e){
            reportUtilLog.error("ReportUtil.generatePdfFromJasperFile() jrXmlFileName:"+ jrXmlFileName + ", exception :" + e.getMessage());
        }
    }
    //======================================================================================================================
    //    Jasper file is a compiled format of JasperReports Document.
    //    Execution of report from Jasper file will be very fast, as it is pre-compiled
    //    It is recommended for the production environment

    public static void generatePdfFromJasperFile(String jasperFileName,String pdfFileName,HashMap reportParameters, List reportVector) throws JRException {

        try {
            jasperFileName = FileUtils.getExportRealPath() + jasperFileName;
            pdfFileName = FileUtils.getExportRealPath() + pdfFileName + FileUtils.FILE_EXTENSION_PDF;
            String fontFileName = FileUtils.getFontRealPath() + "tahoma.ttf";
            JRProperties.setProperty(JRProperties.PDF_FONT_FILES_PREFIX + "Tahoma", fontFileName);

            Object[] reportVectorArray = reportVector.toArray();
            /* JasperPrint : the object contains report after result filling process */
            // fillReport : filling report with data from data source
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFileName,
                                                                   reportParameters,
                                                                   new JRBeanArrayDataSource(reportVectorArray));

            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFileName);
        } catch (JRException jrEx){
            reportUtilLog.error("ReportUtil.generatePdfFromJasperFile() jasperFileName:" + jrEx.getMessage());
        }
    }
    //======================================================================================================================
    public static void generateHtmlFromJasperFile(String jasperFileName,String htmlFileName,HashMap reportParameters, List reportVector) throws JRException {

        try {
            jasperFileName = FileUtils.getExportRealPath() + jasperFileName;
            /* JasperPrint : the object contains report after result filling process */
            // fillReport : filling report with data from data source

            //JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(maps);
            Object[] reportVectorArray = reportVector.toArray();
            JasperPrint jasperPrint = JasperFillManager.fillReport( jasperFileName,
                                                                    reportParameters,
                                                                    new JRBeanArrayDataSource(reportVectorArray));

            JasperExportManager.exportReportToHtmlFile(jasperPrint, htmlFileName);
        } catch (JRException jrEx){
            reportUtilLog.error("ReportUtil.generateHtmlFromJasperFile() jasperFileName:" + jrEx.getMessage());
        }
    }
    //======================================================================================================================
    public static void generateExcelFromJasperFile(String jasperFileName,String excelFileName,HashMap reportParameters, List reportVector) throws JRException {

        try {
            jasperFileName = FileUtils.getExportRealPath() + jasperFileName;
            Object[] reportVectorArray = reportVector.toArray();
            /* JasperPrint : the object contains report after result filling process */
            // fillReport : filling report with data from data source
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFileName,
                    reportParameters,
                    new JRBeanArrayDataSource(reportVectorArray));
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, excelFileName);
            exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
            exporter.exportReport();
        } catch (JRException jrEx){
            reportUtilLog.error("ReportUtil.generateHtmlFromJasperFile() jasperFileName:" + jrEx.getMessage());
        }
    }
    //======================================================================================================================
    public static String generateChartJsonDataFromList(ConfiguredMetricsView metricInfo, List<KeyValueObject> dataList) throws Exception {

        try {
            String jsonData = "{\"chart\": {\n" +
                    "         \"caption\"      : \"" + metricInfo.getMetricDesc() + "\" ,\n" +
                    "         \"xAxisName\"    : \"" + metricInfo.getChartLabelColumn() + "\" ,\n" +
                    "         \"yAxisName\"    : \"" + metricInfo.getChartValueColumn() + "\" ,\n" +
                    "         \"numberPrefix\" : \"" + metricInfo.getValuePrefix() + "\" \n" +
                    "          },\n" +
                    "\"data\" :\n"  +
                    "[\n" ;

            for (KeyValueObject tempObject : dataList) {
                jsonData += "{ \"label\" : \" " + String.valueOf(tempObject.getKey()) + "\"," +
                            "  \"value\" : \" " +  tempObject.getValue() + "\" },";
            }

            jsonData = jsonData.substring(0, jsonData.length() -1) + "]}";

            return jsonData;
        } catch (Exception ex){
            reportUtilLog.error("ReportUtil.generateChartJsonDataFromList() Message :" + ex.getMessage());
            return "";
        }
    }
    //======================================================================================================================
    public static String generateChartJsonDataFromList(UserPermissionsView metricInfo, List<KeyValueObject> dataList,int chartOrientation) throws Exception {

        try {
            String jsonData =   " {\"chart\": {\n" +
                                "         \"caption\"      : \"" + metricInfo.getMetricDesc()       + "\" ,\n" +
                                "         \"subCaption\"   : \"" + metricInfo.getMetricId()         + "\" ,\n";
                    if(chartOrientation == ServerConstants.CHART_ORIENTATION_HORIZONTAL) {
                        jsonData += "         \"xAxisName\"    : \"" + CommonUtil.nvl(metricInfo.getChartLabelColumn(), "") + "\" ,\n" +
                                    "         \"yAxisName\"    : \"" + CommonUtil.nvl(metricInfo.getChartValueColumn(), "") + "\" ,\n";
                    }
                    else {
                        jsonData += "         \"xAxisName\"    : \"" + CommonUtil.nvl(metricInfo.getChartValueColumn(), "") + "\" ,\n" +
                                    "         \"yAxisName\"    : \"" + CommonUtil.nvl(metricInfo.getChartLabelColumn(), "") + "\" ,\n";

                    }
            jsonData += "         \"numberPrefix\" : \"" + metricInfo.getValuePrefix() + "\"  \n" +
                        "          },\n" +
                        "\"data\" :\n" +
                        "[\n";

            for (KeyValueObject tempObject : dataList) {
                long dataValue = Long.parseLong(CommonUtil.nvl(tempObject.getValue(), "0"));
                String columnColor = GREEN_COLOR;
                if(metricInfo.getCriticalMoreValue() > 0 && dataValue >= metricInfo.getCriticalMoreValue())
                    columnColor = RED_COLOR;
                else if(metricInfo.getUnusualMoreValue() > 0 && dataValue >= metricInfo.getUnusualMoreValue())
                    columnColor = ORANGE_COLOR;
                else if(metricInfo.getCriticalLessValue() > 0 && dataValue <= metricInfo.getCriticalLessValue())
                    columnColor = RED_COLOR;
                else if(metricInfo.getUnusualLessValue() > 0 && dataValue <= metricInfo.getUnusualLessValue())
                    columnColor = ORANGE_COLOR;


                if(chartOrientation == ServerConstants.CHART_ORIENTATION_HORIZONTAL) {
                    jsonData += "{ \"label\" : \" " + String.valueOf(tempObject.getKey()) + "\"," +
                                "  \"value\" : \" " + tempObject.getValue() + "\"," +
                                "  \"color\" : \" " + columnColor + "\" },";
                }
                else {
                    jsonData += "{ \"label\" : \" " + String.valueOf(tempObject.getValue()) + "\"," +
                                "  \"value\" : \" " + tempObject.getKey() + "\" },";
                }
            }

            jsonData = jsonData.substring(0, jsonData.length() -1) + "]}";

            return jsonData;
        } catch (Exception ex){
            reportUtilLog.error("ReportUtil.generateChartJsonDataFromList() Message :" + ex.getMessage());
            return "";
        }
    }
    //======================================================================================================================

}
