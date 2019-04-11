package com.miaosha.service;

import com.miaosha.service.model.MedicineTableModel;

import java.util.List;

public interface MedicineTableService {
    List<MedicineTableModel> listMedicineTable();
    Boolean deleteOne(Integer id);
}
