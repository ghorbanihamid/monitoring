package com.soshiant.server.service.parameters;

import com.soshiant.common.dateTime.DateTimeUtil;
import com.soshiant.common.util.ParametersUtil;
import com.soshiant.server.constants.ServiceConstants;
import com.soshiant.server.dao.parameters.ParametersDao;
import com.soshiant.server.model.parameters.*;
import com.soshiant.server.model.position.Positions;
import com.soshiant.server.model.server.Server;
import com.soshiant.server.model.staff.Staff;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: hubert
 * Date: 7/17/11
 * Time: 4:31 PM
 */

@Service()
@Transactional
public class ParametersServiceImpl implements ParametersService {

    Logger log = Logger.getLogger(this.getClass().getName());

    private String logMessage;

    private static List<Bank> banksList;
    private static List<City> citiesList;
    private List<Positions> positionsList;

    private ParametersDao parametersDao;

    public void setParametersDao(ParametersDao parametersDao) {
        this.parametersDao = parametersDao;
    }

    //======================================================================================================================
    //    @Transactional(readOnly = true)
    public List getPositionsList() {

        if (positionsList == null || positionsList.isEmpty()) {
            positionsList = parametersDao.getPositionsList();
            positionsList.remove(0);
        }
        return positionsList;
    }
    //======================================================================================================================
    //    @Transactional(readOnly = true)
    public String getPositionName(byte positionId) {

        Positions positions;
        String positionName = "";
        if (positionsList == null || positionsList.isEmpty()) {
            getPositionsList();
        }

        for (Positions tmpPositionsList : positionsList) {
            positions = tmpPositionsList;
            if (positionId == positions.getPositionId()) {
                positionName = positions.getPositionName();
                break;
            }
        }
        return positionName;
    }
    //======================================================================================================================
    //    @Transactional(readOnly = true)
    public List getBanksList() {

        if (banksList == null || banksList.isEmpty()) {
            banksList = parametersDao.getBanksList();
        }
        return banksList;
    }
    //======================================================================================================================
    //    @Transactional(readOnly = true)
    public Bank getBankInfo(byte bankCode) {

        Bank bankInfo = null;
        if (banksList == null || banksList.isEmpty()) {
            getBanksList();
        }
        for (Bank temBankInfo : banksList) {
            if (temBankInfo.getBankCode() == bankCode) {
                bankInfo = temBankInfo;
                break;
            }
        }
        return bankInfo;
    }
    //======================================================================================================================
    //    @Transactional(readOnly = true)
    public List getCitiesList() {

        if (citiesList == null || citiesList.isEmpty()) {
            citiesList = parametersDao.getCitiesList();
        }
        return citiesList;
    }
    //======================================================================================================================
    //    @Transactional(readOnly = true)
    public City getCityInfo(int cityCode) {

        City cityInfo = null;
        if (citiesList == null || citiesList.isEmpty()) {
            getCitiesList();
        }
        for (City tempCityInfo : citiesList) {
            if (tempCityInfo.getCityCode() == cityCode) {
                cityInfo = tempCityInfo;
                break;
            }
        }
        return cityInfo;
    }
    //======================================================================================================================
    public String makeNewStaffCounter() {
        try{
            logMessage = "ParametersServiceImpl.makeNewStaffCounter() exception : ";
            return parametersDao.makeNewStaffCounter(DateTimeUtil.getCurrentShamsiYear());
        }catch (Exception e){
            log.debug(logMessage + e.getMessage());
            return "-1";
        }
    }

    //======================================================================================================================
    public Staff setStaffDescription(Staff staff){

        if(staff != null){

            String tempValue = ParametersUtil.getGenderStatusName(staff.getGenderStatus());
            staff.setGenderName(tempValue);
        }
        return staff;
    }
    //======================================================================================================================
    public String makeNewSiteCounter() {
        try{
            logMessage = "ParametersServiceImpl.makeNewSiteCounter() exception : ";
            return parametersDao.makeNewSiteCounter();
        }catch (Exception e){
            log.debug(logMessage + e.getMessage());
            return "-1";
        }
    }

    //======================================================================================================================
    public String makeNewServerCounter() {
        try{
            logMessage = "ParametersServiceImpl.makeNewServerCounter() exception : ";
            return parametersDao.makeNewServerCounter();
        }catch (Exception e){
            log.debug(logMessage + e.getMessage());
            return "-1";
        }
    }
    //==================================================================================================================
    public boolean getRmfMonitoringServiceStatus() {

        boolean rmfMonitoringStatus = parametersDao.getRmfMonitoringServiceStatus();
        ServiceConstants.setRmfMonitoringIsEnabled(rmfMonitoringStatus);
        return rmfMonitoringStatus;
    }

    //==================================================================================================================
    public boolean getSaveRmfMonitoringDataServiceStatus() {

        boolean saveRmfMonitoringStatus = parametersDao.getSaveRmfMonitoringDataServiceStatus();
        ServiceConstants.setRmfMonitoringIsEnabled(saveRmfMonitoringStatus);
        return saveRmfMonitoringStatus;
    }

    //==================================================================================================================
    public List<Server> getMainFrameServersList() {

        List<Server> mainFrameServersList = parametersDao.getMainFrameServersList();
        return mainFrameServersList;
    }

    //==================================================================================================================
    public int getChartRefreshTime() {

        int refreshTime = parametersDao.getChartRefreshTime();
        return refreshTime;
    }

    //==================================================================================================================
    public boolean getSendSmsServiceStatus() {

        boolean sendSmsServiceStatus = parametersDao.getSendSmsServiceStatus();
        ServiceConstants.setSendSmsEnabled(sendSmsServiceStatus);
        return sendSmsServiceStatus;
    }

    //==================================================================================================================
    public boolean getReceiveSmsServiceStatus() {

        boolean sendSmsServiceStatus = parametersDao.getReceiveSmsServiceStatus();
        ServiceConstants.setReceiveSmsEnabled(sendSmsServiceStatus);
        return sendSmsServiceStatus;
    }

    //==================================================================================================================
    public boolean getSendEmailServiceStatus() {

        boolean sendEmailServiceStatus = parametersDao.getSendEmailServiceStatus();
        ServiceConstants.setSendEmailEnabled(sendEmailServiceStatus);
        return sendEmailServiceStatus;
    }

}
