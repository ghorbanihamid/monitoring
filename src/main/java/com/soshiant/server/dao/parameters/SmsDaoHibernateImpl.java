package com.soshiant.server.dao.parameters;

import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.model.parameters.*;
import com.soshiant.server.model.staff.StaffShift;
import com.soshiant.server.model.staff.StaffShiftView;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

@Repository
public class SmsDaoHibernateImpl extends HibernateDaoSupport implements SmsDao {


    //======================================================================================================================
    public List<SendSmsInfo> getSmsList() {

        int zeroSendDate = 0;
        return (List<SendSmsInfo>) getHibernateTemplate().getSessionFactory().getCurrentSession()
                                                               .createCriteria(SendSmsInfo.class)
                                                               .add(Restrictions.eq(ServerConstants.SEND_DATE, zeroSendDate))
                                                               .list();
    }
    //======================================================================================================================
    public List<AlarmMessageInfo> getAlarmsList() {

        try {
            int notSendStatus = 0;
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            Criteria criteria = session.createCriteria(AlarmMessageInfo.class);
            criteria.add(Restrictions.eq(ServerConstants.SEND_STATUS, notSendStatus));
            return  (List<AlarmMessageInfo>) criteria.list();
        }
        catch (Exception e){
            logger.error(" getAlarmsList() : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    //======================================================================================================================
    public List<StaffShiftView> getShiftListForAlarm(int shiftDate,int currentTime) {

        try {
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            Criteria criteria = session.createCriteria(StaffShiftView.class);
            criteria.add(Restrictions.eq(ServerConstants.SHIFT_DATE, shiftDate));
            criteria.add(Restrictions.ge(ServerConstants.SHIFT_START_TIME, currentTime));
            criteria.add(Restrictions.le(ServerConstants.SHIFT_END_TIME, currentTime));
            return  (List<StaffShiftView>) criteria.list();
        }
        catch (Exception e){
            logger.error(" getShiftList() : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    //======================================================================================================================
    public List<ReceivedSmsInfo> getReceivedSmsList() {

        int zeroProcessDate = 0;
        return (List<ReceivedSmsInfo>) getHibernateTemplate().getSessionFactory().getCurrentSession()
                                                               .createCriteria(ReceivedSmsInfo.class)
                                                               .add(Restrictions.eq(ServerConstants.PERSIAN_TRANS_DATE, zeroProcessDate))
                                                               .list();
    }
    //======================================================================================================================
    public void saveSmsInfo(SendSmsInfo smsInfo) {

        getHibernateTemplate().save(smsInfo);
    }

    //======================================================================================================================
    public void saveAlarmInfo(AlarmMessageInfo alarmMessageInfo) {

        try {
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            session.save(alarmMessageInfo);
        }
        catch (Exception e){
            logger.error(" saveAlarmInfo() : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }

    }

    //======================================================================================================================
    public void saveReceivedSmsInfo(ReceivedSmsInfo receivedSmsInfo) {

        getHibernateTemplate().save(receivedSmsInfo);
    }
    //======================================================================================================================
    public void markSmsListAsSent(List<SendSmsInfo> smsInfoList,int sendDate) {

        if(smsInfoList != null && !smsInfoList.isEmpty()){
            SendSmsInfo smsInfo;
            for(int i = 0; i < smsInfoList.size();i++){
                smsInfo = smsInfoList.get(i);
                String qryStr = new StringBuilder().append(" UPDATE SmsInfo set sendDate = ").append(sendDate).append(" WHERE  Id = ").append(smsInfo.getId()).toString();
                getHibernateTemplate().bulkUpdate(qryStr);
            }
        }
    }
    //======================================================================================================================
    public void markAlarmListAsSent(List<AlarmMessageInfo> alarmMessageInfoList,int sendDate) {

        if(alarmMessageInfoList != null && !alarmMessageInfoList.isEmpty()){
            AlarmMessageInfo alarmMessageInfo;
            for(int i = 0; i < alarmMessageInfoList.size();i++){
                alarmMessageInfo = alarmMessageInfoList.get(i);
                String qryStr = new StringBuilder().append(" UPDATE AlarmMessageInfo set sendStatus = 1").append(" WHERE  Id = ").append(alarmMessageInfo.getId()).toString();
                getHibernateTemplate().bulkUpdate(qryStr);
            }
        }
    }
    //======================================================================================================================
    public void markReceivedSmsAsProcessed(ReceivedSmsInfo receivedSms,int processDate) {

        String qryStr = String.format("UPDATE ReceivedSmsInfo set processStatus = 1, processDate = %s WHERE Id = %s", processDate,receivedSms.getId());
        getHibernateTemplate().bulkUpdate(qryStr);
    }
    //======================================================================================================================
    public void markReceivedSmsListAsProcessed(List<ReceivedSmsInfo> receivedSmsList,int processDate) {

        if(receivedSmsList != null && !receivedSmsList.isEmpty()){
            for (ReceivedSmsInfo receivedSmsInfo : receivedSmsList) {
                String qryStr = String.format("UPDATE ReceivedSmsInfo set processStatus = 1, processDate = %s WHERE Id = %s", processDate,receivedSmsInfo.getId());
                getHibernateTemplate().bulkUpdate(qryStr);
            }
        }
    }
    //======================================================================================================================

}
