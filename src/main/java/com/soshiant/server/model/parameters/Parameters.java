package com.soshiant.server.model.parameters;

import com.soshiant.common.util.CommonUtil;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: Hamid
 * Date: 8/24/11
 * Time: 8:13 PM
 */

@Entity
@Table(name = "PARAMETERS", uniqueConstraints = {
        @UniqueConstraint(columnNames = "PARAMETERID")})
public class Parameters implements java.io.Serializable{

    @Id
    private short  parameterId;
    private short  parameterYear;
    private String parameterName;
    private String parameterValue;

    @Column(name = "parameterId", unique = true, nullable = false)
    public short getParameterId() {
        return parameterId;
    }

    public void setParameterId(short parameterId) {
        this.parameterId = parameterId;
    }

    @Column(name = "parameterYear", unique = false, nullable = false)
    public short getParameterYear() {
        return parameterYear;
    }

    public void setParameterYear(short parameterYear) {
        this.parameterYear = parameterYear;
    }

    @Column(name = "parameterName", unique = false, nullable = false)
    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    @Column(name = "parameterValue", unique = false, nullable = false)
    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public Parameters() {
    }

    public Parameters(short parameterId, String parameterName, String parameterValue) {

        this.parameterId = parameterId;
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
    }

}