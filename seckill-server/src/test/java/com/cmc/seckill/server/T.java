package com.cmc.seckill.server;

import cn.hutool.json.JSONObject;
import com.cmc.seckill.entity.Item;
import com.cmc.seckill.server.service.ItemServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

@SpringBootTest(classes = SeckillMain.class)
@RunWith(SpringRunner.class)
@Slf4j
public class T {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Test
    public void test(){
        //用户名，封装邮件内容时使用
        String userName = "张三"+ new Random().nextInt(20);
        //指定发送到哪个邮箱 (邮件接收者邮箱)
        String email = "502130705@qq.com";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName",userName);
        jsonObject.put("email",email);
        //将发送的消息转换为Json字符串
        String msg = jsonObject.toString();
        log.info("生产者向消费者发送内容：" +  msg);
        rocketMQTemplate.convertAndSend("springboot-mq",msg);
    }

}
