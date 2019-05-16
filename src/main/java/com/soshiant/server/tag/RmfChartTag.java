package com.soshiant.server.tag;
/*
 * Created by ghorbani on 2016/01/27.
 */

import com.soshiant.common.util.BundleUtil;
import com.soshiant.common.util.CommonUtil;
import com.soshiant.common.util.CustomerUtil;
import com.soshiant.common.util.ReportUtil;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.model.monitoring.MainFrame.RMF.RMFMonitoringData;
import com.soshiant.server.model.parameters.KeyValueObject;
import com.soshiant.server.model.user.UserPermissionsView;
import com.soshiant.server.thread.RMFMonitoringThread;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RmfChartTag extends TagSupport {

    private static int totalRowWidth = 1200;
    private static int chartPerRow = 3;

    private static String defaultChartWidth  = "600";
    private static String defaultChartHeight = "400";


    Logger log = Logger.getLogger(RmfChartTag.class);

    private String align;
    private String dir;
    private String chartWidth;
    private String chartHeight;
    private String serverId;
    private String chartTypeId;
    private String chartTypeName;
    private String chartTheme;


    private HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    @Override
    public int doStartTag() throws JspException {

        try {

            BundleUtil bundle = BundleUtil.getInstance();
            JspWriter jspWriter = pageContext.getOut();

            String tempServerId = (String)getHttpServletRequest().getAttribute(ServerConstants.SERVER_ID);

            if(!CommonUtil.isNullOrEmpty(tempServerId))
                this.serverId = tempServerId;
            if(CommonUtil.isNullOrEmpty(serverId)) {
                jspWriter.println(bundle.getMessage("error.common.chooseServer"));
                return SKIP_BODY;
            }
            List<UserPermissionsView> userPermissionsList = CustomerUtil.getUserPermissionFromSession(Integer.parseInt(this.serverId));

            if(userPermissionsList == null || userPermissionsList.size() == 0) {
                jspWriter.println(bundle.getMessage("error.common.haveNotServerPermission"));
                return SKIP_BODY;
            }

            String alignStr = "";
            if(align != null && (align.equalsIgnoreCase("left") || align.equalsIgnoreCase("right"))){
                alignStr = " align=\"" + align + "\"";
            }
            else {
                alignStr = " align=\"center\"";
            }
            String dirStr = "";
            if(dir != null && (dir.equalsIgnoreCase("ltr") || dir.equalsIgnoreCase("rtl"))){
                dirStr = " dir=\"" + dir + "\"";
            }

            int tdWidth = totalRowWidth / chartPerRow;
            String tdWidthStr = "";
            if(CommonUtil.isNullOrEmpty(chartWidth)){
                chartWidth = String.valueOf(tdWidth - 10);
            }
            tdWidthStr = " width=\"" + String.valueOf(tdWidth) +"\"";

            if(!CommonUtil.isNullOrEmpty(chartHeight)){
                chartHeight = defaultChartHeight;
            }
            String tagPart1 = "<table" + alignStr + dirStr + " cellpadding=\"1\">\n";


            String tagPart2 = "";
            int j = 0;
            int totalChartCount = userPermissionsList.size();
            for (int chartCount = 0; chartCount < totalChartCount; chartCount++) {
                UserPermissionsView permittedMetricInfo = userPermissionsList.get(chartCount);

                if(chartTypeId != null && permittedMetricInfo.getChartType().contains(chartTypeId)){
                    chartTypeName = CommonUtil.getChartTypeName(Integer.parseInt(CommonUtil.nvl(chartTypeId, "0")));
                }
                else {
                    String[] chartTypeList = permittedMetricInfo.getChartType().split(",");
                    if(chartTypeList.length > 0)
                        chartTypeId = chartTypeList[0];
                    chartTypeName = CommonUtil.getChartTypeName(Integer.parseInt(CommonUtil.nvl(chartTypeId,"0")));
                }
                String chartMinValue = permittedMetricInfo.getRmfMinValue();
                String chartMaxValue = permittedMetricInfo.getRmfMaxValue();

                if(!CommonUtil.isNullOrEmpty(chartMinValue)) {
                    chartMinValue = "                      \"yAxisMinValue\": \"" + chartMinValue + "\",\n";
                }

                if(!CommonUtil.isNullOrEmpty(chartMaxValue)) {
                    chartMaxValue = "                      \"yAxisMaxValue\": \"" + chartMaxValue + "\",\n";
                }

                String jsonData = getChartData(permittedMetricInfo);
                if (CommonUtil.isNullOrEmpty(jsonData)) {
                    continue;
                }
                String tdId = "\"chartRmfTD" + permittedMetricInfo.getMetricId() + "\"";
                String divId = "\"chartRmfDIV" + permittedMetricInfo.getMetricId() + "\"";

                if((chartCount % chartPerRow)== 0) {
                    tagPart2 += "    <tr>\n";
                }
                tagPart2 += "          <td style=\"border: dashed\" " + alignStr + dirStr + tdWidthStr + " >\n";
                if (CommonUtil.isNullOrEmpty(jsonData)) {
                    tagPart2 += bundle.getMessage("error.common.haveNotDataToMonitor") + "\n";
                }
                else {
                    tagPart2 += "              <div id=" + divId + ">\n" +
                                "              </div>\n";
                }
                tagPart2 += "          </td>\n";
                if (!CommonUtil.isNullOrEmpty(jsonData)) {
                    tagPart2 += "          <script type=\"text/javascript\" language=\"javascript\">\n" +
                                "              FusionCharts.ready(function(){ \n" +
                                "                  var rmfChartElm" + chartCount + " = new FusionCharts({\n" +
                                "                      \"id\":\"rmfChart" + chartCount + "\",\n" +
                                "                      \"type\": \"" + chartTypeName + "\",\n" +
                                "                      \"renderAt\": " + divId + ",\n" +
                                "                      \"width\": \"" + chartWidth + "\",\n" +
                                "                      \"height\": \"" + chartHeight + "\",\n" +
                                chartMinValue +
                                chartMaxValue +
                                "                      \"dataFormat\": \"json\",\n" +
                                "                      \"dataSource\":  \n" + jsonData +
                                "                  });\n" +
                                "                  rmfChartElm" + chartCount + ".render();\n" +
                                "              })\n" +
                                "          </script>\n";
                }
                j++;
                if(j == chartPerRow || chartCount >= totalChartCount) {
                    tagPart2 += "</tr>\n";
                    j = 0;
                }
                log.info("chart " + chartCount + "created successfully. ");

            }

            if(CommonUtil.isNullOrEmpty(tagPart2)){
                tagPart2 = "<td></td>\n";
            }
            String tagPart3 = "    </tr>\n" +
                              "</table>\n";

            jspWriter.println(tagPart1 + tagPart2 + tagPart3);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getChartWidth() {
        return chartWidth;
    }

    public void setChartWidth(String chartWidth) {
        this.chartWidth = chartWidth;
    }

    public String getChartHeight() {
        return chartHeight;
    }

    public void setChartHeight(String chartHeight) {
        this.chartHeight = chartHeight;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getChartTypeId() {
        return chartTypeId;
    }

    public void setChartTypeId(String chartTypeId) {
        this.chartTypeId = chartTypeId;
    }

    public String getChartTypeName() {
        return chartTypeName;
    }

    public void setChartTypeName(String chartTypeName) {
        this.chartTypeName = chartTypeName;
    }

    public String getChartTheme() {
        return chartTheme;
    }

    public void setChartTheme(String chartTheme) {
        this.chartTheme = chartTheme;
    }

    public String getChartData(UserPermissionsView permittedMetricInfo) {

        try {
            String jsonData = "";
            LinkedList tempDataList = (LinkedList) RMFMonitoringThread.rmfMonitoringDataMap.get(permittedMetricInfo.getServerRmfServiceId());
            if (tempDataList == null || tempDataList.size() == 0) {
                return "";
            }
            LinkedList dataList = (LinkedList) tempDataList.clone();
            int DataListLength = 0;
            if (dataList.size() > 10) {
                DataListLength = dataList.size() - 10;
            }
            List<KeyValueObject> tempList = new ArrayList<>();
            for (int dataSize = DataListLength; dataSize < dataList.size(); dataSize++) {
                ArrayList<RMFMonitoringData> rmfMonitoringDataList = (ArrayList<RMFMonitoringData>) dataList.get(dataSize);

                if (rmfMonitoringDataList != null && rmfMonitoringDataList.size() > 0) {

                    for (RMFMonitoringData monitoringData : rmfMonitoringDataList) {
                        String chartLabel = monitoringData.getRmfColumnValueForChartLabel();
                        if(chartLabel.length() > 20){
                            int dotLastIndex = chartLabel.lastIndexOf(".");
                            if(dotLastIndex > 0) {
                                dotLastIndex = chartLabel.lastIndexOf(".", dotLastIndex - 1);
                            }
                            if(dotLastIndex > 0) {
                                dotLastIndex = chartLabel.lastIndexOf(".", dotLastIndex - 1);
                            }
                            if(dotLastIndex >= 0) {
                                chartLabel = chartLabel.substring(dotLastIndex + 1, chartLabel.length());
                            }
                        }
                        tempList.add(new KeyValueObject(chartLabel, monitoringData.getRowValue()));
                    }
                    if (rmfMonitoringDataList.size() > 1){
                        break;
                    }
                }
            }
            if (tempList.size() == 0) {
                return "";
            }
            jsonData = ReportUtil.generateChartJsonDataFromList(permittedMetricInfo, tempList,ServerConstants.CHART_ORIENTATION_HORIZONTAL);
            return jsonData;
        }
        catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
