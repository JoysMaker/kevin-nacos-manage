package com.kevin.usc.test.persistent.po;

import lombok.EqualsAndHashCode;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`t_order`")
@Entity(name="t_order")
@EqualsAndHashCode
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderSn;

    private String bikeSn;

    private String userName;

    private String mobile;

    private String distance;

    private Long totalTime;

    private Integer status;

    private Date startTime;

    private Date endTime;

    private Integer totalFee;

    private Integer userPay;

    private Integer cityId;

    private String cityName;

    public Long getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(Long orderSn) {
        this.orderSn = orderSn;
    }

    public String getBikeSn() {
        return bikeSn;
    }

    public void setBikeSn(String bikeSn) {
        this.bikeSn = bikeSn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getUserPay() {
        return userPay;
    }

    public void setUserPay(Integer userPay) {
        this.userPay = userPay;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public static void main(String[] args) {
        Order o1 = new Order();
        o1.setBikeSn("11111");
        o1.setCityId(1);
        Order o2 = new Order();
        o2.setBikeSn("11111");
        System.out.println(o1.toString().equals(o2.toString()));
    }
}