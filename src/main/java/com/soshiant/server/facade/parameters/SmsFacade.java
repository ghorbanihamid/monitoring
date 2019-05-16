package com.soshiant.server.facade.parameters;


import com.soshiant.server.facade.Facade;
import com.soshiant.server.facade.FacadeResult;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public interface SmsFacade extends Facade {

    public enum DataKeys {
    }

    public FacadeResult getAlarmList();

    public FacadeResult getSmsList();

    public FacadeResult sendSMSMessage(String cellPhone , String messageText);

    public FacadeResult sendAlarmMessage();

    public FacadeResult sendMultipleSMSMessage();

    public FacadeResult receiveSMSMessage();

    public FacadeResult processReceiveSMSMessages();

}
