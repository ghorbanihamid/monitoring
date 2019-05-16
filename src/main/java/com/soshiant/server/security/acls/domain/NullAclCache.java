package com.soshiant.server.security.acls.domain;

import org.springframework.security.acls.model.AclCache;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.ObjectIdentity;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * UserInfo: hubert
 * Date: 10/12/11
 * Time: 9:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class NullAclCache implements AclCache {
    //@Override
    public void evictFromCache(Serializable pk) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    //@Override
    public void evictFromCache(ObjectIdentity objectIdentity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    //@Override
    public MutableAcl getFromCache(ObjectIdentity objectIdentity) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    //@Override
    public MutableAcl getFromCache(Serializable pk) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    //@Override
    public void putInCache(MutableAcl acl) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    //@Override
    public void clearCache() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
