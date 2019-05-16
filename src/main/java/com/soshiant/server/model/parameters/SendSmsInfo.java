package com.soshiant.server.model.parameters;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 6:05 PM
 */

@Entity
@Table(name = "SENDSMSINFO", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID")})
public class SendSmsInfo implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int    id;

    private int    registerDate;
    private int    sendDate;
    private String cellphone;
    private String messageText;


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

    @Column(name = "sendDate", unique = true, nullable = false)
    public int getSendDate() {
        return sendDate;
    }

    public void setSendDate(int sendDate) {
        this.sendDate = sendDate;
    }

    @Column(name = "Cellphone", unique = true, nullable = false)
    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    @Column(name = "MessageText", unique = true, nullable = false)
    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }



    public SendSmsInfo(){

    }

    public SendSmsInfo(int registerDate, String cellphone, String messageText){

        this.cellphone = cellphone;
        this.messageText = messageText;
        this.registerDate = registerDate;
        this.sendDate = 0;

    }
}
