package com.miaosha.service.impl;

import com.miaosha.dao.DrugOfStockOutTableDOMapper;
import com.miaosha.dao.StockOutTableDOMapper;
import com.miaosha.dataobject.DrugOfStockOutTableDO;
import com.miaosha.dataobject.StockOutTableDO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.service.StockOutTableService;
import com.miaosha.service.model.DrugOfStockOutTableModel;
import com.miaosha.service.model.StockOutTableModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
@Service
public class StockOutTableServiceImpl implements StockOutTableService {
    @Autowired
    private DrugOfStockOutTableDOMapper drugOfStockOutTableDOMapper;
    @Autowired
    private StockOutTableDOMapper stockOutTableDOMapper;
    @Override
    @Transactional
    public void addTable(StockOutTableModel stockOutTableModel) throws BusinessException {
        if(stockOutTableModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        StockOutTableDO stockOutTableDO = new StockOutTableDO();
        BeanUtils.copyProperties(stockOutTableModel, stockOutTableDO);
        List<DrugOfStockOutTableModel> modelList = stockOutTableModel.getDrugOfStockOutTableModelList();
        if(modelList == null || modelList.size() == 0) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "出库表为空");
        }
        stockOutTableDOMapper.insertSelective(stockOutTableDO);
        for(DrugOfStockOutTableModel mo:modelList) {
            DrugOfStockOutTableDO drugDo = new DrugOfStockOutTableDO();
            mo.setSotId(stockOutTableDO.getId());
            BeanUtils.copyProperties(mo, drugDo);
            drugOfStockOutTableDOMapper.insertSelective(drugDo);
        }
    }
}
