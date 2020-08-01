package com.cmc.seeckill.admin.controller;

import com.cmc.seckill.entity.CommonResult;
import com.cmc.seckill.entity.ItemKillSuccess;
import com.cmc.seeckill.admin.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
    @Autowired
    private FeignService feignService;

    /**
     * 获取商品信息
     * @return
     */
    @GetMapping("/admin/getItemList")
    private CommonResult get(){
        return feignService.list();
    }

    /**
     * 获取待秒杀商品信息
     * @param id
     * @return
     */
    @GetMapping("/admin/detail/{id}")
    public CommonResult detail(@PathVariable(value = "id") Integer id){
        return feignService.detail(id);
    }

    /**
     * 商品秒杀
     * @param itemKillSuccess
     * @return
     */
    @PostMapping("/admin/seckill")
    public CommonResult seckill(@RequestBody ItemKillSuccess itemKillSuccess){
        return feignService.seckill(itemKillSuccess);
    }
}
