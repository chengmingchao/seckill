package com.cmc.seckill.server.dao;

import com.cmc.seckill.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemDao {
    List<Item> findItemList();

    Item findItemById(@Param("id") Integer id);
}
