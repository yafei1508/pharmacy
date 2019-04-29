package com.miaosha.controller.viewobject;

import java.util.Date;

public class MedicineTableVO {
    private Integer id;
    private Integer medicalOrderId;
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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
