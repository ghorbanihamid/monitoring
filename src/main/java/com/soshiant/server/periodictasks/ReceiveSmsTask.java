package com.soshiant.server.periodictasks;

import com.soshiant.server.constants.ServiceConstants;
import com.soshiant.server.facade.parameters.SmsFacade;

/**
 * Created with IntelliJ IDEA.
 * User: hubert
 * Date: 3/27/12
 * Time: 11:40 PM
 */

public final class ReceiveSmsTask {


    private SmsFacade smsFacade;

    public void setSmsFacade(SmsFacade smsFacade) {
        this.smsFacade = smsFacade;
    }

   //======================================================================================================================
    public void receiveSMSMessages(){

        if(ServiceConstants.isReceiveSmsEnabled())
            smsFacade.receiveSMSMessage();
    }

    //======================================================================================================================
    public void processReceivedSMSMessages(){

        if(ServiceConstants.isReceiveSmsEnabled())
            smsFacade.processReceiveSMSMessages();
    }

    //======================================================================================================================

}
