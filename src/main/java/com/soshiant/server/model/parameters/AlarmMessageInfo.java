package com.soshiant.server.model.parameters;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 6:05 PM
 */

@Entity
@Table(name = "ALARMMESSAGEINFO", uniqueConstraints = {
        @UniqueConstraint(columnNames = "Id")})
public class AlarmMessageInfo implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int    id;

    private byte   sendStatus;
    private byte   messageType;
    private String messageText;


    @Column(name = "Id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "SendStatus", unique = true, nullable = false)
    public byte getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(byte sendStatus) {
        this.sendStatus = sendStatus;
    }

    @Column(name = "MessageType", unique = true, nullable = false)
    public byte getMessageType() {
        return messageType;
    }

    public void setMessageType(byte messageType) {
        this.messageType = messageType;
    }

    @Column(name = "MessageText", unique = true, nullable = false)
    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }



    public AlarmMessageInfo(){

    }

    public AlarmMessageInfo(byte messageType,  String messageText){

        this.messageText = messageText;
        this.messageType = messageType;
        this.sendStatus  = 0;

    }
}
