package com.miaosha.service.impl;

import com.miaosha.dao.OrderDAOMapper;
import com.miaosha.dao.SequenceInfoDAOMapper;
import com.miaosha.dataobject.OrderDAO;
import com.miaosha.dataobject.SequenceInfoDAO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.service.ItemService;
import com.miaosha.service.OrderService;
import com.miaosha.service.UserService;
import com.miaosha.service.model.ItemModel;
import com.miaosha.service.model.OrderModel;
import com.miaosha.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.EnumMap;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private SequenceInfoDAOMapper sequenceInfoDAOMapper;
    @Autowired
    private OrderDAOMapper orderDAOMapper;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;


    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException {

        //1,校验下单状态
        ItemModel itemModel = itemService.getItemById(itemId);
        if(itemModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商品信息不存在");
        }
        UserModel userModel = userService.getUserById(userId);
        if(userModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户不存在");
        }

        if(amount <= 0 || amount > 99) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "数量信息不正确");
        }


        //2,落单减库存
        boolean result = itemService.decreaseStock(itemId, amount);
        if(!result) {
            throw new BusinessException(EmBusinessError.STOCK_ERROR,"库存不足");
        }

        //订单入库
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setAmount(amount);
        orderModel.setItemId(itemId);
        orderModel.setItemPrice(itemModel.getPrice());
        orderModel.setOrderPrice(itemModel.getPrice().multiply(new BigDecimal(amount)));
        orderModel.setId(generateOrderNo());
        //生成交易流水号
        OrderDAO orderDAO = convertOrderDAOFromOrderModel(orderModel);


        orderDAOMapper.insertSelective(orderDAO);
        //返回前端
        return orderModel;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    //生成订单号
    private String generateOrderNo(){
        StringBuilder builder = new StringBuilder();
        //订单号十六位
        //前8位为时间信息
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");

        builder.append(nowDate);


        //中间六位为自增序列

        //获取当前sequence
        SequenceInfoDAO sequenceInfoDAO = sequenceInfoDAOMapper.getSequenceByName("order_info");
        int sequence = 0;
        sequence = sequenceInfoDAO.getCurrentValue();
        sequenceInfoDAO.setCurrentValue(sequenceInfoDAO.getStep() + sequenceInfoDAO.getCurrentValue());
        sequenceInfoDAOMapper.updateByPrimaryKeySelective(sequenceInfoDAO);
        String sequenceStr = String.valueOf(sequence);
        for(int i = 0;i<6 - sequenceStr.length();i++) {
            builder.append("0");
        }
        builder.append(sequenceStr);
        //最后两位为分库分表位 用于数据库水平拆分
        builder.append("00");
        return builder.toString();
    }
    private OrderDAO convertOrderDAOFromOrderModel(OrderModel orderModel) {
        if(orderModel == null) {
            return null;
        }
        OrderDAO orderDAO = new OrderDAO();
        BeanUtils.copyProperties(orderModel,orderDAO);
        return orderDAO;

    }
}
