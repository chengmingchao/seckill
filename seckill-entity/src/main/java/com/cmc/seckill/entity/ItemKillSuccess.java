package com.cmc.seckill.entity;

import lombok.Data;
import java.util.Date;

@Data
public class ItemKillSuccess {
    private String code;

    private Integer itemId;

    private Integer killId;

    private String userId;

    private Integer status;

    private Date createTime;

    private Integer diffTime;

}