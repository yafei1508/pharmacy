package com.miaosha.service.impl;

import com.miaosha.dao.ItemDAOMapper;
import com.miaosha.dao.ItemStockDAOMapper;
import com.miaosha.dataobject.ItemDAO;
import com.miaosha.dataobject.ItemStockDAO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.service.ItemService;
import com.miaosha.service.model.ItemModel;
import com.miaosha.validator.ValidationResult;
import com.miaosha.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private ItemDAOMapper itemDAOMapper;

    @Autowired
    private ItemStockDAOMapper itemStockDAOMapper;

    private ItemDAO covertItemDAOFromItemModel(ItemModel itemModel) {
        if(itemModel == null) {
            return null;
        }
        ItemDAO itemDAO = new ItemDAO();
        BeanUtils.copyProperties(itemModel, itemDAO);
        return itemDAO;
    }

    private ItemStockDAO convertItemStockDAOFromItemModel(ItemModel itemModel) {
        if(itemModel == null) {
            return null;
        }
        ItemStockDAO itemStockDAO = new ItemStockDAO();
        itemStockDAO.setItemId(itemModel.getId());
        itemStockDAO.setStock(itemModel.getStock());
        return itemStockDAO;
    }

    @Override
    @Transactional
    public ItemModel createItem(ItemModel itemModel) throws BusinessException{
        //校验入参
        ValidationResult validationResult = validator.validate(itemModel);
        if(validationResult.isHasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, validationResult.getErrMsg());
        }

        //转化ItemModel-》DataModel

        ItemDAO itemDAO = this.covertItemDAOFromItemModel(itemModel);
        itemDAOMapper.insertSelective(itemDAO);
        itemModel.setId(itemDAO.getId());

        ItemStockDAO itemStockDAO = this.convertItemStockDAOFromItemModel(itemModel);
        itemStockDAOMapper.insertSelective(itemStockDAO);

        return this.getItemById(itemModel.getId());


    }

    @Override
    public List<ItemModel> listItem() {
        System.out.println("开始");
        List<ItemDAO> itemDAOList = itemDAOMapper.listItem();
        List<ItemDAO> c = itemDAOList;
        List<ItemModel> itemModelList = itemDAOList.stream().map(itemDAO -> {
            ItemStockDAO itemStockDAO = itemStockDAOMapper.selectByItemId(itemDAO.getId());
            ItemModel itemModel = this.convertModelFromDataObject(itemDAO,itemStockDAO);
            return itemModel;
        }).collect(Collectors.toList());
        System.out.println("wacheng");
        return itemModelList;
    }

    @Override
    public ItemModel getItemById(Integer id) {

        ItemDAO itemDAO = itemDAOMapper.selectByPrimaryKey(id);
        if(itemDAO == null) {
            return null;
        }

        //获得库存的数量
        ItemStockDAO itemStockDAO = itemStockDAOMapper.selectByItemId(itemDAO.getId());
        ItemModel itemModel = this.convertModelFromDataObject(itemDAO, itemStockDAO);
        return itemModel;

    }

    @Override
    public boolean decreaseStock(Integer id, Integer amount) {
        int affectRow = itemStockDAOMapper.descreaseStock(id, amount);
        if(affectRow > 0) {
            return true;
        }
        else {
            return false;
        }

    }


    private ItemModel convertModelFromDataObject(ItemDAO itemDAO, ItemStockDAO itemStockDAO) {
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDAO,itemModel);
        itemModel.setStock(itemStockDAO.getStock());
        return itemModel;
    }
}
