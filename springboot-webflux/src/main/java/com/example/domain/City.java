package com.example.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 城市实体类
 */
@Data
public class City implements Serializable {


    private static final long serialVersionUID = -5596928918210386152L;
    /**
     * 城市编号
     */
    private Long id;

    /**
     * 省份编号
     */
    private Long provinceId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 描述
     */
    private String description;



}
