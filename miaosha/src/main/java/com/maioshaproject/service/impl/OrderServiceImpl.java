package com.maioshaproject.service.impl;

import com.maioshaproject.service.ItemService;
import com.maioshaproject.service.OrderService;
import com.maioshaproject.service.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private ItemService itemService;

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer amount) {

        //1.校验下单状态,下单的商品是否存在，用户是否合法，购买数量是否正确



        //2.落单减库存，支付减库存

        //3.订单入库

        //4.返回前端


        return null;
    }
}
