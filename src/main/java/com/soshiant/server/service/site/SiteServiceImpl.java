package com.soshiant.server.service.site;

import com.soshiant.common.util.CommonUtil;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.dao.site.SiteDao;
import com.soshiant.server.model.site.Site;
import com.soshiant.server.service.BusinessException;
import com.soshiant.server.service.parameters.ParametersService;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Hubert
 * Date: 10/6/11
 * Time: 11:19 AM
 */

@Transactional
public class SiteServiceImpl implements SiteService {

    private static Logger logger = Logger.getLogger(SiteServiceImpl.class);

    private SiteDao siteDao;

    private ParametersService parametersService;

    public void setSiteDao(SiteDao siteDao) {
        this.siteDao = siteDao;
    }

    public void setParametersService(ParametersService parametersService) {
        this.parametersService = parametersService;
    }

    //======================================================================================================================
    public List getSiteListByManagerId(int managerId) {

        List<Site> sites = siteDao.getSiteListByManagerId(managerId);
        return sites;
    }

    //======================================================================================================================
    public Site getSiteInfo(int siteId) {

        Site siteInfo = siteDao.getSiteInfo(siteId);
        return siteInfo;
    }

    //======================================================================================================================
    public List<Site> getSiteList(String searchField, String searchString){

        List<Site> siteList = null;

        switch (searchField){
            case ServerConstants.SITE_MANAGER_ID : siteList = siteDao.getSiteListByManagerId(Short.parseShort(searchString));
                                             break;
            case ServerConstants.SITE_NAME : siteList = siteDao.getSiteListBySiteName(searchString);
                                             break;
        }

        if(siteList == null || siteList.size() == 0){
            throw new BusinessException(ServerConstants.ERROR_DB_INFORMATION_NOT_FOUND);
        }

        return siteList;
    }
    //======================================================================================================================
    public int saveSiteInfo(Site site) {

        if(!site.isEditMode()) {

            String siteCounter =  parametersService.makeNewSiteCounter();
            if(siteCounter == null){
                throw new BusinessException(ServerConstants.ERROR_DB_CANNOT_FETCH_SYSTEM_PARAMETERS);
            }
            int siteId = Integer.parseInt(CommonUtil.nvl(siteCounter,"-1"));
            if(siteId == -1){
//                throw new BusinessException(ServerConstants.ERROR_DB_CANNOT_FETCH_SYSTEM_PARAMETERS);
                return ServerConstants.ERROR_DB_CANNOT_FETCH_SYSTEM_PARAMETERS;
            }
            site.setSiteId(siteId);
        }
        siteDao.saveSiteInfo(site);

        return site.getSiteId();
    }
    //======================================================================================================================
    public boolean saveSiteTelephoneNumber(int siteId,int telephoneType, String newTelephoneNumber) {

        switch (telephoneType) {
            case 1:
                siteDao.saveSiteTelephoneNumber1(siteId, newTelephoneNumber);
                break;
            case 2:
                siteDao.saveSiteTelephoneNumber2(siteId, newTelephoneNumber);
                break;
            case 3:
                siteDao.saveSiteTelephoneNumber3(siteId, newTelephoneNumber);
                break;
            case 4:
                siteDao.saveSiteFaxNumber(siteId, newTelephoneNumber);
                break;
        }
        return true;
    }

    //======================================================================================================================

}