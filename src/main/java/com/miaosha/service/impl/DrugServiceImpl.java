package com.miaosha.service.impl;

import com.miaosha.dao.DrugDOMapper;
import com.miaosha.dataobject.DrugDO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.service.DrugService;
import com.miaosha.service.model.DrugModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DrugServiceImpl implements DrugService {
    @Autowired
    private DrugDOMapper drugDOMapper;
    @Override
    public DrugModel getDrugModelById(Integer id) throws BusinessException {
        DrugDO drugDO = drugDOMapper.selectByPrimaryKey(id);
        if(drugDO == null) {
            return null;
        }
        return convertFromDO(drugDO);
    }
    @Override
    public List<DrugModel> listDrugs() {
        List<DrugDO> drugDOList = drugDOMapper.selectAll();
        List<DrugModel> drugModelList = new LinkedList<>();
        for (DrugDO m: drugDOList
        ) {
            drugModelList.add(convertFromDO(m));
        }
        return drugModelList;
    }
    private DrugModel convertFromDO(DrugDO drugDO) {
        if(drugDO == null) {
            return null;
        }
        DrugModel drugModel = new DrugModel();
        BeanUtils.copyProperties(drugDO,drugModel);
        return drugModel;
    }
}
