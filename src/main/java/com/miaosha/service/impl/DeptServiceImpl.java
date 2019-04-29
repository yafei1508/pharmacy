package com.miaosha.service.impl;

import com.miaosha.dao.DeptDOMapper;
import com.miaosha.dataobject.DeptDO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.service.DeptService;
import com.miaosha.service.model.DeptModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDOMapper deptDOMapper;
    @Override
    public List<DeptModel> getDeptList() throws BusinessException {
        List<DeptDO> deptDOList = deptDOMapper.selectAll();
        if(deptDOList == null) {
            throw new BusinessException(EmBusinessError.DATABASE_OPTION_ERROR);
        }
        List<DeptModel> modelList = new LinkedList<>();
        for(DeptDO deptDO:deptDOList) {
            DeptModel deptModel = new DeptModel();
            BeanUtils.copyProperties(deptDO, deptModel);
            modelList.add(deptModel);
        }
        return modelList;
    }
}
