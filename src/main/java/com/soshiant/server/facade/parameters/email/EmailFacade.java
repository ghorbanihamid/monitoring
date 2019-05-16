package com.soshiant.server.facade.parameters.email;


import com.soshiant.server.facade.Facade;
import com.soshiant.server.facade.FacadeResult;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public interface EmailFacade extends Facade {

    public enum DataKeys {
    }

    public FacadeResult getEmailList();

    public FacadeResult sendEmail(String smtpServerType,String recipientsTo[],String recipientsCc[], String subject, String emailContent,String attachmentFile);

    public FacadeResult sendMultipleEmail();

    public FacadeResult saveEmailInfo(String from,String recipientsTo[], String recipientsCc[], String subject, String emailContent,String attachmentFile);
}
