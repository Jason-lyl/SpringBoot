package com.maioshaproject.service.model;

import java.math.BigDecimal;

//用户下单的交易模型
public class OrderModel {

    private String id;

    //购买的用户id
    private Integer userId;

    //购买的商品id
    private Integer itemId;

    //购买商品的单价
    private Integer itemPrice;

    //购买数量
    private Integer amount;

    //购买金额
    private BigDecimal oriderPrice;

    public OrderModel() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getOriderPrice() {
        return oriderPrice;
    }

    public void setOriderPrice(BigDecimal oriderPrice) {
        this.oriderPrice = oriderPrice;
    }
}

