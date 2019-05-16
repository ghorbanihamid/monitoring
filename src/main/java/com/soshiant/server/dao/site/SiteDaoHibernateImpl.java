package com.soshiant.server.dao.site;

import com.soshiant.common.dateTime.DateTimeUtil;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.model.site.Site;
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
public class SiteDaoHibernateImpl extends HibernateDaoSupport implements SiteDao {

    private final static Logger logger = Logger.getLogger(SiteDaoHibernateImpl.class);

    //======================================================================================================================
    public List<Site> getSiteListByManagerId(int managerId) {

        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Site.class);
        criteria.add(Restrictions.eq(ServerConstants.SITE_MANAGER_ID, managerId));
        return  (List<Site>) criteria.list();
    }
    //======================================================================================================================
    public List<Site> getSiteListBySiteName(String searchString) {

        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Site.class);
        criteria.add(Restrictions.eq(ServerConstants.SITE_NAME, searchString));
        return  (List<Site>) criteria.list();
    }
    //======================================================================================================================
    public Site getSiteInfo(int SiteId) {

        return getHibernateTemplate().get(Site.class, SiteId);
    }
    //======================================================================================================================
    public void saveSiteInfo(Site Site) {

        getHibernateTemplate().saveOrUpdate(Site);
    }

    //======================================================================================================================
    public boolean saveSiteTelephoneNumber1(int SiteId, String newTelephoneNumber) {

        String qryStr = "UPDATE Site set siteTelNo1 = '"+ newTelephoneNumber +"' WHERE SiteId = "+ SiteId;
        getHibernateTemplate().bulkUpdate(qryStr);
        return true;

    }
    //======================================================================================================================
    public boolean saveSiteTelephoneNumber2(int SiteId, String newTelephoneNumber) {

        String qryStr = "UPDATE Site set siteTelNo2 = '"+ newTelephoneNumber +"' WHERE SiteId = "+ SiteId;
        getHibernateTemplate().bulkUpdate(qryStr);
        return true;

    }
    //======================================================================================================================
    public boolean saveSiteTelephoneNumber3(int SiteId, String newTelephoneNumber) {

        String qryStr = "UPDATE Site set siteTelNo3 = '"+ newTelephoneNumber +"' WHERE SiteId = "+ SiteId;
        getHibernateTemplate().bulkUpdate(qryStr);
        return true;

    }
    //======================================================================================================================

    public boolean saveSiteFaxNumber(int SiteId, String newFaxNumber) {

        String qryStr = "UPDATE Site set siteFax = '"+ newFaxNumber +"' WHERE SiteId = "+ SiteId;
        getHibernateTemplate().bulkUpdate(qryStr);
        return true;

    }
    //======================================================================================================================

}
