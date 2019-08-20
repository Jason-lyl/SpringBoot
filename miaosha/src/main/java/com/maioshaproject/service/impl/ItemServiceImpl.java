package com.maioshaproject.service.impl;

import com.maioshaproject.dao.ItemDOMapper;
import com.maioshaproject.dao.ItemStockDOMapper;
import com.maioshaproject.dataObject.ItemDO;
import com.maioshaproject.dataObject.ItemStockDO;
import com.maioshaproject.error.BusinessException;
import com.maioshaproject.error.EmBusinessError;
import com.maioshaproject.service.ItemService;
import com.maioshaproject.service.model.ItemModel;
import com.maioshaproject.validator.ValidationResult;
import com.maioshaproject.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl  implements ItemService  {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private ItemDOMapper itemDOMapper;

    @Autowired
    private ItemStockDOMapper itemStockDOMapper;

    private ItemDO convertItemDOFromItemModel(ItemModel itemModel){

        if (itemModel == null){
            return null;
        }

        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemModel, itemDO);
        itemDO.setPrice(itemModel.getPrice().doubleValue());
        return itemDO;
    };

    private ItemStockDO convertItemStockDOFromItemModel(ItemModel itemModel){
        if (itemModel == null){
            return  null;
        }

        ItemStockDO itemStockDO = new ItemStockDO();
        itemStockDO.setItemId(itemModel.getId());
        itemStockDO.setStock(itemModel.getStock());

        return itemStockDO;
    }


    @Override
    @Transactional
    public ItemModel createItems(ItemModel itemModel) throws BusinessException {

        //检验入参
        ValidationResult result = validator.validate(itemModel);
        if (result.isHasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, result.getErrMsg());
        }

        //转化itmeModel -> dataObject
        ItemDO itemDO = this.convertItemDOFromItemModel(itemModel);

        //写入数据库
        itemDOMapper.insertSelective(itemDO);
        itemModel.setId(itemDO.getId());

        ItemStockDO itemStockDO = this.convertItemStockDOFromItemModel(itemModel);
        itemStockDOMapper.insertSelective(itemStockDO);

        //返回创建完成的对象
        return  this.getItemById(itemModel.getId());
    }


    @Override
    public List<ItemModel> listItem() {
        List<ItemDO> itemDOList = itemDOMapper.listItem();
        List<ItemModel> itemModelList = itemDOList.stream().map(itemDO -> {
            ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
            ItemModel itemModel = this.convertModelFromDataObject(itemDO, itemStockDO);
            return itemModel;
        }).collect(Collectors.toList());
        return itemModelList;
    }

    @Override
    public ItemModel getItemById(Integer id) {
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
        if (itemDO == null){
            return  null;
        }
        //操作获得库存数量
       ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());

        //将dataObject -> model
        ItemModel itemModel = convertModelFromDataObject(itemDO, itemStockDO);

        return itemModel;
    }


    private ItemModel convertModelFromDataObject(ItemDO itemDO, ItemStockDO itemStockDO){

        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO, itemModel);
        itemModel.setPrice(new BigDecimal(String.valueOf(itemDO.getPrice())));
        itemModel.setStock(itemStockDO.getStock());
        return  itemModel;

    }
}
