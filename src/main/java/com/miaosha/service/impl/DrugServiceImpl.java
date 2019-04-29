package com.miaosha.service.impl;

import com.miaosha.dao.DrugDOMapper;
import com.miaosha.dataobject.DrugDO;
<<<<<<< HEAD
=======
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
>>>>>>> 9a1a088a00595b929d079ca258991b87660344a6
import com.miaosha.service.DrugService;
import com.miaosha.service.model.DrugModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.LinkedList;
import java.util.List;

=======
>>>>>>> 9a1a088a00595b929d079ca258991b87660344a6
@Service
public class DrugServiceImpl implements DrugService {
    @Autowired
    private DrugDOMapper drugDOMapper;
    @Override
<<<<<<< HEAD
    public DrugModel getDrugModelById(Integer id) {
=======
    public DrugModel getDrugModelById(Integer id) throws BusinessException {
>>>>>>> 9a1a088a00595b929d079ca258991b87660344a6
        DrugDO drugDO = drugDOMapper.selectByPrimaryKey(id);
        if(drugDO == null) {
            return null;
        }
        return convertFromDO(drugDO);
    }

<<<<<<< HEAD
    @Override
    public List<DrugModel> listDrugs() {
        List<DrugDO> drugDOList = drugDOMapper.selectAll();
        List<DrugModel> drugModelList = new LinkedList<>();
        for (DrugDO m: drugDOList
             ) {
            drugModelList.add(convertFromDO(m));
        }
        return drugModelList;
    }

=======
>>>>>>> 9a1a088a00595b929d079ca258991b87660344a6
    private DrugModel convertFromDO(DrugDO drugDO) {
        if(drugDO == null) {
            return null;
        }
        DrugModel drugModel = new DrugModel();
        BeanUtils.copyProperties(drugDO,drugModel);
        return drugModel;
    }
}
