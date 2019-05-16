package com.soshiant.server.facade.site;

import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.SimpleFacadeResult;
import com.soshiant.server.model.site.Site;
import com.soshiant.server.service.parameters.ParametersService;
import com.soshiant.server.service.site.SiteService;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Hubert
 * Date: 10/6/11
 * Time: 11:19 AM
 */

public class SiteFacadeImpl implements SiteFacade {

    private static Logger logger = Logger.getLogger(SiteFacadeImpl.class);

    private SiteService siteService;
    private ParametersService parametersService;

    public void setSiteService(SiteService siteService) {
        this.siteService = siteService;
    }

    public void setParametersService(ParametersService parametersService) {
        this.parametersService = parametersService;
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getSiteListByManagerId(short managerId) {

        List<Site> sites = siteService.getSiteListByManagerId(managerId);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(sites);
        return (simpleFacadeResult.success());
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getSiteInfo(int siteId) {

        Site siteInfo = siteService.getSiteInfo(siteId);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(siteInfo);
        return (simpleFacadeResult.success());
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getSiteList(String searchField, String searchString){

        List<Site> sites = siteService.getSiteList(searchField,searchString);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(sites);
        return (simpleFacadeResult.success());
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult saveSiteInfo(Site site) {

        int siteId = siteService.saveSiteInfo(site);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(siteId);
        return simpleFacadeResult.success();
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult saveSiteTelephoneNumber(int siteId,int telephoneType, String newTelephoneNumber) {

        siteService.saveSiteTelephoneNumber(siteId,telephoneType, newTelephoneNumber);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult();
        return simpleFacadeResult.success();
    }
//======================================================================================================================
    //@Override
    public Class getKeysEnumClass() {
        return DataKeys.class;
    }


}