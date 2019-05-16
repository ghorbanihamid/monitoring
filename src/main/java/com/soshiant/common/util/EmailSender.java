package com.soshiant.common.util;

import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.service.BusinessException;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Created with IntelliJ IDEA.
 * User: Hubert
 * Date: 4/21/12
 * Time: 2:29 PM
 **/
public class EmailSender {

    Logger log = Logger.getLogger(this.getClass().getName());

    boolean sslAuthentication = false;
    String smtpServer  = "";
    String smtpPort    = "";
    String userName    = "";
    String password    = "";
    String fallback    = "false";

    public EmailSender(String smtpServer, String userName, String password, boolean sslAuthentication){

        this.sslAuthentication = sslAuthentication;
        if(smtpServer.equals(ServerConstants.SMTP_SERVER_GMAIL)){
            this.smtpServer = "smtp.gmail.com";
            if(sslAuthentication){
                this.smtpPort   = "465";
            }
            else{
                this.smtpPort   = "25";
            }
        }
        if(smtpServer.equals(ServerConstants.SMTP_SERVER_KORDASTI)){
            this.smtpServer = "mail.kordasti.com";
            if(sslAuthentication){
                this.smtpPort   = "465";
            }
            else{
                this.smtpPort   = "25";
            }

        }
        this.userName = userName;
        this.password = password;
    }

//======================================================================================================================
    public synchronized boolean sendEmailFromGmail(String from, String recipientsTo[], String recipientsCc[], String subject, String emailContent, String attachmentFile){

        try{

            Properties properties = new Properties();

            properties.put("mail.smtp.host", this.smtpServer);
            properties.put("mail.smtp.port", this.smtpPort);

            properties.put("mail.smtp.debug", "false");
            properties.put("mail.smtp.user", this.userName);

            //To use TLS
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable","true");
            properties.put("mail.smtp.password", this.password);

            //To use SSL
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.socketFactory.port", this.smtpPort);
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
            msg.setContent(emailContent,"text/html; charset=utf-8");
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
                log.error("Error : EmailSender.sendEmail() Could not connect to SmtpServer : " + smtpServer);
                throw new BusinessException(ServerConstants.ERROR_SYSTEM_COULD_NOT_CONNECT_EMAIL_SERVER);
            }
            transport.send(msg);
            transport.close();
            return true;

        } catch (Exception ex) {
            log.error("Error : EmailSender.sendEmail() Could not send email from SmtpServer : " + smtpServer + " exception : " + ex.getStackTrace());
            throw new BusinessException(ServerConstants.ERROR_SYSTEM_COULD_NOT_SEND_EMAIL);
        }
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
    public boolean sendEmail(String from, String recipientsTo[], String recipientsCc[], String subject, String emailContent, String attachmentFile){


        try {
            // prepare email
            HtmlEmail htmlEmail = new HtmlEmail();

            //set email server
            htmlEmail.setHostName(this.smtpServer);                      //  "smtp.googlemail.com"
            htmlEmail.setSmtpPort(Integer.parseInt(this.smtpPort));      // 465
    //        htmlEmail.setAuthentication(this.userName, this.password);
            htmlEmail.setAuthenticator(new DefaultAuthenticator(this.userName, this.password));
            if(this.sslAuthentication) {
                htmlEmail.setSSL(true);
    //        htmlEmail.setSslSmtpPort();
            }
            htmlEmail.setCharset("UTF-8");
            htmlEmail.setFrom(from);
            htmlEmail.setSubject(subject);
            htmlEmail.setHtmlMsg(emailContent);
//            htmlEmail.setMsg(emailContent);
            for(int i = 0; i < recipientsTo.length; i++){
                htmlEmail.addTo(recipientsTo[i]);
            }
            if(recipientsCc != null && recipientsCc.length > 0){
                for(int i = 0; i < recipientsCc.length; i++){
                    htmlEmail.addTo(recipientsCc[i]);
                }
            }
            if (!StringUtils.isEmpty(attachmentFile)) {
                EmailAttachment attachment = new EmailAttachment();
                attachment.setPath(attachmentFile);
                attachment.setDisposition(EmailAttachment.ATTACHMENT);
                attachment.setDescription("");
                attachment.setName("");
                htmlEmail.attach(attachment);
            }

            htmlEmail.send();
            return true;

        } catch (Exception ex) {
            log.error("Error : EmailSender.sendEmail() Could not send email from SmtpServer : " + smtpServer + " exception : " + ex.getStackTrace());
            throw new BusinessException(ServerConstants.ERROR_SYSTEM_COULD_NOT_SEND_EMAIL);
        }

    }
//======================================================================================================================

}
