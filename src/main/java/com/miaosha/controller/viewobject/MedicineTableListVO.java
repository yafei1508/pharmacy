package com.miaosha.controller.viewobject;

import java.util.List;

public class MedicineTableListVO {
    private Integer index;
    private Integer total;
    private List<MedicineTableVO> list;

    public MedicineTableListVO(Integer index, Integer total, List<MedicineTableVO> list) {
        this.index = index;
        this.total = total;
        this.list = list;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<MedicineTableVO> getList() {
        return list;
    }

    public void setList(List<MedicineTableVO> list) {
        this.list = list;
    }
}
