package com.soshiant.server.facade.server;


import com.soshiant.server.facade.Facade;
import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.model.server.RmfConfig;
import com.soshiant.server.model.server.Server;


/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 10/6/11
 * Time: 11:19 AM
 */

public interface ServerFacade extends Facade {

    public FacadeResult getServerInfo(int staffId);

    public FacadeResult getRmfConfigInfo(int rmfConfigId);

    public FacadeResult getServerListByManagerId(short managerId);

    public FacadeResult getServerList(String searchField, String searchString);

    public FacadeResult getMonitorableServersOfUser(int userId);

    public FacadeResult saveServerInfo(Server server);

    public FacadeResult saveRmfConfigInfo(RmfConfig  rmfConfig);

    public FacadeResult saveServerTelephoneNumber(int siteId,int telephoneType, String newTelephoneNumber);

    public enum DataKeys {
    }

}
