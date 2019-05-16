package com.soshiant.server.dao.position;

import com.soshiant.server.dao.DaoException;
import com.soshiant.server.model.position.Positions;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * UserInfo: Mahta
 * Date: 10/6/11
 * Time: 11:21 AM
 */
public class PositionDaoHibernateImpl extends HibernateDaoSupport implements PositionDao {
    private final static Logger logger = Logger.getLogger(PositionDaoHibernateImpl.class);
    private final static String ID = "id";
    private final static String POSITION_KEY = "positionKey";

    public List<Positions> findAll() {
        logger.debug("Find all roles.");
        try {
            return (List<Positions>) getHibernateTemplate().getSessionFactory().getCurrentSession()
                    .createCriteria(Positions.class)
                    .list();

        } catch (HibernateException e) {
            throw new DaoException("", 0, 0);
        }

    }

//    public List<Positions> findAllExceptTeacher() {
//        logger.debug("Find all roles.");
//        try {
//            return (List<Positions>) getHibernateTemplate().getSessionFactory().getCurrentSession()
//                    .createCriteria(Positions.class)
//                    .add(Restrictions.ne(POSITION_KEY, Positions.PositionKey.TEACHER))
//                    .list();
//
//        } catch (HibernateException e) {
//            throw new DaoException("", 0, 0);
//        }
//
//    }

    public Positions findPositionById(int positionId) {
        logger.debug("Find Positions by id: " + positionId);
        try {
            return (Positions) getHibernateTemplate().getSessionFactory().getCurrentSession()
                    .createCriteria(Positions.class)
                    .add(Restrictions.eq(ID, positionId))
                    .setMaxResults(1)
                    .uniqueResult();

        } catch (HibernateException e) {
            throw new DaoException("", 0, 0);
        }

    }

//    public Positions findPositionByKey(Positions.PositionKey positionKey) {
//        logger.debug("Find Positions by PositionKey: " + positionKey.name());
//        try {
//            return (Positions) getHibernateTemplate().getSessionFactory().getCurrentSession()
//                    .createCriteria(Positions.class)
//                    .add(Restrictions.eq(POSITION_KEY, positionKey))
//                    .setMaxResults(1)
//                    .uniqueResult();
//
//        } catch (HibernateException e) {
//            throw new DaoException("", 0, 0);
//        }
//    }

    public void updatePosition(Positions position) {
        logger.debug("update Positions with id: " + position.getPositionId());
        try {
            getHibernateTemplate().update(position);

        } catch (HibernateException e) {
            throw new DaoException("", 0, 0);
        }
    }

    public void savePosition(Positions position) {
        logger.debug("Save new Positions with name: " + position.getPositionEnName());
        try {
            getHibernateTemplate().save(position);

        } catch (HibernateException e) {
            throw new DaoException("", 0, 0);
        }
    }

}
