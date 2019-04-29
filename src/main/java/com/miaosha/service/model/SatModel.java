package com.miaosha.service.model;

import com.miaosha.dataobject.SatDO;
import com.miaosha.dataobject.SatDetailsDO;

import java.security.Timestamp;
import java.util.List;

public class SatModel {
    private SatDO satInfo;
//    private Integer id;
//
//    private Integer status;
//
//    private Integer outDepId;

    private List<SatDetailsDO> satDetails;

    public SatDO getSatInfo() {
        return satInfo;
    }

    public void setSatInfo(Integer id, Integer status, Integer outDepId, Integer inDepId) {
        //直接this.satIndo.setXX不行，对象还没有创建
        SatDO satDO = new SatDO();
        satDO.setId(id);
        satDO.setStatus(status);
        satDO.setStockoutDeptId(outDepId);
        satDO.setStockinDeptId(inDepId);
        java.util.Date date = new java.util.Date();
        satDO.setAddTime(date);
        this.satInfo = satDO;
    }

    public List<SatDetailsDO> getSatDetails() {
        return satDetails;
    }

    public void setSatDetails(List<SatDetailsDO> satDetails) {
        this.satDetails = satDetails;
    }

//    public Integer getOutDepId() {
//        return outDepId;
//    }
//
//    public void setOutDepId(Integer outDepId) {
//        this.outDepId = outDepId;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
}
