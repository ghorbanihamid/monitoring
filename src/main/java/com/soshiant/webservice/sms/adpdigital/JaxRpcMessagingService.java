/**
 * JaxRpcMessagingService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soshiant.webservice.sms.adpdigital;

public interface JaxRpcMessagingService extends java.rmi.Remote {
    public com.soshiant.webservice.sms.adpdigital.SendResult sendMultiple(java.lang.String userName, java.lang.String password, java.lang.String shortNumber, java.lang.String sourcePort, java.lang.String destPort, short messageType, boolean longSupported, com.soshiant.webservice.sms.adpdigital.MultiAddressMessageObject[] messages) throws java.rmi.RemoteException;
    public com.soshiant.webservice.sms.adpdigital.BalanceResult getBalance(java.lang.String userName, java.lang.String password, short facilityId) throws java.rmi.RemoteException;
    public short changePassword(java.lang.String userName, java.lang.String currentPassword, java.lang.String newPassword) throws java.rmi.RemoteException;
    public short getStatus(long messageId) throws java.rmi.RemoteException;
    public com.soshiant.webservice.sms.adpdigital.ReceiveResult receive(java.lang.String userName, java.lang.String password, java.lang.String shortNumber, long start, int maxSize) throws java.rmi.RemoteException;
    public com.soshiant.webservice.sms.adpdigital.SendResult send(java.lang.String userName, java.lang.String password, java.lang.String sourceNo, java.lang.String[] destNo, java.lang.String sourcePort, java.lang.String destPort, java.lang.String[] clientId, short messageType, short encoding, boolean longSupported, java.util.Calendar dueTime, java.lang.String content) throws java.rmi.RemoteException;
    public com.soshiant.webservice.sms.adpdigital.ReportResult report(java.lang.String userName, java.lang.String password, java.lang.String shortNumber, java.lang.String fromClientId, java.lang.String clientId, java.lang.String fromId, java.lang.String id, int maxSize) throws java.rmi.RemoteException;
    public com.soshiant.webservice.sms.adpdigital.StatusReportResult statusReport(java.lang.String userName, java.lang.String password, java.lang.String shortNumber, java.lang.String type, long[] ids, java.lang.String[] clientIds) throws java.rmi.RemoteException;
}
