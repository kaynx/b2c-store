package com.org.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.org.pojo.Address;
import com.org.user.mapper.AddressMapper;
import com.org.user.service.AddressService;
import com.org.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈晨
 * @date 2023/5/17
 */
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public R list(Integer userId) {
        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();
        addressQueryWrapper.eq("user_id", userId);
        List<Address> addressList = addressMapper.selectList(addressQueryWrapper);
        log.info("AddressServiceImpl.list()业务结束,结果:{}", "地址列表查询成功");
        return R.ok("查询成功", addressList);
    }

    @Override
    public R save(Address address) {
        int rows = addressMapper.insert(address);
        if (rows == 0) {
            log.info("AddressServiceImpl.save()业务结束,结果:{}", "插入地址失败");
            return R.fail("插入地址失败");
        }
        return list(address.getUserId());
    }

    @Override
    public R remove(Integer id) {
        int rows = addressMapper.deleteById(id);
        if (rows == 0) {
            log.info("AddressServiceImpl.remove()业务结束,结果:{}", "删除地址失败");
            return R.fail("删除地址失败");
        }
        return R.ok("删除地址成功");
    }
}
