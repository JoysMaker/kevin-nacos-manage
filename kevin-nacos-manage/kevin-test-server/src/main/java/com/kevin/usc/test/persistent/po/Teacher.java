package com.kevin.usc.test.persistent.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`t_base_teacher`")
@Entity(name="t_base_teacher")
public class Teacher {

    @Id
    private Long teacherId;

    private String teacherName;

    private String teacherNo;

    private Long teacherType;

    private Long gender;

    private Long nation;

    private Long identityType;

    private String identityNum;

    private Long politics;

    private String nativePlace;

    private Date birthday;

    private Long photo;

    private String telNum;

    private String address;

    private Date enterTime;

    private Long healthy;

    private String isZaibian;

    private Long status;

    private Long schoolId;

    private Long createUser;

    private Date createTime;

    private Long updateUser;

    private Date updateTime;

    private String delFlag;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public Long getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(Long teacherType) {
        this.teacherType = teacherType;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public Long getNation() {
        return nation;
    }

    public void setNation(Long nation) {
        this.nation = nation;
    }

    public Long getIdentityType() {
        return identityType;
    }

    public void setIdentityType(Long identityType) {
        this.identityType = identityType;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    public Long getPolitics() {
        return politics;
    }

    public void setPolitics(Long politics) {
        this.politics = politics;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getPhoto() {
        return photo;
    }

    public void setPhoto(Long photo) {
        this.photo = photo;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public Long getHealthy() {
        return healthy;
    }

    public void setHealthy(Long healthy) {
        this.healthy = healthy;
    }

    public String getIsZaibian() {
        return isZaibian;
    }

    public void setIsZaibian(String isZaibian) {
        this.isZaibian = isZaibian;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}