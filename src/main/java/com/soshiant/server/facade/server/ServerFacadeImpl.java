package com.soshiant.server.facade.server;

import com.soshiant.server.facade.FacadeResult;
import com.soshiant.server.facade.SimpleFacadeResult;
import com.soshiant.server.model.server.RmfConfig;
import com.soshiant.server.model.server.Server;
import com.soshiant.server.model.user.UserServers;
import com.soshiant.server.service.parameters.ParametersService;
import com.soshiant.server.service.server.ServerService;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Hubert
 * Date: 10/6/11
 * Time: 11:19 AM
 */

public class ServerFacadeImpl implements ServerFacade {

    private static Logger logger = Logger.getLogger(ServerFacadeImpl.class);

    private ServerService serverService;
    private ParametersService parametersService;

    public void setServerService(ServerService serverService) {
        this.serverService = serverService;
    }

    public void setParametersService(ParametersService parametersService) {
        this.parametersService = parametersService;
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getServerListByManagerId(short managerId) {

        List<Server> servers = serverService.getServerListByManagerId(managerId);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(servers);
        return (simpleFacadeResult.success());
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getServerInfo(int serverId) {

        Server serverInfo = serverService.getServerInfo(serverId);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(serverInfo);
        return (simpleFacadeResult.success());
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getRmfConfigInfo(int rmfConfigId){

        RmfConfig rmfConfigInfo = serverService.getRmfConfigInfo(rmfConfigId);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(rmfConfigInfo);
        return (simpleFacadeResult.success());
    }

    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getServerList(String searchField, String searchString){

        List<Server> servers = serverService.getServerList(searchField,searchString);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(servers);
        return (simpleFacadeResult.success());
    }
    //======================================================================================================================
    @Transactional(readOnly = true)
    public FacadeResult getMonitorableServersOfUser(int userId) {

        List<UserServers> serverList = serverService.getMonitorableServersOfUser(userId);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(serverList);
        return (simpleFacadeResult.success());

    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult saveServerInfo(Server server) {

        int serverId = serverService.saveServerInfo(server);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(serverId);
        return simpleFacadeResult.success();
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult saveRmfConfigInfo(RmfConfig rmfConfigInfo) {

        int serverId = serverService.saveRmfConfigInfo(rmfConfigInfo);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult(serverId);
        return simpleFacadeResult.success();
    }
    //======================================================================================================================
    @Transactional(readOnly = false)
    public FacadeResult saveServerTelephoneNumber(int serverId,int telephoneType, String newTelephoneNumber) {

        serverService.saveServerTelephoneNumber(serverId,telephoneType, newTelephoneNumber);
        SimpleFacadeResult simpleFacadeResult = new SimpleFacadeResult();
        return simpleFacadeResult.success();
    }
//======================================================================================================================
    //@Override
    public Class getKeysEnumClass() {
        return DataKeys.class;
    }


}