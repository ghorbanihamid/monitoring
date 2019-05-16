package com.soshiant.server.dao.server;


import com.soshiant.server.model.server.RmfConfig;
import com.soshiant.server.model.server.Server;
import com.soshiant.server.model.user.UserServers;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 10/6/11
 * Time: 11:21 AM
 */

public interface ServerDao {

    public List<Server> getServerListByManagerId(int managerId);

    public List<Server> getServerListByServerName(String searchString);

    public List<UserServers> getMonitorableServersOfUser(int userId);

    public Server getServerInfo(int ServerId);

    public RmfConfig getRmfConfigInfo(int rmfConfigId);

    public void saveServerInfo(Server Server);

    public void saveRmfConfigInfo(RmfConfig rmfConfig);

    public boolean saveServerTelephoneNumber1(int ServerId, String newTelephoneNumber);

    public boolean saveServerTelephoneNumber2(int ServerId, String newTelephoneNumber);

    public boolean saveServerTelephoneNumber3(int ServerId, String newTelephoneNumber);

    public boolean saveServerFaxNumber(int ServerId, String newFaxNumber);


}
