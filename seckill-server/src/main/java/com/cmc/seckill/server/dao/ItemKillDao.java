package com.cmc.seckill.server.dao;

import com.cmc.seckill.entity.ItemKill;
import com.cmc.seckill.entity.ItemKillSuccess;
import com.cmc.seckill.entity.KillSuccessUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemKillDao {

    Integer countByUserId(@Param("killId") Integer killId,@Param("userId") Integer userId);

    ItemKill selectById(@Param("killId") Integer killId);

    int updateKillItem(@Param("killId") Integer killId);

    int insertItemKillSuccess(ItemKillSuccess itemKillSuccess);

    KillSuccessUserInfo selectByCode(@Param("code") String code);
}
