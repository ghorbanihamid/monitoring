package com.soshiant.server.stub;

import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.model.monitoring.MainFrame.RMF.ConfiguredMetricsView;
import com.soshiant.server.model.monitoring.MainFrame.RMF.RMFMonitoringData;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

public class MonitoringStub {

    Logger log = Logger.getLogger(MonitoringStub.class);

    public String getRMFMonitoringXMLFromServer (ConfiguredMetricsView configuredMetricsView) throws Exception {

        RMFMonitoringData rmfMonitoringData = new RMFMonitoringData();
        try {
            String rmfUrl = "http://" + ServerConstants.RMF_MONITORING_SERVER_IP_PORT + "/gpm/perform/perform.xml?resource=\"Z108,*,PROCESSOR\"&id=8D0460";

            String  rmfXmlData; //= sendHttpRequest(configuredMetricsView.getRmfUserName(),configuredMetricsView.getRmfPassword(),rmfUrl);

            if(configuredMetricsView.getMetricId().equals("8D0460"))
                rmfXmlData = "<perf-config><server name=\"RMF-DDS-Server\" version=\"ZOSV1R8\" functionality=\"2354\"/><performance-data><buttons>YES</buttons><metric id=\"8D0460\" description=\"% total utilization\" type=\"single\" listtype=\" \" workscope=\",,G\" filter=\"HI=20\" ncols=\"2\" resource=\"Z108,*,PROCESSOR\" restype=\"PROCESSOR\" reslabelurl=\"Z108,*,PROCESSOR\"><timerange localstart=\"20151128182820\" localend=\"20151128182840\" utcstart=\"20151128182820\" utcend=\"20151128182840\"/><scroll prev=\"20151128182810\" next=\"20151128182850\"/><timestamp localstart=\"11/28/2015 18:28:20\" localtime=\"11/28/2015 18:28:40\" timestring=\"20151128182840\"/><gathererinterval seconds=\"20\"/><data-range seconds=\"20\"/><row label=\"\" value=\"32\" per=\"32\" ex=\"NONE\"/></metric></performance-data></perf-config>";
            else
                rmfXmlData = "<perf-config><server name=\"RMF-DDS-Server\" version=\"ZOSV1R8\" functionality=\"2354\"/><performance-data><buttons>YES</buttons><metric id=\"8D2090\" description=\"% using by dataset name\" type=\"multi\" listtype=\"D\" workscope=\",,G\" filter=\"HI=20\" ncols=\"2\" resource=\"Z108,*,I/O_SUBSYSTEM\" restype=\"I/O_SUBSYSTEM\" reslabelurl=\"Z108,*,I/O_SUBSYSTEM\"><timerange localstart=\"20151129095820\" localend=\"20151129095840\" utcstart=\"20151129095820\" utcend=\"20151129095840\"/><scroll prev=\"20151129095810\" next=\"20151129095850\"/><timestamp localstart=\"11/29/2015 09:58:20\" localtime=\"11/29/2015 09:58:40\" timestring=\"20151129095840\"/><gathererinterval seconds=\"20\"/><data-range seconds=\"20\"/><row label=\"DSNPCAT.LOGCOPY2.DS02.DATA\" value=\"60\" per=\"60\" ex=\"NONE\"/><row label=\"SYS1.SMF.MANY.DATA\" value=\"40\" per=\"40\" ex=\"NONE\"/><row label=\"SYS1.SMF.MANX.DATA\" value=\"40\" per=\"40\" ex=\"NONE\"/><row label=\"DSNPCAT.LOGCOPY1.DS02.DATA\" value=\"40\" per=\"40\" ex=\"NONE\"/><row label=\"SYS1.SMF.DUMP.MANXYZ.G2372V00\" value=\"10\" per=\"10\" ex=\"NONE\"/><row label=\"PSPCAT.DSNDBD.NGFS.IX4ISORQ.J0001.A002\" value=\"0\" per=\"0\" ex=\"NONE\"/><row label=\"PSPCAT.DSNDBD.NGFS.TSBASRQ.I0001.A001\" value=\"0\" per=\"0\" ex=\"NONE\"/></metric></performance-data></perf-config>";

            log.info("getRMFMonitoringXMLData() successfully done, rmfUrl : " + rmfUrl + ", Result : " + rmfMonitoringData.toString());
            return rmfXmlData;
        }
        catch (Exception ex) {
            log.error("getRMFMonitoringXMLData() Exception: rmfUrl:" + configuredMetricsView.toString() + " , Message : " + ex.getMessage());
            throw ex;
        }

    }

    public String sendHttpRequest(String userName, String password, String requestURL) {

        HttpURLConnection httpConnection = null;
        try {
            Authenticator.setDefault(new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password.toCharArray());
                }
            });

            URL url = new URL(requestURL);
            httpConnection = (HttpURLConnection)url.openConnection();    //Create Http Connection
//            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            httpConnection.setRequestProperty("Content-Length",Integer.toString("".getBytes().length));
            httpConnection.setRequestProperty("Content-Language", "en-US");

            httpConnection.setUseCaches(false);
            httpConnection.setDoOutput(true);
//            httpConnection.setRequestProperty("User-Agent", "Monitoring AGENT");
//            httpConnection.setConnectTimeout(4000);
//            httpConnection.setReadTimeout(4000);

            //Send request
            DataOutputStream wr = new DataOutputStream (httpConnection.getOutputStream());
            wr.writeBytes("");
            wr.close();

            //Get Response
            InputStream inputStream = httpConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder response = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            bufferedReader.close();
            return response.toString();
        }
        catch (Exception e) {
            log.error("sendHttpRequest error, requestURL :" + requestURL);
            e.printStackTrace();
            return null;
        }
        finally {
            if(httpConnection != null) {
                httpConnection.disconnect();
            }
        }
    }

    }
