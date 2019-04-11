package com.miaosha.service;

import com.miaosha.service.model.DrugOfTableModel;

public interface DrugOfTableService {
    boolean deleteDrugOfTable(Integer id);
    Integer addDrugToTable(DrugOfTableModel drugOfTableModel);
}
