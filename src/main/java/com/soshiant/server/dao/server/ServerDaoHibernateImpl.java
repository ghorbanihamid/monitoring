package com.soshiant.server.dao.server;

import com.soshiant.common.dateTime.DateTimeUtil;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.model.server.RmfConfig;
import com.soshiant.server.model.server.Server;
import com.soshiant.server.model.user.UserServers;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

@Repository
public class ServerDaoHibernateImpl extends HibernateDaoSupport implements ServerDao {

    private final static Logger logger = Logger.getLogger(ServerDaoHibernateImpl.class);

    //======================================================================================================================
    public Server getServerInfo(int ServerId) {

        return getHibernateTemplate().get(Server.class, ServerId);
    }
    //======================================================================================================================
    public RmfConfig getRmfConfigInfo(int rmfConfigId) {

        return getHibernateTemplate().get(RmfConfig.class, rmfConfigId);
    }
    //======================================================================================================================
    public List<Server> getServerListByManagerId(int managerId) {

        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Server.class);
        criteria.add(Restrictions.eq(ServerConstants.SITE_MANAGER_ID, managerId));
        return  (List<Server>) criteria.list();
    }
    //======================================================================================================================
    public List<Server> getServerListByServerName(String searchString) {

        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Server.class);
        criteria.add(Restrictions.eq(ServerConstants.SITE_NAME, searchString));
        return  (List<Server>) criteria.list();
    }
    //======================================================================================================================
    public List<UserServers> getMonitorableServersOfUser(int userId) {

        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(UserServers.class);
        criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
        return  (List<UserServers>) criteria.list();
    }
    //======================================================================================================================
    public void saveServerInfo(Server Server) {

        getHibernateTemplate().saveOrUpdate(Server);
    }

    //======================================================================================================================
    public void saveRmfConfigInfo(RmfConfig rmfConfig) {

        getHibernateTemplate().saveOrUpdate(rmfConfig);
    }

    //======================================================================================================================
    public boolean saveServerTelephoneNumber1(int ServerId, String newTelephoneNumber) {

        String qryStr = "UPDATE Server set serverIp = '"+ newTelephoneNumber +"' WHERE serverIp = "+ ServerId;
        getHibernateTemplate().bulkUpdate(qryStr);
        return true;

    }
    //======================================================================================================================
    public boolean saveServerTelephoneNumber2(int ServerId, String newTelephoneNumber) {

        String qryStr = "UPDATE Server set serverIp = '"+ newTelephoneNumber +"' WHERE serverIp = "+ ServerId;
        getHibernateTemplate().bulkUpdate(qryStr);
        return true;

    }
    //======================================================================================================================
    public boolean saveServerTelephoneNumber3(int ServerId, String newTelephoneNumber) {

        String qryStr = "UPDATE Server set serverIp = '"+ newTelephoneNumber +"' WHERE serverIp = "+ ServerId;
        getHibernateTemplate().bulkUpdate(qryStr);
        return true;

    }
    //======================================================================================================================

    public boolean saveServerFaxNumber(int ServerId, String newFaxNumber) {

        String qryStr = "UPDATE Server set serverIp = '"+ newFaxNumber +"' WHERE serverIp = "+ ServerId;
        getHibernateTemplate().bulkUpdate(qryStr);
        return true;

    }
    //======================================================================================================================

}
