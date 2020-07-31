package com.cmc.seckill.server.controller;

import com.cmc.seckill.api.ItemService;
import com.cmc.seckill.entity.CommonResult;
import com.cmc.seckill.entity.Item;
import com.cmc.seckill.entity.ResponseCode;
import com.fasterxml.classmate.members.ResolvedParameterizedMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ItemController {

    @Autowired
    private ItemService itemService;
    /**
     * 获取商品信息
     * @return
     */
    @GetMapping("/item/getList")
    public CommonResult list(){
        List<Item> itemList = itemService.findItemList();
        log.info("查询成功："+itemList);
        return new CommonResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),itemList);
    }

    /**
     * 获取待秒杀商品详情信息
     * @param id
     * @return
     */
    @GetMapping("/item/detail/{id}")
    public CommonResult detail(@PathVariable Integer id){
        if (id==null){
            return new CommonResult(ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getMessage(),null);
        }else {
            Item item = itemService.findItemById(id);
            return new CommonResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),item);
        }
    }
}
