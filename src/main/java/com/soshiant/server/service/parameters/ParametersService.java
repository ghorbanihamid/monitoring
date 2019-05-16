package com.soshiant.server.service.parameters;


import com.soshiant.server.model.parameters.Bank;
import com.soshiant.server.model.parameters.City;
import com.soshiant.server.model.server.Server;
import com.soshiant.server.model.staff.Staff;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

public interface ParametersService {

    public List getBanksList();

    public List getPositionsList();

    public String getPositionName(byte positionId);

    public Bank getBankInfo(byte bankCode);

    public List getCitiesList();

    public City getCityInfo(int cityCode);

    public String makeNewStaffCounter();

    public Staff setStaffDescription(Staff staff);

    public String makeNewSiteCounter();

    public String makeNewServerCounter();

    public boolean getRmfMonitoringServiceStatus();

    public boolean getSaveRmfMonitoringDataServiceStatus();

    public List<Server> getMainFrameServersList();

    public int getChartRefreshTime();

    public boolean getSendSmsServiceStatus();

    public boolean getReceiveSmsServiceStatus();

    public boolean getSendEmailServiceStatus();

}
