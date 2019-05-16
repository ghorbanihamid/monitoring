package com.soshiant.server.model.monitoring.MainFrame.RMF;

import javax.persistence.*;
import java.math.BigInteger;

/*
 * Created by Hamid on 11/30/15.
 */

@Entity
@Table(name = "RMFMONITORINGDATA", uniqueConstraints = {
        @UniqueConstraint(columnNames = "rmfMetricId")})
public class RMFMonitoringData implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    BigInteger id;

    short gathererInterval;
    short dataRange;
    int persianTransDate;
    int transStartTime;
    int transEndTime;
    int insertDate;
    int serverId;

    BigInteger localStartTime;
    BigInteger localEndTime;
    BigInteger utcStartTime;
    BigInteger utcEndTime;

    String rmfMetricId;
    String rmfMetricDesc;
    String rowEx;
    String rowValue;
    String rowPer;
    String rowLabel;



    @Transient
    String xmlData;


    @Column(name = "Id", unique = true, nullable = false)
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Column(name = "GathererInterval", unique = true, nullable = false)
    public short getGathererInterval() {
        return gathererInterval;
    }

    public void setGathererInterval(short gathererInterval) {
        this.gathererInterval = gathererInterval;
    }

    @Column(name = "Id", unique = true, nullable = false)
    public short getDataRange() {
        return dataRange;
    }

    public void setDataRange(short dataRange) {
        this.dataRange = dataRange;
    }

    @Column(name = "ServerId", unique = true, nullable = false)
    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    @Column(name = "RmfMetricId", unique = false, nullable = false)
    public String getRmfMetricId() {
        return rmfMetricId;
    }

    public void setRmfMetricId(String rmfMetricId) {
        this.rmfMetricId = rmfMetricId;
    }

    @Column(name = "RmfMetricDesc", unique = false, nullable = false)
    public String getRmfMetricDesc() {
        return rmfMetricDesc;
    }

    public void setRmfMetricDesc(String rmfMetricDesc) {
        this.rmfMetricDesc = rmfMetricDesc;
    }

    @Column(name = "RowEx", unique = false, nullable = false)
    public String getRowEx() {
        return rowEx;
    }

    public void setRowEx(String rowEx) {
        this.rowEx = rowEx;
    }

    @Column(name = "RowValue", unique = false, nullable = false)
    public String getRowValue() {
        return rowValue;
    }

    public void setRowValue(String rowValue) {
        this.rowValue = rowValue;
    }

    @Column(name = "RowPer", unique = false, nullable = false)
    public String getRowPer() {
        return rowPer;
    }

    public void setRowPer(String rowPer) {
        this.rowPer = rowPer;
    }

    @Column(name = "PersianTransDate", unique = false, nullable = false)
    public int getPersianTransDate() {
        return persianTransDate;
    }

    public void setPersianTransDate(int persianTransDate) {
        this.persianTransDate = persianTransDate;
    }

    @Column(name = "transStartTime", unique = false, nullable = false)
    public int getTransStartTime() {
        return transStartTime;
    }

    public void setTransStartTime(int transStartTime) {
        this.transStartTime = transStartTime;
    }

    @Column(name = "transEndTime", unique = false, nullable = false)
    public int getTransEndTime() {
        return transEndTime;
    }

    public void setTransEndTime(int transEndTime) {
        this.transEndTime = transEndTime;
    }




    @Column(name = "InsertDate", unique = false, nullable = false)
    public int getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(int insertDate) {
        this.insertDate = insertDate;
    }

    @Column(name = "RowLabel", unique = false, nullable = false)
    public String getRowLabel() {
        return rowLabel;
    }

    public void setRowLabel(String rowLabel) {
        this.rowLabel = rowLabel;
    }

    @Column(name = "LocalStartTime", unique = false, nullable = false)
    public BigInteger getLocalStartTime() {
        return localStartTime;
    }

    public void setLocalStartTime(BigInteger localStartTime) {
        this.localStartTime = localStartTime;
    }

    @Column(name = "LocalEndTime", unique = false, nullable = false)
    public BigInteger getLocalEndTime() {
        return localEndTime;
    }

    public void setLocalEndTime(BigInteger localEndTime) {
        this.localEndTime = localEndTime;
    }

    @Column(name = "UtcStartTime", unique = false, nullable = false)
    public BigInteger getUtcStartTime() {
        return utcStartTime;
    }

    public void setUtcStartTime(BigInteger utcStartTime) {
        this.utcStartTime = utcStartTime;
    }

    @Column(name = "UtcEndTime", unique = false, nullable = false)
    public BigInteger getUtcEndTime() {
        return utcEndTime;
    }

    public void setUtcEndTime(BigInteger utcEndTime) {
        this.utcEndTime = utcEndTime;
    }

    public String getXmlData() {
        return xmlData;
    }

    public void setXmlData(String xmlData) {
        this.xmlData = xmlData;
    }

    //==================================================================================================================
    public String getRmfColumnValueForChartLabel(){

        switch (this.rmfMetricId){
            case "8D0460" : return String.valueOf(this.transStartTime);
            case "8D0160" : return String.valueOf(this.transStartTime);
            case "8D1A20" : return String.valueOf(this.transStartTime);
            case "8D1A80" : return String.valueOf(this.transStartTime);
            case "8D1AE0" : return String.valueOf(this.transStartTime);
            case "8D1B40" : return String.valueOf(this.transStartTime);
            case "8D1BA0" : return String.valueOf(this.transStartTime);
            case "8D1C00" : return String.valueOf(this.transStartTime);
            case "8D04A0" : return String.valueOf(this.transStartTime);
            case "8D1D40" : return String.valueOf(this.transStartTime);
            case "8D1DB0" : return String.valueOf(this.transStartTime);
            case "8D0550" : return String.valueOf(this.transStartTime);
            case "8D1ED0" : return String.valueOf(this.transStartTime);
            case "8D1F30" : return String.valueOf(this.transStartTime);
            case "8D0620" : return String.valueOf(this.transStartTime);
            case "8D0680" : return String.valueOf(this.transStartTime);
            case "8D0D50" : return String.valueOf(this.transStartTime);
            case "8D0EB0" : return String.valueOf(this.transStartTime);
            case "8D0E90" : return String.valueOf(this.transStartTime);
            case "8D1FB0" : return String.valueOf(this.transStartTime);
            case "8D11F0" : return String.valueOf(this.transStartTime);
            case "8D1200" : return String.valueOf(this.transStartTime);
            case "8D3310" : return String.valueOf(this.transStartTime);
            case "8D2560" : return String.valueOf(this.transStartTime);
            case "8D32E0" : return String.valueOf(this.transStartTime);
            case "8D2590" : return String.valueOf(this.transStartTime);
            case "8D3340" : return String.valueOf(this.transStartTime);
            case "8D3400" : return String.valueOf(this.transStartTime);
            case "8D25B0" : return String.valueOf(this.transStartTime);
            case "8D24A0" : return String.valueOf(this.transStartTime);
            case "8D25F0" : return String.valueOf(this.transStartTime);
            case "8D2640" : return String.valueOf(this.transStartTime);
            case "8D2670" : return String.valueOf(this.transStartTime);
            case "8D2690" : return String.valueOf(this.transStartTime);
            case "8D2760" : return String.valueOf(this.transStartTime);
            case "8D27A0" : return String.valueOf(this.transStartTime);
            case "8D2A30" : return String.valueOf(this.transStartTime);
            case "8D2A80" : return String.valueOf(this.transStartTime);
            case "8D2420" : return String.valueOf(this.transStartTime);
            case "8D2440" : return String.valueOf(this.transStartTime);
            case "8D2460" : return String.valueOf(this.transStartTime);
            case "8D34D0" : return String.valueOf(this.transStartTime);
            case "8D3580" : return String.valueOf(this.transStartTime);
            case "8D2480" : return String.valueOf(this.transStartTime);

            default       :return String.valueOf(this.rowLabel);
        }
    }
    //==================================================================================================================
    @Override
    public String toString() {

        return "RMFMonitoringData{" +
                "xmlData=" + xmlData +
                ", rowEx=" + rowEx +
                ", rowLabel=" + rowLabel +
                ", rowValue=" + rowValue +
                ", rowPer=" + rowPer +
                '}';
    }

}
