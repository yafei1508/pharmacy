package com.miaosha.service.impl;

import com.miaosha.dao.DrugDOMapper;
import com.miaosha.dao.DrugOfTableDOMapper;
import com.miaosha.dataobject.DrugOfTableDO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.service.DrugOfTableService;
import com.miaosha.service.model.DrugOfTableModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DrugOfTableServiceImpl implements DrugOfTableService {
    @Autowired
    private DrugOfTableDOMapper drugOfTableDOMapper;
    @Override
    public boolean deleteDrugOfTable(Integer id) {
        int num = drugOfTableDOMapper.deleteByPrimaryKey(id);
        return num > 0;
    }

    @Override
    public DrugOfTableModel getDrugOfTableById(Integer id) {
        return convertFromDOTDO(drugOfTableDOMapper.selectByPrimaryKey(id));
    }

    @Override
    public List<DrugOfTableModel> getDrugOfTableByTableId(Integer tableId) throws BusinessException {
        List<DrugOfTableDO> drugOfTableDOList = drugOfTableDOMapper.selectByTableId(tableId);
        if(drugOfTableDOList == null || drugOfTableDOList.size() == 0) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        List<DrugOfTableModel> drugOfTableModelList = new LinkedList<>();
        for(DrugOfTableDO dotdo :drugOfTableDOList) {
            drugOfTableModelList.add(convertFromDOTDO(dotdo));
        }
        return drugOfTableModelList;
    }


    private DrugOfTableModel convertFromDOTDO(DrugOfTableDO drugOfTableDO) {
        if(drugOfTableDO == null) {
            return null;
        }
        DrugOfTableModel drugOfTableModel = new DrugOfTableModel();
        BeanUtils.copyProperties(drugOfTableDO, drugOfTableModel);
        return drugOfTableModel;
    }

    @Override
    public Integer addDrugToTable(DrugOfTableModel drugOfTableModel) {
        return drugOfTableDOMapper.insertSelective(convertFromDOTModel(drugOfTableModel));

    }

    private DrugOfTableDO convertFromDOTModel(DrugOfTableModel drugOfTableModel) {
        if(drugOfTableModel == null) {
            return null;
        }
        DrugOfTableDO drugOfTableDO = new DrugOfTableDO();
        BeanUtils.copyProperties(drugOfTableModel, drugOfTableDO);
        return drugOfTableDO;
    }
}
