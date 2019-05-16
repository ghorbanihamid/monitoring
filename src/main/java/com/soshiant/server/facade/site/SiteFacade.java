package com.soshiant.server.facade.site;


import com.soshiant.server.facade.Facade;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.model.site.Site;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 10/6/11
 * Time: 11:19 AM
 */

public interface SiteFacade extends Facade {

    public FacadeResult getSiteListByManagerId(short managerId);

    public FacadeResult getSiteInfo(int staffId);

    public FacadeResult getSiteList(String searchField, String searchString);

    public FacadeResult saveSiteInfo(Site site);

    public FacadeResult saveSiteTelephoneNumber(int siteId,int telephoneType, String newTelephoneNumber);

    public enum DataKeys {
    }

}
