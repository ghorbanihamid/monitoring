package com.soshiant.server.periodictasks;

import com.soshiant.server.constants.ActionConstants;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.constants.ServiceConstants;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.log.AppLogFacade;
import com.soshiant.server.facade.parameters.SmsFacade;
import com.soshiant.server.facade.parameters.email.EmailFacade;
import com.soshiant.server.model.parameters.AlarmMessageInfo;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hubert
 * Date: 3/27/12
 * Time: 11:40 PM
 */

public final class SendAlarmTask {

    Logger log = Logger.getLogger(SendAlarmTask.class);

    private String actionName;
    private String logMessage = "";


    private SmsFacade smsFacade;
    private EmailFacade emailFacade;
    private AppLogFacade appLogFacade;

    public void setSmsFacade(SmsFacade smsFacade) {
        this.smsFacade = smsFacade;
    }

    public void setEmailFacade(EmailFacade emailFacade) {
        this.emailFacade = emailFacade;
    }

    public void setAppLogFacade(AppLogFacade appLogFacade) {
        this.appLogFacade = appLogFacade;
    }

    //======================================================================================================================

    public void sendAlarmMessages(){

        smsFacade.sendAlarmMessage();
    }

//======================================================================================================================

}
