package com.soshiant.server.dao.group;

import com.soshiant.server.dao.DaoException;
import com.soshiant.server.model.group.Groups;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */
public class GroupDaoHibernateImpl extends HibernateDaoSupport implements GroupDao {
    private Logger logger = Logger.getLogger(GroupDaoHibernateImpl.class);
    public final static String ID = "userId";

    public List<Groups> findAll() throws DaoException {
        logger.debug("finding all Group instances");

        try {
            return (List<Groups>) getHibernateTemplate().getSessionFactory().getCurrentSession()
                    .createCriteria(Groups.class)
                    .list();

        } catch (HibernateException e) {

            throw new DaoException("", 0, 0);
        }
    }

}