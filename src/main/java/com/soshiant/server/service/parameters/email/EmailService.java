package com.soshiant.server.service.parameters.email;


import com.soshiant.server.model.parameters.EmailInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public interface EmailService {

    public List<EmailInfo> getEmailList();

    public void saveEmailInfo(EmailInfo emailInfo);

    public void saveEmailInfo(String from,String recipientsTo[], String recipientsCc[], String subject, String emailContent,String attachmentFile);

    public void sendEmail(String smtpServerType,String recipients[], String recipientsCc[], String subject, String emailContent,String attachmentFile);

    public short sendMultipleEmailMessage();
}
