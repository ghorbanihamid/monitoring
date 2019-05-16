package com.soshiant.server.facade.parameters;

import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.SimpleFacadeResult;
import com.soshiant.server.model.parameters.*;
import com.soshiant.server.service.parameters.SmsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class SmsFacadeImpl implements SmsFacade {

    private SmsService smsService;

    public void setSmsService(SmsService smsService) {
        this.smsService = smsService;
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getAlarmList() {

        List<AlarmMessageInfo> tmpAlarmList = this.smsService.getAlarmsList();
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(tmpAlarmList);
        return simpleFacadeResult.success();
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getSmsList() {

        List<SendSmsInfo> tmpSmsList = this.smsService.getSmsList();
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(tmpSmsList);
        return simpleFacadeResult.success();
    }

    //======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult sendSMSMessage(String cellPhone , String messageText) {

        int resultStatus = this.smsService.sendSMSMessage(cellPhone, messageText);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(resultStatus);
        return simpleFacadeResult.success();
    }

    //======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult sendAlarmMessage() {

        int resultStatus = this.smsService.sendAlarmMessage();
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(resultStatus);
        return simpleFacadeResult.success();
    }

    //======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult sendMultipleSMSMessage() {

        int resultStatus = this.smsService.sendMultipleSMSMessage();
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(resultStatus);
        return simpleFacadeResult.success();
    }

    //======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult receiveSMSMessage() {

        int resultStatus = this.smsService.receiveSMS();
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(resultStatus);
        return simpleFacadeResult.success();
    }

    //======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult processReceiveSMSMessages() {

        int resultStatus = this.smsService.processReceiveSMSMessages();
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult();
        if(resultStatus != 0){
            return simpleFacadeResult.fail(resultStatus);
        }
        else{
            return simpleFacadeResult.success();
        }
    }

    //======================================================================================================================
    //@Override
    public Class getKeysEnumClass() {
        return DataKeys.class;
    }
}
