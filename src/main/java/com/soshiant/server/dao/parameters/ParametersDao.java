package com.soshiant.server.dao.parameters;

import com.soshiant.server.model.parameters.*;
import com.soshiant.server.model.position.Positions;
import com.soshiant.server.model.server.Server;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 4:31 PM
 */
public interface ParametersDao {


    public List<Bank> getBanksList();

    public List<City> getCitiesList();

    public List<Positions> getPositionsList();

    public List<Server> getMainFrameServersList();

    public boolean getRmfMonitoringServiceStatus();

    public boolean getSaveRmfMonitoringDataServiceStatus();

    public int getChartRefreshTime();

    public boolean getSendSmsServiceStatus();

    public boolean getReceiveSmsServiceStatus();

    public boolean getSendEmailServiceStatus();

    public String makeNewStaffCounter(short termYear);

    public boolean saveParameterNewValue(short parameterId,String newCounter);

    public String makeNewSiteCounter();

    public String makeNewServerCounter();

}
