package com.cmc.seckill.server.controller;

import com.cmc.seckill.entity.CommonResult;
import com.cmc.seckill.entity.ItemKillSuccess;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeckillController {


    @PostMapping("/sckill")
    public CommonResult seckill(ItemKillSuccess itemKillSuccess){


        return new CommonResult();
    }
}
