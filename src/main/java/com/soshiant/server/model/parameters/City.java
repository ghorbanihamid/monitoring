package com.soshiant.server.model.parameters;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: hamid
 * Date: 7/17/11
 * Time: 6:05 PM
 */

@Entity
@Table(name = "CITIESINFO", uniqueConstraints = {
        @UniqueConstraint(columnNames = "CityCode")})
public class City implements java.io.Serializable {

    @Id
    private int cityCode;
    private int provinceCode;
    private String cityEnName;
    private String cityOlName;
    private String provinceName;
    private String englishProvinceName;

    @Column(name = "CityCode", unique = true, nullable = false)
    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        cityCode = cityCode;
    }

    @Column(name = "ProvinceCode", unique = true, nullable = false)
    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    @Column(name = "CityEnName", unique = true, nullable = false)
    public String getCityEnName() {
        return cityEnName;
    }

    public void setCityEnName(String cityEnName) {
        this.cityEnName = cityEnName;
    }

    @Column(name = "CityOlName", unique = true, nullable = false)
    public String getCityOlName() {
        return cityOlName;
    }

    public void setCityOlName(String cityOlName) {
        this.cityOlName = cityOlName;
    }

    @Column(name = "ProvinceName", unique = true, nullable = false)
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Column(name = "EnglishProvinceName", unique = true, nullable = false)
    public String getEnglishProvinceName() {
        return englishProvinceName;
    }

    public void setEnglishProvinceName(String englishProvinceName) {
        this.englishProvinceName = englishProvinceName;
    }

    public City(){

    }

    public City(int cityCode, String cityEnName, String cityOlName){

        this.cityCode = cityCode;
        this.cityEnName = cityEnName;
        this.cityOlName = cityOlName;
    }

}
