package com.miaosha.service;

import com.miaosha.error.BusinessException;
import com.miaosha.service.model.DrugModel;
import java.util.List;

public interface DrugService {
    List<DrugModel> listDrugs();
    DrugModel getDrugModelById(Integer id) throws BusinessException;
}
