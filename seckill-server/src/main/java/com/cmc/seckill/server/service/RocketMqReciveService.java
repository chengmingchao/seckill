package com.cmc.seckill.server.service;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ExtRocketMQTemplateConfiguration;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * RocketMQ消费服务，消费信息发送邮件
 */

@Component
@RocketMQMessageListener(topic = "${rocketmq.topic}",consumerGroup = "${rocketmq.producer.group}")
@Slf4j
public class RocketMqReciveService implements RocketMQListener<String> {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void onMessage(String s) {
        log.info("收到消息："+s);
        JSONObject jsonObject = new JSONObject(s);
        String userName = jsonObject.getStr("userName");
        String email = jsonObject.getStr("email");
        log.info("userName-{},email-{}",userName,email);
        sendEmail(userName,email);
    }

    /**
     * 发送简单邮件
     * @param userName  用户名，用户封装邮件内容
     * @param toEmail 发送的邮箱地址
     * @return
     */
    public String sendEmail(String userName,String toEmail){
        //发送消息对象
        SimpleMailMessage message = new SimpleMailMessage();
        //发送者
        message.setFrom(fromEmail);
        //接收者
        message.setTo(toEmail);
        //主题(标题)
        message.setSubject("尊敬的"+userName);
        //内容
        message.setText("恭喜您抢到商品！");  //发送html，发送html格式源码
        javaMailSender.send(message);
        log.info("发送邮件完成："+new JSONObject(message));
        return  "send success!";
    }
}
