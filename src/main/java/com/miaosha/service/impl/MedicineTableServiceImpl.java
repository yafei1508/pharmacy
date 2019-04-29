package com.miaosha.service.impl;

import com.miaosha.dao.MedicineTableDOMapper;
import com.miaosha.dataobject.MedicineTableDO;
import com.miaosha.response.CommonReturnType;
import com.miaosha.service.MedicineTableService;
import com.miaosha.service.model.MedicineTableModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class MedicineTableServiceImpl implements MedicineTableService {
    @Autowired
    private MedicineTableDOMapper medicineTableDOMapper;
    @Override
    public List<MedicineTableModel> listMedicineTable() {
        List<MedicineTableDO> medicineTableDOList = medicineTableDOMapper.selectAll();
        List<MedicineTableModel> medicineTableModelList = new LinkedList<>();
        for (MedicineTableDO m: medicineTableDOList
             ) {
            medicineTableModelList.add(convertFromMTDO(m));
        }
        return medicineTableModelList;
    }

    @Override
    public Boolean deleteOne(Integer id) {
        int deleteNum = medicineTableDOMapper.deleteByPrimaryKey(id);
        return deleteNum > 0;
    }

    private MedicineTableModel convertFromMTDO(MedicineTableDO medicineTableDO) {
        if (medicineTableDO == null) {
            return null;
        }
        MedicineTableModel medicineTableModel = new MedicineTableModel();
        BeanUtils.copyProperties(medicineTableDO, medicineTableModel);
        return medicineTableModel;
    }
}
