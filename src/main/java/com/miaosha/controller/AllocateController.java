package com.miaosha.controller;

import com.alibaba.fastjson.JSON;
import com.miaosha.controller.viewobject.DrugVO;
import com.miaosha.controller.viewobject.SatDetailsVO;
import com.miaosha.dataobject.SatDetailsDO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.response.CommonReturnType;
import com.miaosha.service.DrugService;
import com.miaosha.service.SatService;
import com.miaosha.service.model.DrugModel;
import com.miaosha.service.model.SatModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller("allocateCtrl")
@RequestMapping("/allocateCtrl")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class AllocateController {

    @Autowired
    private DrugService drugService;

    @Autowired
    private SatService satService;

    // 显示药品数据
    @RequestMapping(value= "/showDrugs",method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType showDrugList(@RequestParam(name = "index") Integer index) throws BusinessException{
        List<DrugModel> drugModelList= drugService.listDrugs();
        if(drugModelList == null || drugModelList.size() == 0) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }


        List<DrugVO> drugVOList = new LinkedList<>();
        for(DrugModel drugModel:drugModelList){
            DrugVO drugVO = convertFromDMToDVO(drugModel);
            drugVOList.add(drugVO);
        }
        return CommonReturnType.create(drugVOList);
    }


    @RequestMapping(value="/applyMedicine", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType handleApplication(@RequestParam(name = "applyTable") String applyTable)throws BusinessException{
        //[{"drugId":19,"num":34}]这种格式的
        System.out.println(applyTable);

        // 把接受数据转化为对象List

        List<SatDetailsVO> testVOList = JSON.parseArray(applyTable,SatDetailsVO.class);

        SatModel satModel = new SatModel();
        List<SatDetailsDO> testDOList = new LinkedList<>();


        // 根据申请表处理结果选择是否进一步处理
        SatService.ApplyResult applyResult = satService.getApplyResult(testVOList);
        if(applyResult.getResult()){
            //TODO 本部门的ID怎么获取待定
            Integer myDepId = 3;
            satModel.setSatInfo((satService.countSatEntryNums() + 1), 1,applyResult.getOutDepId(), myDepId);
            // 把接受到的List转换为DO规定的格式,并添加到SatModel中
            for(SatDetailsVO satDetailsVO: testVOList){
                SatDetailsDO satDetailsDO = convertFromSatVO2SatDO(satDetailsVO);
                satDetailsDO.setSatId(satModel.getSatInfo().getId());
                testDOList.add(satDetailsDO);
            }
            satModel.setSatDetails(testDOList);

            satService.addSat(satModel.getSatInfo());

            for(SatDetailsDO satDetailsDO:satModel.getSatDetails()){
                satService.addSatDetails(satDetailsDO);
            }
            //检测是否成功创造了satModel
            System.out.println(JSON.toJSONString(satModel));

            return CommonReturnType.create(satModel.getSatInfo());
            //成功的回复结果
        }else{
            //失败的回复结果
//            System.out.println(applyResult.getResult());
            return CommonReturnType.create(applyResult,"refused");
        }
    }



    private  DrugVO convertFromDMToDVO(DrugModel drugModel) {
        if( drugModel == null) {
            return null;
        }
        DrugVO drugVO = new DrugVO();
        BeanUtils.copyProperties(drugModel,drugVO);
        return drugVO;
    }

    private SatDetailsDO convertFromSatVO2SatDO(SatDetailsVO satDetailsVO){
        if( satDetailsVO == null){
            return null;
        }
        SatDetailsDO satDetailsDO = new SatDetailsDO();
        BeanUtils.copyProperties(satDetailsVO, satDetailsDO);
        return satDetailsDO;
    }
}
