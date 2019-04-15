package com.miaosha.controller.viewobject;

public class DrugOfTableVO {
    // id
    private Integer id;
    // 摆药单id
    private Integer medicineTableId;
    // 药品id
    private DrugVO drug;
    // 药品剂量
    private Integer dose;
    // 医嘱中对每个药品的的各种注意事项
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public DrugVO getDrug() {
        return drug;
    }

    public void setDrug(DrugVO drug) {
        this.drug = drug;
    }

    public Integer getDose() {
        return dose;
    }

    public void setDose(Integer dose) {
        this.dose = dose;
    }
}
