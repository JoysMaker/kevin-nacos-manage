package com.kevin.usc.test.persistent.po;

import javax.persistence.*;

@Table(name = "`BCSC_TICKET_TYPE`")
public class bcscTicketType {
    private Integer ticketTypeId;

    private String ticketTypeName;

    private String ticketCategory;

    private String caseServiceTypeCode;

    private String state;

    private String comments;

    public Integer getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(Integer ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public String getTicketTypeName() {
        return ticketTypeName;
    }

    public void setTicketTypeName(String ticketTypeName) {
        this.ticketTypeName = ticketTypeName;
    }

    public String getTicketCategory() {
        return ticketCategory;
    }

    public void setTicketCategory(String ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    public String getCaseServiceTypeCode() {
        return caseServiceTypeCode;
    }

    public void setCaseServiceTypeCode(String caseServiceTypeCode) {
        this.caseServiceTypeCode = caseServiceTypeCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}