package com.cmc.seckill.server.service;

import com.cmc.seckill.api.ItemService;
import com.cmc.seckill.entity.Item;
import com.cmc.seckill.server.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public List<Item> findItemList() {
        return itemDao.findItemList();
    }

    @Override
    public Item findItemById(Integer id) {
        return itemDao.findItemById(id);
    }
}
