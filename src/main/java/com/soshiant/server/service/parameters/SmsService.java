package com.soshiant.server.service.parameters;


import com.soshiant.server.model.parameters.AlarmMessageInfo;
import com.soshiant.server.model.parameters.SendSmsInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public interface SmsService {

    public List<SendSmsInfo> getSmsList();

    public List<AlarmMessageInfo> getAlarmsList();

    public void saveAlarmInfo(AlarmMessageInfo alarmMessageInfo);

    public void saveSmsInfo(SendSmsInfo smsInfo);

    public short sendSMSMessage(String cellPhone , String messageText);

    public short sendAlarmMessage();

    public short sendMultipleSMSMessage();

    public short receiveSMS();

    public short processReceiveSMSMessages();
}
