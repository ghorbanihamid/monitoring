package com.soshiant.server.dao.email;

import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.model.parameters.EmailInfo;
import com.soshiant.server.model.parameters.SendSmsInfo;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class EmailDaoHibernateImpl extends HibernateDaoSupport implements EmailDao {


    public List<EmailInfo> getEmailList() {

        int zeroSendDate = 0;
        return (List<EmailInfo>) getHibernateTemplate().getSessionFactory().getCurrentSession()
                                                               .createCriteria(SendSmsInfo.class)
                                                               .add(Restrictions.eq(ServerConstants.SEND_DATE, zeroSendDate))
                                                               .list();
    }
//======================================================================================================================
    public void saveEmailInfo(EmailInfo emailInfo) {

        getHibernateTemplate().saveOrUpdate(emailInfo);
    }

//======================================================================================================================
    public void markEmailListAsSent(List<EmailInfo> emailInfoList,int sendDate) {

        if(emailInfoList != null && !emailInfoList.isEmpty()){
            EmailInfo emailInfo;
            for(int i = 0; i < emailInfoList.size();i++){
                emailInfo = emailInfoList.get(i);
                String qryStr = new StringBuilder().append(" UPDATE EmailInfo set sendDate = ").append(sendDate).append(" WHERE  Id = ").append(emailInfo.getId()).toString();
                getHibernateTemplate().bulkUpdate(qryStr);
            }
        }
    }
//======================================================================================================================

}
