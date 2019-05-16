package com.soshiant.server.model.parameters;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 6:05 PM
 */

@Entity
@Table(name = "RECEIVEDSMSINFO", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID")})
public class ReceivedSmsInfo implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int    id;

    private boolean  processStatus;

    private int      registerDate;
    private int      processDate;
    private long     smsId;
    private String   fromCellphone;
    private String   toNumber;
    private String   messageText;
    private String   receiveTime;


    @Column(name = "Id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "ProcessStatus", unique = true, nullable = false)
    public boolean isProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(boolean processStatus) {
        this.processStatus = processStatus;
    }

    @Column(name = "RegisterDate", unique = true, nullable = false)
    public int getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(int registerDate) {
        this.registerDate = registerDate;
    }

    @Column(name = "ProcessDate", unique = true, nullable = false)
    public int getProcessDate() {
        return processDate;
    }

    public void setProcessDate(int processDate) {
        this.processDate = processDate;
    }

    @Column(name = "SmsId", unique = true, nullable = false)
    public long getSmsId() {
        return smsId;
    }

    public void setSmsId(long smsId) {
        this.smsId = smsId;
    }

    @Column(name = "FromCellphone", unique = true, nullable = false)
    public String getFromCellphone() {
        return fromCellphone;
    }

    public void setFromCellphone(String fromCellphone) {
        this.fromCellphone = fromCellphone;
    }

    @Column(name = "ToNumber", unique = true, nullable = false)
    public String getToNumber() {
        return toNumber;
    }

    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }

    @Column(name = "MessageText", unique = true, nullable = false)
    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    @Column(name = "ReceiveTime", unique = true, nullable = false)
    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public ReceivedSmsInfo(){

    }

    public ReceivedSmsInfo(int registerDate, String fromCellphone, String messageText,String receiveTime){

        this.fromCellphone = fromCellphone;
        this.messageText = messageText;
        this.registerDate = registerDate;
        this.receiveTime = receiveTime;

    }
}
