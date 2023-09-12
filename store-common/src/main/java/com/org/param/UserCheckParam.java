package com.org.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 陈晨
 * @date 2023/5/16
 */
@Data
public class UserCheckParam {
    @NotBlank
    private String userName;
}
