package com.miaosha.service;

import com.miaosha.error.BusinessException;
import com.miaosha.service.model.StockOutTableModel;

public interface StockOutTableService {
    void addTable(StockOutTableModel stockOutTableModel) throws BusinessException;
}
