package com.org.admin.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author 陈晨
 * @date 2023/5/30
 */
@Data
public class AdminUserParam {

    @Length(min = 6)
    private String userAccount;
    @Length(min = 6)
    private String userPassword;
    @NotBlank
    private String verCode;

}
