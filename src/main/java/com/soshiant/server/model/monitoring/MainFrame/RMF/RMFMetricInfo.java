package com.soshiant.server.model.monitoring.MainFrame.RMF;

import javax.persistence.*;

/*
 * Created by Hamid on 11/30/15.
 */

@Entity
@Table(name = "RMFMONITORINGITEMS",
uniqueConstraints = {@UniqueConstraint(columnNames = {"metricId"})})
public class RMFMetricInfo implements java.io.Serializable {

    @Id
    private String metricId;

    private String chartType;

    private String metricDesc;
    private String parentTitle;
    private String resourceName;
    private String resourceType;
    private String chartLabelColumn;
    private String chartValueColumn;
    private String valuePrefix;
    private String rmfMinValue;
    private String rmfMaxValue;


    @Column(name = "metricId", unique = false, nullable = false)
    public String getMetricId() {
        return metricId;
    }

    public void setMetricId(String metricId) {
        this.metricId = metricId;
    }

    @Column(name = "ChartType", unique = false, nullable = false)
    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    @Column(name = "metricDesc", unique = false, nullable = false)
    public String getMetricDesc() {
        return metricDesc;
    }

    public void setMetricDesc(String metricDesc) {
        this.metricDesc = metricDesc;
    }

    @Column(name = "ParentTitle", unique = false, nullable = false)
    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }

    @Column(name = "ResourceName", unique = false, nullable = false)
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Column(name = "ResourceType", unique = false, nullable = false)
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Column(name = "ChartLabelColumn", unique = false, nullable = false)
    public String getChartLabelColumn() {
        return chartLabelColumn;
    }

    public void setChartLabelColumn(String chartLabelColumn) {
        this.chartLabelColumn = chartLabelColumn;
    }

    @Column(name = "ChartValueColumn", unique = false, nullable = false)
    public String getChartValueColumn() {
        return chartValueColumn;
    }

    public void setChartValueColumn(String chartValueColumn) {
        this.chartValueColumn = chartValueColumn;
    }

    @Column(name = "ValuePrefix", unique = false, nullable = false)
    public String getValuePrefix() {
        return valuePrefix;
    }

    public void setValuePrefix(String valuePrefix) {
        this.valuePrefix = valuePrefix;
    }

    @Column(name = "RmfMinValue", unique = false, nullable = false)
    public String getRmfMinValue() {
        return rmfMinValue;
    }

    public void setRmfMinValue(String rmfMinValue) {
        this.rmfMinValue = rmfMinValue;
    }

    @Column(name = "RmfMaxValue", unique = false, nullable = false)
    public String getRmfMaxValue() {
        return rmfMaxValue;
    }

    public void setRmfMaxValue(String rmfMaxValue) {
        this.rmfMaxValue = rmfMaxValue;
    }

}
