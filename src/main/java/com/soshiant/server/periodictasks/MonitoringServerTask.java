package com.soshiant.server.periodictasks;

import com.soshiant.server.facade.monitoring.MonitoringFacade;

/**
 * Created with IntelliJ IDEA.
 * User: hamid
 * Date: 3/27/12
 * Time: 11:40 PM
 */


public final class MonitoringServerTask {


    private MonitoringFacade monitoringFacade;

    public void setMonitoringFacade(MonitoringFacade monitoringFacade) {

        this.monitoringFacade = monitoringFacade;
    }

    //======================================================================================================================

    public void getMonitoringDataFromStub(){

        monitoringFacade.createRMFMonitoringThreads();
    }

    //======================================================================================================================

}
