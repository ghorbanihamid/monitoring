package com.soshiant.server.thread;

import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.service.parameters.email.EmailService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Hubert
 * Date: 4/21/12
 * Time: 11:08 AM
 **/
public class EmailSenderThread implements Runnable{

    String emailFrom;
    String[] recipientsTo;
    String[] recipientsCc;
    String emailSubject;
    String emailContent;
    String attachmentFile;

    EmailService emailService;

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public EmailSenderThread() {

    }
//======================================================================================================================
    public EmailSenderThread(String emailFrom,String[] recipientsTo, String[] recipientsCc,String emailSubject, String emailContent, String attachmentFile) {
        this.emailFrom      = emailFrom;
        this.recipientsTo   = recipientsTo;
        this.recipientsCc   = recipientsCc;
        this.emailSubject   = emailSubject;
        this.emailContent   = emailContent;
        this.attachmentFile = attachmentFile;
    }
//======================================================================================================================
    public void run() {
        Logger log = Logger.getLogger(this.getClass().getName());

        try{
            emailService.sendEmail(emailFrom,recipientsTo,recipientsCc, emailSubject, emailContent, attachmentFile);

        }
        catch (Exception e) {
            log.error(e.getMessage() +"from : '" + emailFrom + "', to :'" + recipientsTo +"', cc :'" + recipientsCc + "' " + "subject :'" + emailSubject + "' " + "attachmentFile :'" + attachmentFile + "' ");
        }
    }
//======================================================================================================================

    public String getAttachmentFile() {
        return attachmentFile;
    }

    public void setAttachmentFile(String attachmentFile) {
        this.attachmentFile = attachmentFile;
    }
//======================================================================================================================
    public void sendEmail(String from,String recipientsTo[],String recipientsCc[], String subject, String emailContent,String attachmentFile){

        try{
            boolean debug = false;


            String smtpServer = "smtp.gmail.com";

            Properties properties = new Properties();

            properties.put("mail.smtp.host", smtpServer);
//            properties.put("mail.smtp.port", "" + smtpPort);

            // getting Session
            Session mailSession = Session.getDefaultInstance(properties, null);
            mailSession.setDebug(debug);

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
            // Adding all CC recipients
            InternetAddress[] addressCc = new InternetAddress[recipientsCc.length];
            for (int i = 0; i < recipientsCc.length; i++) {
                if(!StringUtils.isEmpty(recipientsCc[i])){
                    addressCc[i] = new InternetAddress(recipientsCc[i]);
                }
            }

            msg.setRecipients(Message.RecipientType.TO, addressTo);

            if(addressCc.length > 0){
                msg.setRecipients(Message.RecipientType.CC, addressCc);
            }

            // Setting e-mail header
            msg.addHeader("MailingApplication", "Struts 2 Mailing Application");

            // Setting the Subject and Content Type
            msg.setSubject(subject);
            msg.setContent(emailContent, "text/html");
            if(!StringUtils.isEmpty(attachmentFile)){
                FileSystemResource file = new FileSystemResource(new File("c:/Sample.jpg"));
                msg.setFileName(attachmentFile);
            }

            Transport.send(msg);

        } catch (Exception ex) {
            System.out.print("EmailMessage exception : " + ex.getStackTrace());
        }
    }

}
