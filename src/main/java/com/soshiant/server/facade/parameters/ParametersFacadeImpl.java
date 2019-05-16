package com.soshiant.server.facade.parameters;

import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.SimpleFacadeResult;
import com.soshiant.server.model.parameters.*;
import com.soshiant.server.model.position.Positions;
import com.soshiant.server.model.server.Server;
import com.soshiant.server.service.parameters.ParametersService;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

@Transactional(readOnly = true)
public class ParametersFacadeImpl implements ParametersFacade {

    private ParametersService parametersService;

    public void setParametersService(ParametersService parametersService) {
        this.parametersService = parametersService;
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getPositionsList() {

        List<Positions> tmpPositionsList = this.parametersService.getPositionsList();
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(tmpPositionsList);
        return simpleFacadeResult.success();
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getBanksList() {

        List<Bank> tmpBankList = this.parametersService.getBanksList();
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(tmpBankList);
        return simpleFacadeResult.success();
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getBankInfo(byte bankCode) {

        Bank tmpBankInfo = this.parametersService.getBankInfo(bankCode);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(tmpBankInfo);
        return simpleFacadeResult.success();
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getCitiesList() {

        List<City> tmpCitiesList = this.parametersService.getCitiesList();
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(tmpCitiesList);
        return simpleFacadeResult.success();
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getCityInfo(int cityCode) {

        City tmpCityInfo = this.parametersService.getCityInfo(cityCode);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(tmpCityInfo);
        return simpleFacadeResult.success();
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getRmfMonitoringServiceStatus() {
        boolean rmfMonitoringStatus = this.parametersService.getRmfMonitoringServiceStatus();
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(rmfMonitoringStatus);
        return simpleFacadeResult.success();
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getSaveRmfMonitoringDataServiceStatus() {
        boolean saveRmfMonitoringDataServiceStatus = this.parametersService.getSaveRmfMonitoringDataServiceStatus();
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(saveRmfMonitoringDataServiceStatus);
        return simpleFacadeResult.success();
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getMainFrameServersList() {
        List<Server> mainFrameServersList = this.parametersService.getMainFrameServersList();
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(mainFrameServersList);
        return simpleFacadeResult.success();
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getChartRefreshTime() {
        int refreshTime = this.parametersService.getChartRefreshTime();
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(refreshTime);
        return simpleFacadeResult.success();
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getSendSmsServiceStatus() {
        boolean sendSmsServiceStatus = this.parametersService.getSendSmsServiceStatus();
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(sendSmsServiceStatus);
        return simpleFacadeResult.success();
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getReceiveSmsServiceStatus() {
        boolean sendSmsServiceStatus = this.parametersService.getSendSmsServiceStatus();
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(sendSmsServiceStatus);
        return simpleFacadeResult.success();
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult checkForStartingSmsAndEmailServices() {
        this.parametersService.getSendSmsServiceStatus();
        this.parametersService.getReceiveSmsServiceStatus();
        this.parametersService.getSendEmailServiceStatus();
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(null);
        return simpleFacadeResult.success();
    }
    //======================================================================================================================
    //@Override
    public Class getKeysEnumClass() {
        return DataKeys.class;
    }
}
