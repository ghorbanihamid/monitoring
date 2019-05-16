package com.soshiant.server.periodictasks;

import com.soshiant.server.constants.ServiceConstants;
import com.soshiant.server.facade.parameters.SmsFacade;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: hubert
 * Date: 3/27/12
 * Time: 11:40 PM
 */

public final class SendSmsTask {


    private SmsFacade smsFacade;

    public void setSmsFacade(SmsFacade smsFacade) {
        this.smsFacade = smsFacade;
    }
//======================================================================================================================

    public void sendMultipleMessages(){

        if(ServiceConstants.isSendSmsEnabled())
            smsFacade.sendMultipleSMSMessage();
    }

//======================================================================================================================

}
