package com.miaosha.service.model;

public class DrugOfStockOutTableModel {
    private Integer id;
    private Integer sotId;
    private Integer drugId;
    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSotId() {
        return sotId;
    }

    public void setSotId(Integer sotId) {
        this.sotId = sotId;
    }

    public Integer getDrugId() {
        return drugId;
    }

    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
