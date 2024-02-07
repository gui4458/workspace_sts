package com.green.Car.sales.controller;

import com.green.Car.sales.service.SalesServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sales")
public class SalesController {
    @Resource(name="salesService")
    private SalesServiceImpl salesService;
}
