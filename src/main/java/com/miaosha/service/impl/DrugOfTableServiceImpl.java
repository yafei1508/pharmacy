package com.miaosha.service.impl;

import com.miaosha.dao.DrugDOMapper;
import com.miaosha.dao.DrugOfTableDOMapper;
import com.miaosha.dataobject.DrugOfTableDO;
import com.miaosha.service.DrugOfTableService;
import com.miaosha.service.model.DrugOfTableModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
