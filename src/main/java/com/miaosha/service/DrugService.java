package com.miaosha.service;

import com.miaosha.service.model.DrugModel;

import java.util.List;

public interface DrugService {
    public List<DrugModel> listDrugs();
    public DrugModel getDrugModelById(Integer id);
}
