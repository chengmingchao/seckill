package com.cmc.seckill.api;

import com.cmc.seckill.entity.Item;

import java.util.List;

public interface ItemService {
    List<Item> findItemList();

    Item findItemById(Integer id);
}
