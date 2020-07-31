package com.cmc.seckill.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(value = "com.cmc.seckill.server.dao")
public class SeckillMain {
    public static void main(String[] args) {
        SpringApplication.run(SeckillMain.class,args);
    }
}
