package com.maioshaproject.server;

import com.maioshaproject.error.BusinessException;
import com.maioshaproject.server.model.ItemModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ItemService {

    //创建商品
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    //商品列表浏览
    List<ItemModel> listItem();

    //商品详情浏览
    ItemModel getItemById(Integer id);


}
