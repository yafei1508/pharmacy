package com.miaosha.service.model;

import java.util.Date;

public class MedicineTableModel {
    private Integer id;
    private Integer medicalOrderId;
    private Integer accountId;
    private Date addTime;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedicalOrderId() {
        return medicalOrderId;
    }

    public void setMedicalOrderId(Integer medicalOrderId) {
        this.medicalOrderId = medicalOrderId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
