package com.soshiant.server.facade.parameters.email;

import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.SimpleFacadeResult;
import com.soshiant.server.model.parameters.EmailInfo;
import com.soshiant.server.service.parameters.email.EmailService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public class EmailFacadeImpl implements EmailFacade {

    private EmailService emailService;

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

//======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getEmailList() {

        List<EmailInfo> tmpSmsList = this.emailService.getEmailList();
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(tmpSmsList);
        return simpleFacadeResult.success();
    }

//======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult sendEmail(String smtpServerType, String recipientsTo[], String recipientsCc[],String subject, String emailContent,String attachmentFile) {

//        Thread senderThread = new Thread(new EmailSenderThread(from,recipientsTo,recipientsCc, subject, emailContent,attachmentFile));
//        senderThread.start();
        this.emailService.sendEmail(smtpServerType,recipientsTo, recipientsCc, subject, emailContent,attachmentFile);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult();
        return simpleFacadeResult.success();
    }

//======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult sendMultipleEmail() {

        int resultStatus = this.emailService.sendMultipleEmailMessage();
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(resultStatus);
        return simpleFacadeResult.success();
    }

//======================================================================================================================
    public FacadeResult saveEmailInfo(String from,String recipientsTo[], String recipientsCc[], String subject, String emailContent,String attachmentFile) {

        this.emailService.saveEmailInfo(from, recipientsTo, recipientsCc, subject, emailContent ,attachmentFile);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult();
        return simpleFacadeResult.success();

    }
//======================================================================================================================
    //@Override
    public Class getKeysEnumClass() {
        return DataKeys.class;
    }
}
