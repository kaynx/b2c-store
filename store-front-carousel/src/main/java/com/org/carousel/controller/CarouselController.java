package com.org.carousel.controller;

import com.org.carousel.service.CarouselService;
import com.org.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈晨
 * @date 2023/5/17
 */
@RestController
@RequestMapping("carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;
    @PostMapping("list")
    public R list(){
        return carouselService.list();
    }
}
