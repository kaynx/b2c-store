package com.org.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 陈晨
 * @date 2023/5/18
 */
@TableName("category")
@Data
public class Category {
    @JsonProperty("category_id")
    @TableId(type = IdType.AUTO)
    private Integer categoryId;

    @JsonProperty("category_name")
    private String categoryName;
}
