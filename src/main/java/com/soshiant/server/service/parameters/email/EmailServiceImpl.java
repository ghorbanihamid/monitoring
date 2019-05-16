package com.soshiant.server.service.parameters.email;

import com.soshiant.common.dateTime.DateTimeUtil;
import com.soshiant.common.util.BundleUtil;
import com.soshiant.common.util.EmailSender;
import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.dao.email.EmailDao;
import com.soshiant.server.model.parameters.EmailInfo;
import com.soshiant.server.service.BusinessException;
import com.soshiant.server.service.log.AppLogService;
import com.soshiant.webservice.sms.adpdigital.JaxRpcMessagingService;
import com.soshiant.webservice.sms.adpdigital.JaxRpcMessagingServiceServiceLocator;
import com.soshiant.webservice.sms.adpdigital.MultiAddressMessageObject;
import com.soshiant.webservice.sms.adpdigital.SendResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class EmailServiceImpl implements EmailService {

    private EmailDao emailDao;
    private AppLogService appLogService;

    public void setEmailDao(EmailDao emailDao) {
        this.emailDao = emailDao;
    }

    public void setAppLogService(AppLogService appLogService) {
        this.appLogService = appLogService;
    }

//======================================================================================================================
    private class SmtpAuthenticator extends Authenticator
    {
        String userName;
        String password;

        public SmtpAuthenticator(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(this.userName, this.password);
        }
    }
//======================================================================================================================
    @Transactional(readOnly = true)
    public List<EmailInfo> getEmailList() {

        return emailDao.getEmailList();
    }
//======================================================================================================================
    @Transactional(readOnly = false)
    public void saveEmailInfo(EmailInfo emailInfo) {
//        smsInfo.setCellphone(addCountryPrefix(smsInfo.getCellphone()));
//        smsDao.saveSmsInfo(smsInfo);
    }
//======================================================================================================================
    @Transactional(readOnly = false)
    public void saveEmailInfo(String from,String recipientsTo[], String recipientsCc[], String subject, String emailContent,String attachmentFile) {

//        cellPhone = addCountryPrefix(cellPhone);
        EmailInfo emailInfo = new EmailInfo() ;
//        smsInfo.setCellphone(cellPhone);
//        smsInfo.setMessageText(messageText);
//        smsInfo.setRegisterDate(DateTimeUtil.getCurrentShamsiDate());
//        smsInfo.setSendDate(0);
        emailDao.saveEmailInfo(emailInfo);
    }
//======================================================================================================================
    @Transactional(readOnly = false)
    public void markEmailListAsSent(List<EmailInfo> emailInfoList,int sendDate) {

        emailDao.markEmailListAsSent(emailInfoList,sendDate);
    }
//======================================================================================================================
    @Transactional(readOnly = false)
    public void sendEmail(String smtpServerType,String recipientsTo[],String recipientsCc[], String subject, String emailContent,String attachmentFile){

//        Thread senderThread = new Thread(new EmailSenderThread(from,recipientsTo,recipientsCc, subject, emailContent,attachmentFile));
//        senderThread.start();
        String senderEmailAddress;
        EmailSender emailSender;
        if(smtpServerType.equalsIgnoreCase(ServerConstants.SMTP_SERVER_GMAIL)){
            emailSender = new EmailSender(ServerConstants.SMTP_SERVER_GMAIL,ServerConstants.SMTP_GMAIL_PLACEMENT_TEST_EMAIL_USER,ServerConstants.SMTP_GMAIL_PLACEMENT_TEST_EMAIL_PASSWORD,true);
            senderEmailAddress = ServerConstants.PLACEMENT_TEST_SENDER_GMAIL_ADDRESS;
        }
        else {
            emailSender = new EmailSender(ServerConstants.SMTP_SERVER_KORDASTI,ServerConstants.SMTP_KORDASTI_PLACEMENT_TEST_EMAIL_USER,ServerConstants.SMTP_KORDASTI_PLACEMENT_TEST_EMAIL_PASSWORD,false);
            senderEmailAddress = ServerConstants.PLACEMENT_TEST_SENDER_KORDASTI_ADDRESS;
        }
        emailSender.sendEmail(senderEmailAddress, recipientsTo, recipientsCc, subject, emailContent, attachmentFile);
    }

//======================================================================================================================
    @Transactional(readOnly = false)
    public void sendEmail1(String from, String recipientsTo[], String recipientsCc[], String subject, String emailContent, String attachmentFile){

        try{
            String appMessage;


            String smtpServer = "smtp.gmail.com";
            String smtpPort   = "465";
            String userName   = "ghorbani.h@gmail.com";
            String password   = "hamid1687";
            String fallback   = "false";


            Properties properties = new Properties();

            properties.put("mail.smtp.host", smtpServer);
            properties.put("mail.smtp.port", smtpPort);

            properties.put("mail.smtp.debug", "true");
            properties.put("mail.smtp.user", userName);

            //To use TLS
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable","true");
            properties.put("mail.smtp.password", password);

            //To use SSL
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.socketFactory.port", smtpPort);
            properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            properties.put("mail.smtp.socketFactory.fallback", fallback);

            SmtpAuthenticator authentication = new SmtpAuthenticator(userName,password);

            // getting Session
            Session mailSession = Session.getDefaultInstance(properties, authentication);
            mailSession.setDebug(true);

            // create a message
            Message msg = new MimeMessage(mailSession);

            // Setting sender and Recipients address
            InternetAddress addressFrom = new InternetAddress(from);
            msg.setFrom(addressFrom);

            // Adding all To recipients
            InternetAddress[] addressTo = new InternetAddress[recipientsTo.length];
            for (int i = 0; i < recipientsTo.length; i++) {
                if(!StringUtils.isEmpty(recipientsTo[i])){
                    addressTo[i] = new InternetAddress(recipientsTo[i]);
                }

            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);

            // Adding all CC recipients
            if(recipientsCc != null ){
                InternetAddress[] addressCc = new InternetAddress[recipientsCc.length];
                for (int i = 0; i < recipientsCc.length; i++) {
                    if(!StringUtils.isEmpty(recipientsCc[i])){
                        addressCc[i] = new InternetAddress(recipientsCc[i]);
                    }
                }
                if(addressCc.length > 0){
                    msg.setRecipients(Message.RecipientType.CC, addressCc);
                }
            }
            // Setting e-mail header
            msg.addHeader("MailingApplication", "Struts 2 Mailing Application");

            // Setting the Subject and Content Type
            msg.setSubject(subject);
            msg.setContent(emailContent, "text/html");
            msg.setText(emailContent);
            if(!StringUtils.isEmpty(attachmentFile)){
                FileSystemResource file = new FileSystemResource(new File("c:/Sample.jpg"));
                msg.setFileName(attachmentFile);
            }
            msg.saveChanges();
            Transport transport = mailSession.getTransport("smtps");
            transport.connect(smtpServer, userName, password);
//            transport.sendMessage(msg, msg.getAllRecipients());
            if(!transport.isConnected()){
                if (ServerConstants.isAppLogEnabled)
                    appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, 0, 0, ActionConstants.SEND_EMAIL_ACTION_PROCESS_CODE, "", "", "EmailService.sendEmail()", "", "could not connect to mail server","", "recipientsTo : " + recipientsTo);
                saveEmailInfo(from,recipientsTo,recipientsCc, subject, emailContent,attachmentFile);
                throw new BusinessException(ServerConstants.ERROR_SYSTEM_COULD_NOT_CONNECT_EMAIL_SERVER);
            }
            transport.send(msg);
            transport.close();
            if (ServerConstants.isAppLogEnabled)
                appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, 0, 0, ActionConstants.SEND_EMAIL_ACTION_PROCESS_CODE, "", "", "EmailService.sendEmail()", "", "email sent successfully","", "recipientsTo : " + recipientsTo);

        } catch (Exception ex) {
            System.out.print("EmailMessage exception : " + ex.getStackTrace());
            if (ServerConstants.isAppLogEnabled)
                appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, 0, 0, ActionConstants.SEND_EMAIL_ACTION_PROCESS_CODE, "", "", "EmailService.sendEmail()", "", "exception sending email",ex.getMessage(), "");
            saveEmailInfo(from,recipientsTo,recipientsCc, subject, emailContent,attachmentFile);
            throw new BusinessException(ServerConstants.ERROR_SYSTEM_COULD_NOT_SEND_EMAIL);
        }
    }
//======================================================================================================================
    @Transactional(readOnly = false)
    public short sendMultipleEmailMessage(){

        short smsResultStatus = 0;
        int smsCount = 0;
        try{
            boolean longMessageSupported = true;
            int currentDate = DateTimeUtil.getCurrentShamsiDate();
            List<EmailInfo> emailInfoList =  emailDao.getEmailList();
            if(emailInfoList == null || emailInfoList.isEmpty()){
                if (ServerConstants.isAppLogEnabled)
                    appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, 0, 0, ActionConstants.SEND_EMAIL_ACTION_PROCESS_CODE, "", "", "SmsService.sendMultipleSMSMessage()", "", "there is no message to send","", "");
                return 0;
            }
            smsCount = emailInfoList.size();
            String[] destinationNumber = new String[1];
            MultiAddressMessageObject[] messages = new MultiAddressMessageObject[smsCount];
            for(int i = 0; i < smsCount;i++){

                EmailInfo emailInfo = emailInfoList.get(i);
                if(emailInfo != null && !StringUtils.isEmpty(emailInfo.getEmailTo())){
                    MultiAddressMessageObject tmpMessage = new MultiAddressMessageObject();
                    destinationNumber[0] = emailInfo.getEmailTo();
                    tmpMessage.setClientIds(null);
                    tmpMessage.setContent(emailInfo.getEmailContent());
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
                markEmailListAsSent(emailInfoList,currentDate);
            }

            if (ServerConstants.isAppLogEnabled)
                appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, smsResultStatus, 0, ActionConstants.SEND_EMAIL_ACTION_PROCESS_CODE, "", "", "SmsService.sendMultipleSMSMessage()", "", getSendMessageResultText(smsResultStatus),"", "");

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("sendMultipleSMSMessage exception : " + ex.getStackTrace());
            if (ServerConstants.isAppLogEnabled)
                appLogService.logInfo(ServerConstants.ActionMethodIsMainOperation, 0, 0, -1, 0, ActionConstants.SEND_EMAIL_ACTION_PROCESS_CODE, "", "", "SmsService.sendMultipleSMSMessage()", "", "exception sending sms",ex.getMessage(), "");
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
            case 7  : return bundle.getMessage("sms.adp.sendStatus.6");
            case 8  : return bundle.getMessage("sms.adp.sendStatus.7");
            case 9  : return bundle.getMessage("sms.adp.sendStatus.8");
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





//public class Mail
//
//
//{
//
//
//         String  d_email = "iamdvr@gmail.com",
//
//
//            d_password = "****",
//
//
//            d_host = "smtp.gmail.com",
//
//
//            d_port  = "465",
//
//
//            m_to = "iamdvr@yahoo.com",
//
//
//            m_subject = "Testing",
//
//
//            m_text = "Hey, this is the testing email using smtp.gmail.com.";
//
//
//    public static void main(String[] args)
//    {
//                String[] to={"XXX@yahoo.com"};
//
//
//                String[] cc={"XXX@yahoo.com"};
//
//
//                String[] bcc={"XXX@yahoo.com"};
//
//
//                Mail.sendMail("venkatesh@dfdf.com","password","smtp.gmail.com","465","true","true",true,"javax.net.ssl.SSLSocketFactory","false",to,cc,bcc,
//
//                              "hi baba don't send virus mails..","This is my style...of reply..If u send virus mails..");
//
//    }
//
//
//
//
//
//
//
//
//
//
//}
