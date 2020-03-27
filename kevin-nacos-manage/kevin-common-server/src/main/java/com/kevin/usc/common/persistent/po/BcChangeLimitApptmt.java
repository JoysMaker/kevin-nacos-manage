package com.kevin.usc.common.persistent.po;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`BC_CHANGE_LIMIT_APPTMT`")
public class BcChangeLimitApptmt {
    private Long apptmtId;

    private Long custId;

    private Long acctId;

    private BigDecimal acctItemGroupId;

    private Integer ccType;

    private Long prodInstId;

    private Long compInstId;

    private String chargeUtil;

    private BigDecimal paymentLimit;

    private BigDecimal upperAmount;

    private BigDecimal dailyCeilLimit;

    private Long createStaff;

    private Date createDate;

    private Long updateStaff;

    private Date updateDate;

    private Date effDate;

    private Date expDate;

    private Date statusDate;

    private String errMsg;

    private Short tryTimes;

    private String remarks;

    private Integer priority;

    public Long getApptmtId() {
        return apptmtId;
    }

    public void setApptmtId(Long apptmtId) {
        this.apptmtId = apptmtId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Long getAcctId() {
        return acctId;
    }

    public void setAcctId(Long acctId) {
        this.acctId = acctId;
    }

    public BigDecimal getAcctItemGroupId() {
        return acctItemGroupId;
    }

    public void setAcctItemGroupId(BigDecimal acctItemGroupId) {
        this.acctItemGroupId = acctItemGroupId;
    }

    public Integer getCcType() {
        return ccType;
    }

    public void setCcType(Integer ccType) {
        this.ccType = ccType;
    }

    public Long getProdInstId() {
        return prodInstId;
    }

    public void setProdInstId(Long prodInstId) {
        this.prodInstId = prodInstId;
    }

    public Long getCompInstId() {
        return compInstId;
    }

    public void setCompInstId(Long compInstId) {
        this.compInstId = compInstId;
    }

    public String getChargeUtil() {
        return chargeUtil;
    }

    public void setChargeUtil(String chargeUtil) {
        this.chargeUtil = chargeUtil;
    }

    public BigDecimal getPaymentLimit() {
        return paymentLimit;
    }

    public void setPaymentLimit(BigDecimal paymentLimit) {
        this.paymentLimit = paymentLimit;
    }

    public BigDecimal getUpperAmount() {
        return upperAmount;
    }

    public void setUpperAmount(BigDecimal upperAmount) {
        this.upperAmount = upperAmount;
    }

    public BigDecimal getDailyCeilLimit() {
        return dailyCeilLimit;
    }

    public void setDailyCeilLimit(BigDecimal dailyCeilLimit) {
        this.dailyCeilLimit = dailyCeilLimit;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Short getTryTimes() {
        return tryTimes;
    }

    public void setTryTimes(Short tryTimes) {
        this.tryTimes = tryTimes;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}