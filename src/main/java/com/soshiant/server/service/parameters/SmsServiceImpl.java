package com.soshiant.server.service.parameters;

import com.soshiant.common.dateTime.DateTimeUtil;
import com.soshiant.common.util.BundleUtil;
import com.soshiant.common.util.CommonUtil;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.constants.ServiceConstants;
import com.soshiant.server.dao.parameters.SmsDao;
import com.soshiant.server.model.parameters.*;
import com.soshiant.server.model.staff.StaffShiftView;
import com.soshiant.server.service.log.AppLogService;
import com.soshiant.webservice.sms.adpdigital.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class SmsServiceImpl implements SmsService {
    Logger log = Logger.getLogger(SmsServiceImpl.class);

    private String actionName;
    private String logMessage = "";



    private SmsDao smsDao;
    private AppLogService appLogService;

    public void setSmsDao(SmsDao smsDao) {
        this.smsDao = smsDao;
    }

    public void setAppLogService(AppLogService appLogService) {
        this.appLogService = appLogService;
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public List<SendSmsInfo> getSmsList() {

        return smsDao.getSmsList();
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public List<AlarmMessageInfo> getAlarmsList() {

        return smsDao.getAlarmsList();
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public List<ReceivedSmsInfo> getReceivedSmsList() {

        return smsDao.getReceivedSmsList();
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public void saveAlarmInfo(AlarmMessageInfo alarmMessageInfo) {
        smsDao.saveAlarmInfo(alarmMessageInfo);
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public void saveSmsInfo(SendSmsInfo smsInfo) {
        smsInfo.setCellphone(addCountryPrefix(smsInfo.getCellphone()));
        smsDao.saveSmsInfo(smsInfo);
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public void saveSmsInfo(String cellPhone , String messageText) {

        cellPhone = addCountryPrefix(cellPhone);
        SendSmsInfo smsInfo = new SendSmsInfo() ;
        smsInfo.setCellphone(cellPhone);
        smsInfo.setMessageText(messageText);
        smsInfo.setRegisterDate(DateTimeUtil.getCurrentShamsiDate());
        smsInfo.setSendDate(0);
        smsDao.saveSmsInfo(smsInfo);
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public void markSmsListAsSent(List<SendSmsInfo> smsInfoList,int sendDate) {

        smsDao.markSmsListAsSent(smsInfoList, sendDate);
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public void markAlarmListAsSent(List<AlarmMessageInfo> smsInfoList,int sendDate) {

        smsDao.markAlarmListAsSent(smsInfoList, sendDate);
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public short sendSMSMessage(String cellPhone , String messageText){

        short smsResultStatus = 0;
        try{
            boolean longMessageSupported = true;
            cellPhone = addCountryPrefix(cellPhone);
            String[] destinationNumber = {cellPhone};
            JaxRpcMessagingServiceServiceLocator locator = new JaxRpcMessagingServiceServiceLocator();
            JaxRpcMessagingService adpMessagingService =  locator.getMessagingService();
            SendResult sendMessageResult = adpMessagingService.send(ServerConstants.SMS_ADP_MESSAGE_USERNAME,
                                                                    ServerConstants.SMS_ADP_MESSAGE_PASSWORD,
                                                                    ServerConstants.SMS_ADP_SOURCE_NUMBER,
                                                                    destinationNumber,null,null,null,
                                                                    ServerConstants.SMS_MESSAGE_TYPE_PLAIN_TEXT,
                                                                    ServerConstants.SMS_MESSAGE_ENCODING_LATIN,
                                                                    longMessageSupported,null,messageText);
            smsResultStatus = sendMessageResult.getStatus();
            if (ServerConstants.isAppLogEnabled)
                appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, smsResultStatus, 0, ActionConstants.SEND_SMS_ACTION_PROCESS_CODE, "", "", "SmsService.sendSMSMessage()", "", getSendMessageResultText(smsResultStatus),"", "");
            if(smsResultStatus != 0){
                saveSmsInfo(cellPhone,messageText);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("SmsMessage exception : " + ex.getStackTrace());
            if (ServerConstants.isAppLogEnabled)
                appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, smsResultStatus, 0, ActionConstants.SEND_SMS_ACTION_PROCESS_CODE, "", "", "SmsService.sendSMSMessage()", "", "exception sending sms",ex.getMessage(), "");
            saveSmsInfo(cellPhone,messageText);

        }
        return smsResultStatus;
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public short sendAlarmMessage(){

        short smsResultStatus = 0;
        int smsCount = 0;
        try{
            if(!ServiceConstants.isSendSmsEnabled()) {
                return 0;
            }

            List<AlarmMessageInfo> alarmMessageInfoList = smsDao.getAlarmsList();

            if (alarmMessageInfoList == null || alarmMessageInfoList.size() == 0) {
                this.logMessage = this.actionName + " : error getAlarmList() is empty, Time: " + DateTimeUtil.getCurrentTimeStamp();
                return 0;
            }

            int currentTime = Integer.parseInt(DateTimeUtil.getCurrentTime(ServerConstants.TIME_FORMAT_WITH_MINUTE, false));
            int shiftDate = DateTimeUtil.getCurrentShamsiDate();
            List<StaffShiftView> staffShiftList = smsDao.getShiftListForAlarm(shiftDate,currentTime);

            if (staffShiftList == null || staffShiftList.size() == 0) {
                this.logMessage = this.actionName + " : error getShiftList() is empty, Time: " + DateTimeUtil.getCurrentTime();
                return 0;
            }

            boolean longMessageSupported = true;
            int currentDate = DateTimeUtil.getCurrentShamsiDate();
            String[] destinationNumber = new String[1];
            MultiAddressMessageObject[] messages = new MultiAddressMessageObject[smsCount];
            int i = 0;
            for (AlarmMessageInfo alarmMessageInfo : alarmMessageInfoList){

                for (StaffShiftView staffShift : staffShiftList){

                    if(alarmMessageInfo != null && !StringUtils.isEmpty(staffShift.getCellPhone())){
                        MultiAddressMessageObject tmpMessage = new MultiAddressMessageObject();
                        destinationNumber[0] = addCountryPrefix(staffShift.getCellPhone());
                        tmpMessage.setClientIds(null);
                        tmpMessage.setContent(alarmMessageInfo.getMessageText());
                        tmpMessage.setEncoding(ServerConstants.SMS_MESSAGE_ENCODING_LATIN);
                        tmpMessage.setDueTime(DateTimeUtil.getCurrentDateTimeCalendar());
                        tmpMessage.setPhoneNumbers(destinationNumber);
                        messages[i] = tmpMessage;
                        i++;
                    }
                }
            }
            JaxRpcMessagingServiceServiceLocator locator = new JaxRpcMessagingServiceServiceLocator();
            JaxRpcMessagingService adpMessagingService =  locator.getMessagingService();
            SendResult sendMessageResult = adpMessagingService.sendMultiple(ServerConstants.SMS_ADP_MESSAGE_USERNAME,
                                                                            ServerConstants.SMS_ADP_MESSAGE_PASSWORD,
                                                                            ServerConstants.SMS_ADP_SOURCE_NUMBER,
                                                                            null, null,
                                                                            ServerConstants.SMS_MESSAGE_TYPE_PLAIN_TEXT,
                                                                            longMessageSupported,
                                                                            messages);
            smsResultStatus = sendMessageResult.getStatus();
            if(smsResultStatus == ServerConstants.SMS_MESSAGE_SEND_STATUS_OK){
                markAlarmListAsSent(alarmMessageInfoList, currentDate);
            }

            if (ServerConstants.isAppLogEnabled)
                appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, smsResultStatus, 0, ActionConstants.SEND_SMS_ACTION_PROCESS_CODE, "", "", "SmsService.sendMultipleSMSMessage()", "", getSendMessageResultText(smsResultStatus),"", "");

        } catch (Exception ex) {
            ex.printStackTrace();
            if (ServerConstants.isAppLogEnabled)
                appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.SEND_SMS_ACTION_PROCESS_CODE, "", "", "SmsService.sendMultipleSMSMessage()", "", "exception sending sms",ex.getMessage(), "");
        }
        return smsResultStatus;
    }
    //======================================================================================================================
    //======================================================================================================================
    @Transactional(readOnly = false)
    public short sendMultipleSMSMessage(){

        short smsResultStatus = 0;
        int smsCount = 0;
        try{
            boolean longMessageSupported = true;
            int currentDate = DateTimeUtil.getCurrentShamsiDate();
            List<SendSmsInfo> smsInfoList =  smsDao.getSmsList();
            if(smsInfoList == null || smsInfoList.isEmpty()){
                if (ServerConstants.isAppLogEnabled)
                    appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, 0, 0, ActionConstants.SEND_SMS_ACTION_PROCESS_CODE, "", "", "SmsService.sendMultipleSMSMessage()", "", "there is no message to send","", "");
                return 0;
            }
            smsCount = smsInfoList.size();
            String[] destinationNumber = new String[1];
            MultiAddressMessageObject[] messages = new MultiAddressMessageObject[smsCount];
            for(int i = 0; i < smsCount;i++){

                SendSmsInfo smsInfo = smsInfoList.get(i);
                if(smsInfo != null && !StringUtils.isEmpty(smsInfo.getCellphone())){
                    MultiAddressMessageObject tmpMessage = new MultiAddressMessageObject();
                    destinationNumber[0] = addCountryPrefix(smsInfo.getCellphone());
                    tmpMessage.setClientIds(null);
                    tmpMessage.setContent(smsInfo.getMessageText());
                    tmpMessage.setEncoding(ServerConstants.SMS_MESSAGE_ENCODING_LATIN);
                    tmpMessage.setDueTime(DateTimeUtil.getCurrentDateTimeCalendar());
                    tmpMessage.setPhoneNumbers(destinationNumber);
                    messages[i] = tmpMessage;
                }
            }
            JaxRpcMessagingServiceServiceLocator locator = new JaxRpcMessagingServiceServiceLocator();
            JaxRpcMessagingService adpMessagingService =  locator.getMessagingService();
            SendResult sendMessageResult = adpMessagingService.sendMultiple(ServerConstants.SMS_ADP_MESSAGE_USERNAME,
                                                                            ServerConstants.SMS_ADP_MESSAGE_PASSWORD,
                                                                            ServerConstants.SMS_ADP_SOURCE_NUMBER,
                                                                            null, null,
                                                                            ServerConstants.SMS_MESSAGE_TYPE_PLAIN_TEXT,
                                                                            longMessageSupported,
                                                                            messages);
            smsResultStatus = sendMessageResult.getStatus();
            if(smsResultStatus == ServerConstants.SMS_MESSAGE_SEND_STATUS_OK){
                markSmsListAsSent(smsInfoList,currentDate);
            }

            if (ServerConstants.isAppLogEnabled)
                appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, smsResultStatus, 0, ActionConstants.SEND_SMS_ACTION_PROCESS_CODE, "", "", "SmsService.sendMultipleSMSMessage()", "", getSendMessageResultText(smsResultStatus),"", "");

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("sendMultipleSMSMessage exception : " + ex.getStackTrace());
            if (ServerConstants.isAppLogEnabled)
                appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.SEND_SMS_ACTION_PROCESS_CODE, "", "", "SmsService.sendMultipleSMSMessage()", "", "exception sending sms",ex.getMessage(), "");
        }
        return smsResultStatus;
    }
    //======================================================================================================================
    public String getSendMessageResultText(short resultStatus){

        Locale locale = new Locale("en", "US");
        BundleUtil bundle = BundleUtil.getInstance(locale);
        switch (resultStatus) {
            case 0  : return bundle.getMessage("sms.adp.sendStatus.0");
            case 1  : return bundle.getMessage("sms.adp.sendStatus.1");
            case 2  : return bundle.getMessage("sms.adp.sendStatus.2");
            case 3  : return bundle.getMessage("sms.adp.sendStatus.3");
            case 4  : return bundle.getMessage("sms.adp.sendStatus.4");
            case 5  : return bundle.getMessage("sms.adp.sendStatus.5");
            case 6  : return bundle.getMessage("sms.adp.sendStatus.6");
            case 7  : return bundle.getMessage("sms.adp.sendStatus.7");
            case 8  : return bundle.getMessage("sms.adp.sendStatus.8");
            case 9  : return bundle.getMessage("sms.adp.sendStatus.9");
            case 10 : return bundle.getMessage("sms.adp.sendStatus.10");
            case 11 : return bundle.getMessage("sms.adp.sendStatus.11");
            case 12 : return bundle.getMessage("sms.adp.sendStatus.12");
            case 13 : return bundle.getMessage("sms.adp.sendStatus.13");
            case 14 : return bundle.getMessage("sms.adp.sendStatus.14");
            case 15 : return bundle.getMessage("sms.adp.sendStatus.15");
            default : return bundle.getMessage("sms.adp.sendStatus.unKnown");
        }
    }
//======================================================================================================================
    @Transactional(readOnly = false)
    public short receiveSMS() {

        String logMessage = "";
        short smsResultStatus = 0;
        JaxRpcMessagingServiceServiceLocator locator = new JaxRpcMessagingServiceServiceLocator();
        try{
            JaxRpcMessagingService adpMessagingService =  locator.getMessagingService();
            ReceiveResult receiveMessageResult = adpMessagingService.receive(ServerConstants.SMS_ADP_MESSAGE_USERNAME,
                                                                       ServerConstants.SMS_ADP_MESSAGE_PASSWORD,
                                                                       "",0,0);
            IncomingMessage[] msg= receiveMessageResult.getMessages();
            if(msg != null  && msg.length > 0){
                logMessage = "received messages count : " + msg.length;
                for (IncomingMessage msgItem : msg) {

                    ReceivedSmsInfo rcvSmsInfo = new ReceivedSmsInfo();
                    rcvSmsInfo.setRegisterDate(DateTimeUtil.getCurrentShamsiDate());
                    rcvSmsInfo.setSmsId(msgItem.getId());
                    rcvSmsInfo.setFromCellphone(msgItem.getFrom());
                    rcvSmsInfo.setToNumber(msgItem.getTo());
                    rcvSmsInfo.setMessageText(msgItem.getTo());
                    rcvSmsInfo.setReceiveTime(String.valueOf(msgItem.getTime().getTime()));
                    smsDao.saveReceivedSmsInfo(rcvSmsInfo);
                }
                logMessage += " and saved successfully";
            }
            else {
                logMessage = "empty received message list";
            }
            if (ServerConstants.isAppLogEnabled)
                appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.RECEIVE_SMS_ACTION_PROCESS_CODE, "", "", "SmsService.receiveSMS()", "", logMessage,"", "");


        } catch (Exception ex) {

            ex.printStackTrace();
            System.out.print("receiveSMS exception : " + ex.getStackTrace());
            if (ServerConstants.isAppLogEnabled)
                appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.RECEIVE_SMS_ACTION_PROCESS_CODE, "", "", "SmsService.receiveSMS()", "", "exception receiving sms",ex.getMessage(), "");
        }
        return smsResultStatus;
    }
//======================================================================================================================
    @Transactional(readOnly = false)
    public short processReceiveSMSMessages() {

        String logMessage = "";
        short smsResultStatus = 0;
        JaxRpcMessagingServiceServiceLocator locator = new JaxRpcMessagingServiceServiceLocator();
        try{

            List<ReceivedSmsInfo> receivedSmsList = smsDao.getReceivedSmsList();
            if(receivedSmsList != null  && receivedSmsList.size() > 0) {
                logMessage = "processed messages count : " + receivedSmsList.size();
                for (ReceivedSmsInfo receivedSmsItem : receivedSmsList) {
                    String messageText = CommonUtil.nvl(receivedSmsItem.getMessageText(),"").trim().toLowerCase();
                    if(messageText.contains(ServerConstants.SMS_RECEIVED_CONFIRM_ENGLISH_TEXT) || messageText.contains(ServerConstants.SMS_RECEIVED_CONFIRM_PERSIAN_TEXT)){
//                        studentDao.saveReservedSeminarConfirmation(CommonUtil.removeZeroFromCellphone(receivedSmsItem.getFromCellphone()),DateTimeUtil.getCurrentShamsiDate());
                    }
                    smsDao.markReceivedSmsAsProcessed(receivedSmsItem,DateTimeUtil.getCurrentShamsiDate());
                }
                logMessage += " and processed successfully";
            }
            else {
                logMessage = "empty process message list";
            }
            if (ServerConstants.isAppLogEnabled)
                appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.RECEIVE_SMS_ACTION_PROCESS_CODE, "", "", "SmsService.processReceiveSMSMessages()", "", logMessage,"", "");


        } catch (Exception ex) {

            ex.printStackTrace();
            System.out.print("SmsService.processReceiveSMSMessages() exception : " + ex.getStackTrace());
            if (ServerConstants.isAppLogEnabled)
                appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.RECEIVE_SMS_ACTION_PROCESS_CODE, "", "", "SmsService.processReceiveSMSMessages()", "", "exception processing sms",ex.getMessage(), "");
        }
        return smsResultStatus;
    }
//======================================================================================================================
    public String addCountryPrefix(String cellPhone) {

        if(!cellPhone.startsWith(ServerConstants.SMS_MESSAGE_COUNTRY_PREFIX)){
            if(cellPhone.startsWith("0")){
                cellPhone = StringUtils.removeStart(cellPhone,"0");
            }
            cellPhone = ServerConstants.SMS_MESSAGE_COUNTRY_PREFIX + cellPhone;
        }
        return cellPhone;
    }
}
