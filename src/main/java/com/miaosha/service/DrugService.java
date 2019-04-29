package com.miaosha.service;

import com.miaosha.error.BusinessException;
import com.miaosha.service.model.DrugModel;

public interface DrugService {
    DrugModel getDrugModelById(Integer id) throws BusinessException;
}
