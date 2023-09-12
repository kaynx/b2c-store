package com.org.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.pojo.Order;
import com.org.vo.AdminOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 陈晨
 * @date 2023/5/29
 */
public interface OrderMapper extends BaseMapper<Order> {
    /**
     *
     * @param offSet
     * @param pageSize
     * @return
     */
    List<AdminOrderVo> selectAdminOrder(@Param("offSet") int offSet,@Param("pageSize") int pageSize);
}
