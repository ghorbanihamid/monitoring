package com.soshiant.server.dao.usergroup;

import com.soshiant.server.dao.DaoException;
import com.soshiant.server.dao.user.UserDaoHibernateImpl;
import com.soshiant.server.model.user.UserGroups;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * UserInfo: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */
public class UserGroupDaoHibernateImpl extends HibernateDaoSupport implements UserGroupDao {

    private final static String GROUP = "group";
    private final static String USER = "user";

    public List<UserGroups> getUserGroupByUser(int userId) throws DaoException {

        try {

            return (List<UserGroups>) getHibernateTemplate().getSessionFactory().getCurrentSession()
                    .createCriteria(UserGroups.class)
                    .createAlias(USER, "user")
                    .add(Restrictions.eq("user." , userId))
                    .setProjection(Projections.property(GROUP))
                    .list();

        } catch (HibernateException e) {

            throw new DaoException("", 0, 0);
        }
    }

}
