package com.cmc.seckill.server.service;

import cn.hutool.core.date.DateTime;
import com.cmc.seckill.api.ItemKillService;
import com.cmc.seckill.entity.ItemKill;
import com.cmc.seckill.entity.ItemKillSuccess;
import com.cmc.seckill.server.dao.ItemKillDao;
import com.cmc.seckill.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ItemKillServiceImpl implements ItemKillService {

    @Autowired
    private ItemKillDao itemKillDao;

    private SnowFlake snowFlake = new SnowFlake(2, 3);

    /**
     * 商品秒杀逻辑
     * @param killId
     * @param userId
     * @return
     */
    @Override
    public Boolean SeckillItem(Integer killId, Integer userId) {
        Boolean result = false;
        //判断当前用户是否已秒杀
        Integer count = itemKillDao.countByUserId(killId, userId);
        if (count <= 0) {
            //判断商品秒杀库存充足，以及是否出现在可抢的时间段内
            ItemKill itemKill = itemKillDao.selectById(killId);
            if (itemKill != null && itemKill.getCanKill() == 1) {
                //扣减库存
                int i = itemKillDao.updateKillItem(killId);
                if (i > 0) {
                    //秒杀成功，保存秒杀订单，同时通知用户秒杀成功
                    this.commonRecordKillSuccessInfo(itemKill, userId);
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * 通用的方法-记录用户秒杀成功后生成的订单-并进行异步邮件消息的通知
     *
     * @param kill
     * @param userId
     * @throws Exception
     */
    private void commonRecordKillSuccessInfo(ItemKill kill, Integer userId) {
        //TODO:记录抢购成功后生成的秒杀订单记录

        ItemKillSuccess entity = new ItemKillSuccess();
        String orderNo = String.valueOf(snowFlake.nextId());

        //entity.setCode(RandomUtil.generateOrderCode());   //传统时间戳+N位随机数
        entity.setCode(orderNo); //雪花算法
        entity.setItemId(kill.getItemId());
        entity.setKillId(kill.getId());
        entity.setUserId(userId.toString());
        entity.setStatus(0);
        entity.setCreateTime(new Date());
        //TODO:学以致用，举一反三 -> 仿照单例模式的双重检验锁写法
        if (itemKillDao.countByUserId(kill.getId(), userId) <= 0) {
            int res = itemKillDao.insertItemKillSuccess(entity);

            if (res > 0) {
                //TODO:进行异步邮件消息的通知=rabbitmq+mail
//                rabbitSenderService.sendKillSuccessEmailMsg(orderNo);

                //TODO:入死信队列，用于 “失效” 超过指定的TTL时间时仍然未支付的订单
//                rabbitSenderService.sendKillSuccessOrderExpireMsg(orderNo);
            }
        }
    }
}
