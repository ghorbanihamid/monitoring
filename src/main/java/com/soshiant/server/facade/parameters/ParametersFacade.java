package com.soshiant.server.facade.parameters;


import com.soshiant.server.facade.Facade;
import com.soshiant.server.facade.FacadeResult;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public interface ParametersFacade extends Facade {

    public enum DataKeys {
    }


    public FacadeResult getBanksList();

    public FacadeResult getPositionsList();

    public FacadeResult getBankInfo(byte bankCode);

    public FacadeResult getCitiesList();

    public FacadeResult getCityInfo(int cityCode);

    public FacadeResult getRmfMonitoringServiceStatus();

    public FacadeResult getSaveRmfMonitoringDataServiceStatus();

    public FacadeResult getMainFrameServersList();

    public FacadeResult getChartRefreshTime();

    public FacadeResult getSendSmsServiceStatus();

    public FacadeResult getReceiveSmsServiceStatus();

    public FacadeResult checkForStartingSmsAndEmailServices();


}
