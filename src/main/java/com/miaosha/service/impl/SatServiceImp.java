package com.miaosha.service.impl;

import com.miaosha.controller.viewobject.SatDetailsVO;
import com.miaosha.dao.SatDOMapper;
import com.miaosha.dao.SatDetailsDOMapper;
import com.miaosha.dataobject.SatDO;
import com.miaosha.dataobject.SatDetailsDO;
import com.miaosha.service.SatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class SatServiceImp implements SatService {
    @Autowired
    private SatDOMapper satDOMapper;

    @Autowired
    private SatDetailsDOMapper satDetailsDOMapper;

    //TODO 先通过本地获取申请结果
    @Override
    public ApplyResult getApplyResult(List<SatDetailsVO> sdList) {
        ApplyResult applyResult = new ApplyResult();
        for(SatDetailsVO sdVO:sdList){
            if(sdVO.getNum() > 30){
                applyResult.setResult(false);
                return  applyResult;
            }
        }
        applyResult.setResult(true);
        applyResult.setOutDepId(8);
        return  applyResult;
    }

    @Override
    public Integer countSatEntryNums() {

        Integer totalEntries = satDOMapper.countNums();

        return totalEntries;
    }

    @Override
    public void addSat(SatDO satModel) {
        satDOMapper.insert(satModel);
    }

    @Override
    public void addSatDetails(SatDetailsDO satDetailsDO) {
        satDetailsDOMapper.insert(satDetailsDO);
    }
}
