package com.soshiant.server.service.site;

import com.soshiant.server.model.site.Site;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 10/6/11
 * Time: 11:19 AM
 */

public interface SiteService {

    public List getSiteListByManagerId(int managerId);

    public Site getSiteInfo(int staffId);

    public List<Site> getSiteList(String searchField, String searchString);

    public int saveSiteInfo(Site staff);

    public boolean saveSiteTelephoneNumber(int siteId,int telephoneType, String newTelephoneNumber);

}
