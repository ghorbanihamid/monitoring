package com.soshiant.server.dao.log;

import com.soshiant.server.dao.DaoException;
import com.soshiant.server.model.log.AppLog;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 4:31 PM
 */
public interface AppLogDao {

    public List<AppLog> getLogByDoerId(int customerId, int logDate) throws DaoException;

    public List<AppLog> getLogByDate(int lodDate) throws DaoException;

    public void insertLog(AppLog appLog);

}
