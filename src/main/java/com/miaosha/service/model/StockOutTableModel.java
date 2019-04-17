package com.miaosha.service.model;

import java.util.Date;
import java.util.List;

public class StockOutTableModel {
    private Integer id;
    private Integer stockinDeptId;
    private Integer stockoutDeptId;
    private Date addTime;
    private List<DrugOfStockOutTableModel> drugOfStockOutTableModelList;

    public List<DrugOfStockOutTableModel> getDrugOfStockOutTableModelList() {
        return drugOfStockOutTableModelList;
    }

    public void setDrugOfStockOutTableModelList(List<DrugOfStockOutTableModel> drugOfStockOutTableModelList) {
        this.drugOfStockOutTableModelList = drugOfStockOutTableModelList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStockinDeptId() {
        return stockinDeptId;
    }

    public void setStockinDeptId(Integer stockinDeptId) {
        this.stockinDeptId = stockinDeptId;
    }

    public Integer getStockoutDeptId() {
        return stockoutDeptId;
    }

    public void setStockoutDeptId(Integer stockoutDeptId) {
        this.stockoutDeptId = stockoutDeptId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
