package com.soshiant.server.dao.parameters;

import com.soshiant.server.model.parameters.*;
import com.soshiant.server.model.staff.StaffShift;
import com.soshiant.server.model.staff.StaffShiftView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 4:31 PM
 */
public interface SmsDao {

    public List<SendSmsInfo> getSmsList();

    public List<ReceivedSmsInfo> getReceivedSmsList();

    public List<AlarmMessageInfo> getAlarmsList();

    public List<StaffShiftView> getShiftListForAlarm(int shiftDate,int currentTime);

    public void saveAlarmInfo(AlarmMessageInfo alarmMessageInfo);

    public void saveSmsInfo(SendSmsInfo smsInfo);

    public void saveReceivedSmsInfo(ReceivedSmsInfo receivedSmsInfo);

    public void markSmsListAsSent(List<SendSmsInfo> smsInfoList,int sendDate);

    public void markAlarmListAsSent(List<AlarmMessageInfo> smsInfoList,int sendDate);

    public void markReceivedSmsAsProcessed(ReceivedSmsInfo receivedSms,int processDate);

    public void markReceivedSmsListAsProcessed(List<ReceivedSmsInfo> receivedSmsList,int processDate);

}
