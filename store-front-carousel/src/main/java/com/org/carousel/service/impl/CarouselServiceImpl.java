package com.org.carousel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.org.carousel.mapper.CarouselMapper;
import com.org.carousel.service.CarouselService;
import com.org.pojo.Carousel;
import com.org.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 陈晨
 * @date 2023/5/17
 */
@Service
@Slf4j
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Cacheable(value = "list.carousel", key = "#root.methodName", cacheManager = "cacheManagerDay")
    @Override
    public R list() {
        QueryWrapper<Carousel> carouselQueryWrapper = new QueryWrapper<>();
        carouselQueryWrapper.orderByDesc("priority");
        List<Carousel> carouselList = carouselMapper.selectList(carouselQueryWrapper);
        List<Carousel> collect = carouselList.stream().limit(4).collect(Collectors.toList());
        R ok = R.ok(collect);
        log.info("CarouselServiceImpl.list()业务结束,结果:{}", ok);
        return ok;
    }
}
