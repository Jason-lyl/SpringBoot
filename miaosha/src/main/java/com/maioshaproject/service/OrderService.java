package com.maioshaproject.service;

import com.maioshaproject.service.model.OrderModel;

public interface OrderService {

    OrderModel createOrder(Integer userId, Integer itemId, Integer amount);
}
