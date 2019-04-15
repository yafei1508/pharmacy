package com.miaosha.service;

import com.miaosha.error.BusinessException;
import com.miaosha.service.model.DrugOfTableModel;

import java.util.List;

public interface DrugOfTableService {
    boolean deleteDrugOfTable(Integer id);
    DrugOfTableModel getDrugOfTableById(Integer id);
    List<DrugOfTableModel> getDrugOfTableByTableId(Integer tableId) throws BusinessException;
    Integer addDrugToTable(DrugOfTableModel drugOfTableModel);
}
