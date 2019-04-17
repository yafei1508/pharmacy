package com.miaosha.controller;

import com.miaosha.controller.viewobject.DrugVO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.response.CommonReturnType;
import com.miaosha.service.DrugService;
import com.miaosha.service.model.DrugModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("drug")
@RequestMapping(value = "/drug")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class DrugController extends BaseController{
    @Autowired
    private DrugService drugService;
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType getDrugById(@RequestParam(name = "id") Integer id) throws BusinessException {
        DrugModel drugModel = drugService.getDrugModelById(id);
        if(drugModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"没有这个drug");
        }
        DrugVO drugVO = convertFromDrugModelToVO(drugModel);
        System.out.print(drugVO.getName() + drugVO.getDescription());
        return CommonReturnType.create(drugVO);
    }

    private DrugVO convertFromDrugModelToVO(DrugModel drugModel) {
        if(drugModel == null) {
            return null;
        }
        DrugVO drugVO = new DrugVO();
        BeanUtils.copyProperties(drugModel, drugVO);
        return drugVO;
    }
}
