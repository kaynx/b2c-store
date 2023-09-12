package com.org.param;

import lombok.Data;

/**
 * @author 陈晨
 * @date 2023/5/21
 */
@Data
public class ProductSearchParam extends PageParam{
    private String search;


    /**
     * 运算分页起始值
     *
     * @return
     */
    public int getFrom() {
        return (super.getCurrentPage() - 1) * super.getPageSize();
    }

    /**
     * 返回查询值
     *
     * @return
     */
    public int getSize() {
        return  super.getPageSize();
    }
}
