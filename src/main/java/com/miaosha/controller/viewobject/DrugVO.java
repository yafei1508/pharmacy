package com.miaosha.controller.viewobject;

import java.math.BigDecimal;

public class DrugVO {
    private Integer id;
    private String name;
    private String unit;
    private BigDecimal price;
    private String description;
    private Integer priority;
    //申请数量
    private Integer applyNums=0;
    private Integer stock;

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public Integer getApplyNums() {
        return applyNums;
    }

    public void setApplyNums(Integer applyNums) {
        this.applyNums = applyNums;
    }
}
