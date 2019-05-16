package com.soshiant.server.dao.user;

import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.model.user.SecureUser;
import com.soshiant.server.model.user.UserInfo;
import com.soshiant.server.model.user.UserPermissions;
import com.soshiant.server.model.user.UserPermissionsView;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

@Repository
public class UserDaoHibernateImpl extends HibernateDaoSupport implements UserDao {


    @Autowired
    private PasswordEncoder passwordEncoder;

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private SaltSource saltSource;

    public void setSaltSource(SaltSource saltSource) {
        this.saltSource = saltSource;
    }

    //======================================================================================================================
    public List<UserInfo> getUserInfoList() {

        return (List<UserInfo>) getHibernateTemplate().loadAll(UserInfo.class);
    }
//======================================================================================================================
    public UserInfo getUserInfo(String userName) {

        return getHibernateTemplate().get(UserInfo.class, userName);
    }
//======================================================================================================================
    public SecureUser getSecuredUserInfo(String userName) {

        UserInfo userInfo = getHibernateTemplate().get(UserInfo.class, userName);
        if(userInfo != null) {
        return new SecureUser(userInfo.getUsername(),
                              userInfo.getPassword().toLowerCase(),
                              userInfo.isEnabled(),
                              true,
                              true,
                              true,
                              getAuthorities(1),
                              userInfo.getSalt()
                             );
        }
        return null;
    }
//======================================================================================================================
    public UserInfo getUserInfo(int userId) {

        return (UserInfo) getHibernateTemplate().getSessionFactory().getCurrentSession()
                                                .createCriteria(UserInfo.class)
                                                .add(Restrictions.eq(ServerConstants.USER_ID, userId))
                                                .setMaxResults(1)
                                                .uniqueResult();

    }
//======================================================================================================================
    public boolean saveNewUserName(String currentUserName, String newUserName) {

        String qryStr = "UPDATE UserInfo set username = '"+ newUserName +"' WHERE username = '"+ currentUserName +"'";
        getHibernateTemplate().bulkUpdate(qryStr);
        return true;

    }
//======================================================================================================================
    public boolean saveNewPassword(int passChangeDate, String userName, String newEncodedPassword) {

        String qryStr = String.format("UPDATE UserInfo set password = '%s' , passChgDate = %s WHERE username = '%s'", newEncodedPassword,passChangeDate, userName);
        getHibernateTemplate().bulkUpdate(qryStr);
        return true;
    }
//======================================================================================================================
    public boolean resetUserPassword(String userName) {

        String encodedPassword = passwordEncoder.encodePassword(ServerConstants.DEFAULT_PASSWORD, null);

        String qryStr = String.format("UPDATE UserInfo set password = '%s' , passChgDate = 0 WHERE username = '%s'", encodedPassword, userName);
        getHibernateTemplate().bulkUpdate(qryStr);
        return true;
    }
//======================================================================================================================
    public void deleteUser(String userName) {

        getHibernateTemplate().delete(getUserInfo(userName));
    }
//======================================================================================================================
    public boolean saveNewUser(UserInfo userInfo) {

        getHibernateTemplate().saveOrUpdate(userInfo);
        return true;
    }

    //======================================================================================================================
    public Collection<GrantedAuthority> getAuthorities(Integer access) {
       // Create a list of grants for this user
       List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

       // All users are granted with ROLE_USER access
       // Therefore this user gets a ROLE_USER by default
       logger.debug("Grant ROLE_USER to this user");
       authList.add(new GrantedAuthorityImpl("ROLE_USER"));

       // Check if this user has admin access
       // We interpret Integer(1) as an admin user
       if ( access.compareTo(1) == 0) {
        // User has admin access
        logger.debug("Grant ROLE_ADMIN to this user");
        authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
       }

       // Return list of granted authorities
       return authList;

    }

    //======================================================================================================================
    public List<UserPermissionsView> getUserPermissions(int userId, int todayDate) {

        try {
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            Criteria criteria = session.createCriteria(UserPermissionsView.class);
            criteria.add(Restrictions.eq(ServerConstants.USER_ID, userId));
            criteria.add(Restrictions.eq(ServerConstants.SERVER_STATUS, ServerConstants.SERVER_STATUS_ACTIVE));
            criteria.add(Restrictions.le(ServerConstants.USER_PERMISSION_START_DATE, todayDate));
            criteria.add(Restrictions.ge(ServerConstants.USER_PERMISSION_END_DATE, todayDate));
            return  (List<UserPermissionsView>) criteria.list();
        }
        catch (Exception e){
            logger.error(" getUserPermissions() : " + e.getMessage());
            e.printStackTrace();
            throw e;
        }

    }
    //======================================================================================================================

}
