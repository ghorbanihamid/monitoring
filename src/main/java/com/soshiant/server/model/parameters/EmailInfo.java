package com.soshiant.server.model.parameters;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 6:05 PM
 */

@Entity
@Table(name = "EMAILINFO", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID")})
public class EmailInfo implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int    id;

    private int    registerDate;
    private int    sendDate;
    private String emailFrom;
    private String emailTo;
    private String emailCc;
    private String emailBcc;
    private String emailSubject;
    private String emailContent;
    private String attachmentPath;
    private String attachmentName;


    @Column(name = "Id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "RegisterDate", unique = true, nullable = false)
    public int getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(int registerDate) {
        this.registerDate = registerDate;
    }

    @Column(name = "SendDate", unique = true, nullable = false)
    public int getSendDate() {
        return sendDate;
    }

    public void setSendDate(int sendDate) {
        this.sendDate = sendDate;
    }

    @Column(name = "EmailFrom", unique = true, nullable = false)
    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    @Column(name = "EmailTo", unique = true, nullable = false)
    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    @Column(name = "EmailCc", unique = true, nullable = false)
    public String getEmailCc() {
        return emailCc;
    }

    public void setEmailCc(String emailCc) {
        this.emailCc = emailCc;
    }

    @Column(name = "EmailBcc", unique = true, nullable = false)
    public String getEmailBcc() {
        return emailBcc;
    }

    public void setEmailBcc(String emailBcc) {
        this.emailBcc = emailBcc;
    }

    @Column(name = "EmailSubject", unique = true, nullable = false)
    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    @Column(name = "EmailContent", unique = true, nullable = false)
    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    @Column(name = "AttachmentPath", unique = true, nullable = false)
    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    @Column(name = "AttachmentName", unique = true, nullable = false)
    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public EmailInfo(){

    }

    public EmailInfo(int registerDate, String emailFrom,String emailTo,String emailCc, String emailBcc , String emailSubject, String emailContent,String attachmentPath, String attachmentName){

        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
        this.emailCc = emailCc;
        this.emailBcc = emailBcc;
        this.emailSubject = emailSubject;
        this.emailContent = emailContent;
        this.attachmentPath = attachmentPath;
        this.attachmentName = attachmentName;
        this.registerDate = registerDate;
        this.sendDate = 0;

    }
}
