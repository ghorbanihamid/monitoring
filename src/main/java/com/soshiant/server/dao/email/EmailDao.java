package com.soshiant.server.dao.email;

import com.soshiant.server.model.parameters.EmailInfo;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 4:31 PM
 */
public interface EmailDao {

    public List<EmailInfo> getEmailList();

    public void saveEmailInfo(EmailInfo emailInfo);

    public void markEmailListAsSent(List<EmailInfo> emailInfoList, int sendDate);

}
