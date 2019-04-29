package com.miaosha.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.miaosha.controller.viewobject.DrugStockOutVO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.response.CommonReturnType;
import com.miaosha.service.DeptService;
import com.miaosha.service.StockOutTableService;
import com.miaosha.service.model.DeptModel;
import com.miaosha.service.model.DrugOfStockOutTableModel;
import com.miaosha.service.model.StockOutTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller("stockout")
@RequestMapping("/stockout")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class StockOutController {
    @Autowired
    private StockOutTableService stockOutTableService;

    @Autowired
    private DeptService deptService;
    @RequestMapping(value = "/deptlist", method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType getDeptList() throws BusinessException {
        List<DeptModel> list = deptService.getDeptList();
        if(list == null) {
            throw new BusinessException(EmBusinessError.DATABASE_OPTION_ERROR);
        }
        return CommonReturnType.create(list);
    }




    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonReturnType add(@RequestParam("itemList") String itemList,
                                @RequestParam("out") Integer outDeptId,
                                @RequestParam("in") Integer inDeptId) throws BusinessException {

        List<DrugStockOutVO> list = JSON.parseArray(itemList, DrugStockOutVO.class);
        StockOutTableModel stockOutTableModel = new StockOutTableModel();
        stockOutTableModel.setAddTime(new Date());
        stockOutTableModel.setStockinDeptId(inDeptId);
        stockOutTableModel.setStockoutDeptId(outDeptId);
        List<DrugOfStockOutTableModel> modelList = new LinkedList<>();
        for (DrugStockOutVO vo : list) {
            DrugOfStockOutTableModel model = new DrugOfStockOutTableModel();
            model.setDrugId(vo.getId());
            model.setNum(vo.getNum());
            modelList.add(model);
        }
        stockOutTableModel.setDrugOfStockOutTableModelList(modelList);
        stockOutTableService.addTable(stockOutTableModel);
        return CommonReturnType.create(null);
    }
}
