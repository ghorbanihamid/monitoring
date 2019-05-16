package com.soshiant.server.dao.site;


import com.soshiant.server.model.site.Site;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 10/6/11
 * Time: 11:21 AM
 */

public interface SiteDao {

    public List<Site> getSiteListByManagerId(int managerId);

    public List<Site> getSiteListBySiteName(String searchString);

    public Site getSiteInfo(int SiteId);

    public void saveSiteInfo(Site Site);

    public boolean saveSiteTelephoneNumber1(int SiteId, String newTelephoneNumber);

    public boolean saveSiteTelephoneNumber2(int SiteId, String newTelephoneNumber);

    public boolean saveSiteTelephoneNumber3(int SiteId, String newTelephoneNumber);

    public boolean saveSiteFaxNumber(int SiteId, String newFaxNumber);


}
