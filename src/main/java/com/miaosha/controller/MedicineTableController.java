package com.miaosha.controller;

import com.miaosha.response.CommonReturnType;
import com.miaosha.service.DrugOfTableService;
import com.miaosha.service.MedicineTableService;
import com.miaosha.service.impl.MedicineTableServiceImpl;
import com.miaosha.service.model.MedicineTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType list() {
        List<MedicineTableModel> medicineTableModel = medicineTableService.listMedicineTable();
        return CommonReturnType.create(medicineTableModel);
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


}
