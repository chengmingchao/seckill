package com.cmc.seckill.server.service;

import com.cmc.seckill.entity.KillSuccessUserInfo;
import com.cmc.seckill.server.dao.ItemKillDao;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * RocketMQ发送消息服务
 */
@Slf4j
public class RocketMqSendService {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private ItemKillDao itemKillDao;

    public void sendKillSuccessMail(String orderNo) {
        log.info("秒杀成功异步发送邮件通知消息-准备发送消息：{}", orderNo);
        if (StringUtils.isBlank(orderNo)) {
            KillSuccessUserInfo killSuccessUserInfo = itemKillDao.selectByCode(orderNo);
            if (killSuccessUserInfo!=null){

            }
        }
    }
}
