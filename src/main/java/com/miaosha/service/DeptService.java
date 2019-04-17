package com.miaosha.service;

import com.miaosha.error.BusinessException;
import com.miaosha.service.model.DeptModel;

import java.util.List;

public interface DeptService {
    List<DeptModel> getDeptList() throws BusinessException;

}
