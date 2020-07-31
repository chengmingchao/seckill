package com.cmc.seeckill.admin.service;

import com.cmc.seckill.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "seckill-server")
public interface FeignService {

    @GetMapping("/item/getList")
    CommonResult list();

    @GetMapping("/item/detail/{id}")
    CommonResult detail(@PathVariable Integer id);
}
