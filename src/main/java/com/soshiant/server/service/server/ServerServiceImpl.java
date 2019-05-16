package com.soshiant.server.service.server;

import com.soshiant.common.util.CommonUtil;
import com.soshiant.server.constants.ServerConstants;
import com.soshiant.server.dao.server.ServerDao;
import com.soshiant.server.model.server.RmfConfig;
import com.soshiant.server.model.server.Server;
import com.soshiant.server.model.user.UserServers;
import com.soshiant.server.service.BusinessException;
import com.soshiant.server.service.parameters.ParametersService;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Hubert
 * Date: 10/6/11
 * Time: 11:19 AM
 */

@Transactional
public class ServerServiceImpl implements ServerService {

    private static Logger logger = Logger.getLogger(ServerServiceImpl.class);

    private ServerDao serverDao;

    private ParametersService parametersService;

    public void setServerDao(ServerDao serverDao) {
        this.serverDao = serverDao;
    }

    public void setParametersService(ParametersService parametersService) {
        this.parametersService = parametersService;
    }

    //======================================================================================================================
    public List getServerListByManagerId(int managerId) {

        List<Server> servers = serverDao.getServerListByManagerId(managerId);
        return servers;
    }

    //======================================================================================================================
    public Server getServerInfo(int serverId) {

        Server serverInfo = serverDao.getServerInfo(serverId);
        return serverInfo;
    }

    //======================================================================================================================
    public RmfConfig getRmfConfigInfo(int rmfConfigId) {

        RmfConfig rmfConfigInfo = serverDao.getRmfConfigInfo(rmfConfigId);
        return rmfConfigInfo;
    }

    //======================================================================================================================
    public List<Server> getServerList(String searchField, String searchString){

        List<Server> serverList = null;

        switch (searchField){
            case ServerConstants.SITE_MANAGER_ID : serverList = serverDao.getServerListByManagerId(Short.parseShort(searchString));
                                             break;
            case ServerConstants.SITE_NAME : serverList = serverDao.getServerListByServerName(searchString);
                                             break;
        }

        if(serverList == null || serverList.size() == 0){
            throw new BusinessException(ServerConstants.ERROR_DB_INFORMATION_NOT_FOUND);
        }

        return serverList;
    }
    //======================================================================================================================
    public List<UserServers> getMonitorableServersOfUser(int userId){

        List<UserServers> serverList = null;


        serverList = serverDao.getMonitorableServersOfUser(userId);

        if(serverList == null || serverList.size() == 0){
            throw new BusinessException(ServerConstants.ERROR_DB_INFORMATION_NOT_FOUND);
//            return ServerConstants.ERROR_DB_INFORMATION_NOT_FOUND;
        }

        return serverList;
    }
    //======================================================================================================================
    public int saveServerInfo(Server server) {

        if(!server.isEditMode()) {

            String serverCounter =  parametersService.makeNewServerCounter();
            if(serverCounter == null){
                throw new BusinessException(ServerConstants.ERROR_DB_CANNOT_FETCH_SYSTEM_PARAMETERS);
            }
            int serverId = Integer.parseInt(CommonUtil.nvl(serverCounter,"-1"));
            if(serverId == -1){
//                throw new BusinessException(ServerConstants.ERROR_DB_CANNOT_FETCH_SYSTEM_PARAMETERS);
                return ServerConstants.ERROR_DB_CANNOT_FETCH_SYSTEM_PARAMETERS;
            }
            server.setServerId(serverId);
        }
        serverDao.saveServerInfo(server);

        return server.getServerId();
    }
    //======================================================================================================================
    public int saveRmfConfigInfo(RmfConfig rmfConfig) {

        if(!rmfConfig.isEditMode()) {

            String serverCounter =  parametersService.makeNewServerCounter();
            if(serverCounter == null){
                throw new BusinessException(ServerConstants.ERROR_DB_CANNOT_FETCH_SYSTEM_PARAMETERS);
            }
            int serverId = Integer.parseInt(CommonUtil.nvl(serverCounter,"-1"));
            if(serverId == -1){
//                throw new BusinessException(ServerConstants.ERROR_DB_CANNOT_FETCH_SYSTEM_PARAMETERS);
                return ServerConstants.ERROR_DB_CANNOT_FETCH_SYSTEM_PARAMETERS;
            }
            rmfConfig.setServerId(serverId);
        }
        serverDao.saveRmfConfigInfo(rmfConfig);

        return rmfConfig.getServerId();
    }
    //======================================================================================================================
    public boolean saveServerTelephoneNumber(int serverId,int telephoneType, String newTelephoneNumber) {

        switch (telephoneType) {
            case 1:
                serverDao.saveServerTelephoneNumber1(serverId, newTelephoneNumber);
                break;
            case 2:
                serverDao.saveServerTelephoneNumber2(serverId, newTelephoneNumber);
                break;
            case 3:
                serverDao.saveServerTelephoneNumber3(serverId, newTelephoneNumber);
                break;
            case 4:
                serverDao.saveServerFaxNumber(serverId, newTelephoneNumber);
                break;
        }
        return true;
    }

    //======================================================================================================================

}