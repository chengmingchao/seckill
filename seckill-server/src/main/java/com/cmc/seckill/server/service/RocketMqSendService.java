package com.cmc.seckill.server.service;

import cn.hutool.json.JSONObject;
import com.cmc.seckill.entity.ItemKillSuccess;
import com.cmc.seckill.entity.KillSuccessUserInfo;
import com.cmc.seckill.server.dao.ItemKillDao;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * RocketMQ发送消息服务
 */
@Slf4j
@Service
public class RocketMqSendService {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private ItemKillDao itemKillDao;

    @Value("${rocketmq.topic}")
    private String topic;

    public void sendKillSuccessMail(String orderNo) {
        log.info("秒杀成功异步发送邮件通知消息-准备发送消息：{}", orderNo);
        if (!StringUtils.isBlank(orderNo)) {
            KillSuccessUserInfo killSuccessUserInfo = itemKillDao.selectByCode(orderNo);
            if (killSuccessUserInfo!=null){
                //用户名，封装邮件内容时使用
                String userName = killSuccessUserInfo.getUserName();
                //指定发送到哪个邮箱 (邮件接收者邮箱)
                String email = killSuccessUserInfo.getEmail();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userName",userName);
                jsonObject.put("email",email);
                //将发送的消息转换为Json字符串
                String msg = jsonObject.toString();
                log.info("生产者向消费者发送内容：" +  msg);
                rocketMQTemplate.convertAndSend(topic,msg);
            }
        }
    }

    public void sendKillItemByMq(ItemKillSuccess itemKillSuccess){
        rocketMQTemplate.convertAndSend(topic,itemKillSuccess);
    }
}
