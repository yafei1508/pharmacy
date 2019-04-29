package com.miaosha.service;


import com.miaosha.controller.viewobject.SatDetailsVO;
import com.miaosha.dataobject.SatDO;
import com.miaosha.dataobject.SatDetailsDO;
import com.miaosha.service.model.SatModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SatService {
    //自定义申请结果类型
    public class ApplyResult{
        Boolean result;
        Integer outDepId;

        public void setResult(Boolean result) {
            this.result = result;
        }

        public void setOutDepId(Integer outDepId) {
            this.outDepId = outDepId;
        }

        public Boolean getResult() {
            return result;
        }

        public Integer getOutDepId() {
            return outDepId;
        }
    }

    // 先用本地模拟处理申请
    public ApplyResult getApplyResult(List<SatDetailsVO> sdList);

    // 获取Sat表中的记录的数目
    public Integer countSatEntryNums();

    // insert Sat表
    public void addSat(SatDO satModel);

    // insert SatDetail表
    public void addSatDetails(SatDetailsDO satDetailsDO);
}
