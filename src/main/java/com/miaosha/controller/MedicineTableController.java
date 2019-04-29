package com.miaosha.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.miaosha.controller.viewobject.DrugOfTableVO;
import com.miaosha.controller.viewobject.DrugVO;
import com.miaosha.controller.viewobject.MedicineTableListVO;
import com.miaosha.controller.viewobject.MedicineTableVO;
import com.miaosha.dataobject.DrugOfTableDO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.response.CommonReturnType;
import com.miaosha.service.DrugOfTableService;
import com.miaosha.service.DrugService;
import com.miaosha.service.MedicineTableService;
import com.miaosha.service.impl.MedicineTableServiceImpl;
import com.miaosha.service.model.DrugModel;
import com.miaosha.service.model.DrugOfTableModel;
import com.miaosha.service.model.MedicineTableModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.awt.EmbeddedFrame;

import java.util.LinkedList;
import java.util.List;

@Controller("medicinetable")
@RequestMapping("/medicinetable")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class MedicineTableController extends BaseController {

    @Autowired
    private MedicineTableService medicineTableService;
    @Autowired
    private DrugOfTableService drugOfTableService;
    @Autowired
    private DrugService drugService;

    // 处理获得摆药单列表

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType list(@RequestParam(name = "index") Integer index) throws BusinessException {
        List<MedicineTableModel> medicineTableModelList = medicineTableService.listMedicineTable();
        int total = medicineTableModelList.size();
        if(index < 1 || (total % 10 == 0 && index > total /10) || (total % 10 != 0 && index > total/10 +1)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        if(index * 10 <= total) {
            medicineTableModelList = medicineTableModelList.subList((index - 1) * 10, index * 10);
        }else {
            medicineTableModelList = medicineTableModelList.subList((index - 1) * 10, total);
        }
        List<MedicineTableVO> returnList = new LinkedList<>();
        for(MedicineTableModel mtm : medicineTableModelList) {
            returnList.add(convertFromMMToMVO(mtm));
        }
        MedicineTableListVO medicineTableListVO = new MedicineTableListVO(index,total,returnList);
        return CommonReturnType.create(medicineTableListVO);
    }



    // 处理获取摆药单详情

    @RequestMapping(value = "/detail", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getTableDetail(@RequestParam(name = "id") Integer id) throws BusinessException {
        List<DrugOfTableModel> drugOfTableModelList= drugOfTableService.getDrugOfTableByTableId(id);
        if(drugOfTableModelList == null || drugOfTableModelList.size() == 0) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        List<DrugOfTableVO> drugOfTableVOList = new LinkedList<>();
        for(DrugOfTableModel mode:drugOfTableModelList) {
            DrugOfTableVO drugOfTableVO = convertFromDTMToDTVO(mode);
            DrugVO drugVO = convertFromDMToDVO(drugService.getDrugModelById(mode.getDrugId()));
            drugOfTableVO.setDrug(drugVO);
            drugOfTableVOList.add(drugOfTableVO);
        }
        return CommonReturnType.create(drugOfTableVOList);
    }


    @RequestMapping(value = "/tableprocessed", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType checkMedicineTable(@RequestParam(name = "id") Integer id) {
        return CommonReturnType.create(null);
    }
    @RequestMapping(value = "/deletetable", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType deleteTable(@RequestParam(name = "id") Integer id) {
        return CommonReturnType.create(medicineTableService.deleteOne(id));
    }
    @RequestMapping(value = "/deletedrugoftable", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType deleteDrug(@RequestParam(name = "id") Integer id) {
        return CommonReturnType.create(drugOfTableService.deleteDrugOfTable(id));
    }

//    @RequestMapping(value = "/adddrugtotable", method = {RequestMethod.GET})
//    @ResponseBody
//    public CommonReturnType addDrugInTable(@RequestParam(name = "drug_id") Integer drug_id) {
//        return CommonReturnType.create(medicineTableService.deleteOne(id));
//    }



    // 各种内部接口用于不同对象之间的转换


    private MedicineTableVO convertFromMMToMVO(MedicineTableModel mtm) {
        if(mtm == null) {
            return null;
        }
        MedicineTableVO vo = new MedicineTableVO();
        BeanUtils.copyProperties(mtm,vo);
        return vo;
    }

    private DrugOfTableVO convertFromDTMToDTVO(DrugOfTableModel drugOfTableModel) {
        if(drugOfTableModel == null) {
            return null;
        }
        DrugOfTableVO drugOfTableVO = new DrugOfTableVO();
        BeanUtils.copyProperties(drugOfTableModel,drugOfTableVO);
        return drugOfTableVO;
    }
    private  DrugVO convertFromDMToDVO(DrugModel drugModel) {
        if( drugModel == null) {
            return null;
        }
        DrugVO drugVO = new DrugVO();
        BeanUtils.copyProperties(drugModel,drugVO);
        return drugVO;
    }
}
