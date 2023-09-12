package com.org.pojo;

/**
 * @author 陈晨
 * @date 2023/5/17
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("carousel")
public class Carousel implements Serializable {

    public static final Long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("carousel_id")
    private Integer carouselId;

    private String imgPath;

    private String describes;

    @JsonProperty("product_id")
    private Integer productId;

    private Integer priority;

}

