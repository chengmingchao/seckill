package com.cmc.seckill.api;

import com.cmc.seckill.entity.ItemKillSuccess;

public interface ItemKillService {

    Boolean SeckillItem(Integer killId,Integer userId);

    Boolean SeckillItemByMq(ItemKillSuccess itemKillSuccess);
}
