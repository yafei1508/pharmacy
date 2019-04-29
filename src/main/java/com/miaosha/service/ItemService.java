package com.miaosha.service;

import com.miaosha.error.BusinessException;
import com.miaosha.service.model.ItemModel;
import com.miaosha.validator.ValidationResult;

import java.util.List;

public interface ItemService {



    //创建商品
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    //得到商品列表
    List<ItemModel> listItem();

    // 商品详情浏览
    ItemModel getItemById(Integer id);


    boolean decreaseStock(Integer id, Integer amount);
}
