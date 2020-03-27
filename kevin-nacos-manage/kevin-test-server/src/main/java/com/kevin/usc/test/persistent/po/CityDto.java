package com.kevin.usc.test.persistent.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.BeanUtils;

import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class CityDto {

    private Long cityId;

    private String cityName;

    private String mode;

    private String opMode;

    private String fianchiseeName;

    private String cityAdmins;

    private String cityAdmin;

    public String getCityAdmin() {
        return cityAdmin;
    }

    public void setCityAdmin(String cityAdmin) {
        this.cityAdmin = cityAdmin;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date openTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    private String sysUserName;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getOpMode() {
        return opMode;
    }

    public void setOpMode(String opMode) {
        this.opMode = opMode;
    }

    public String getFianchiseeName() {
        return fianchiseeName;
    }

    public void setFianchiseeName(String fianchiseeName) {
        this.fianchiseeName = fianchiseeName;
    }

    public String getCityAdmins() {
        return cityAdmins;
    }

    public void setCityAdmins(String cityAdmins) {
        this.cityAdmins = cityAdmins;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public static void main(String[] args){

        City city = new City();
        city.setCityName("北京");
        CityDto cityDto = new CityDto();
        BeanUtils.copyProperties(city, cityDto);

        System.out.println(cityDto);
    }
}