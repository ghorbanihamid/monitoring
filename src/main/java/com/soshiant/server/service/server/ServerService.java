package com.soshiant.server.service.server;

import com.soshiant.server.model.server.RmfConfig;
import com.soshiant.server.model.server.Server;
import com.soshiant.server.model.user.UserServers;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 10/6/11
 * Time: 11:19 AM
 */

public interface ServerService {

    public List getServerListByManagerId(int managerId);

    public List<Server> getServerList(String searchField, String searchString);

    public List<UserServers> getMonitorableServersOfUser(int userId);

    public Server getServerInfo(int staffId);

    public RmfConfig getRmfConfigInfo(int rmfConfigId);

    public int saveServerInfo(Server staff);

    public int saveRmfConfigInfo(RmfConfig rmfConfig);

    public boolean saveServerTelephoneNumber(int siteId,int telephoneType, String newTelephoneNumber);

}
