package com.cmc.seeckill.admin.controller;

import com.cmc.seckill.entity.CommonResult;
import com.cmc.seeckill.admin.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public CommonResult detail(@PathVariable Integer id){
        return feignService.detail(id);
    }
}
