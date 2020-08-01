package com.cmc.seckill.server.controller;

import com.cmc.seckill.api.ItemKillService;
import com.cmc.seckill.entity.CommonResult;
import com.cmc.seckill.entity.ItemKillSuccess;
import com.cmc.seckill.entity.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeckillController {

    @Autowired
    private ItemKillService itemKillService;

    @PostMapping("/item/seckill")
    public CommonResult seckill(@RequestBody ItemKillSuccess itemKillSuccess){
        Boolean result = itemKillService.SeckillItem(itemKillSuccess.getKillId(), itemKillSuccess.getUserId());
        if (result){
            return new CommonResult(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMessage());
        }else {
            return new CommonResult(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMessage());
        }
    }

    @PostMapping("/item/seckill/mq")
    public CommonResult seckillMq(@RequestBody ItemKillSuccess itemKillSuccess){
        itemKillService.SeckillItemByMq(itemKillSuccess);
        return new CommonResult(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMessage());
    }
}
