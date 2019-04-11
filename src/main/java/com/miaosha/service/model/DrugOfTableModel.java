package com.miaosha.service.model;

public class DrugOfTableModel {
    // id
    private Integer id;
    // 摆药单id
    private Integer medicineTableId;
    // 摆药单id
    private Integer drugId;
    // 药品剂量
    private Integer dose;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedicineTableId() {
        return medicineTableId;
    }

    public void setMedicineTableId(Integer medicineTableId) {
        this.medicineTableId = medicineTableId;
    }

    public Integer getDrugId() {
        return drugId;
    }

    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }

    public Integer getDose() {
        return dose;
    }

    public void setDose(Integer dose) {
        this.dose = dose;
    }
}
