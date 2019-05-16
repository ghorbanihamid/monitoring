package com.soshiant.server.periodictasks;

import com.soshiant.server.constants.ServiceConstants;
import com.soshiant.server.facade.parameters.email.EmailFacade;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: hamid
 * Date: 3/27/12
 * Time: 11:40 PM
 */


public final class SendEmailTask {


    private EmailFacade emailFacade;

    public void setEmailFacade(EmailFacade emailFacade) {
        this.emailFacade = emailFacade;
    }

    //======================================================================================================================
    public void sendMultipleEmail(){

        if(ServiceConstants.isSendEmailEnabled())
            emailFacade.sendMultipleEmail();
    }
    //======================================================================================================================

}
