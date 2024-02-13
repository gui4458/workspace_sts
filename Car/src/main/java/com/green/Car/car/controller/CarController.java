package com.green.Car.car.controller;

import com.green.Car.car.service.CarServiceImpl;
import com.green.Car.car.vo.CarVO;
import com.green.Car.sales.service.SalesServiceImpl;
import com.green.Car.sales.vo.SalesVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {
    @Resource(name="carService")
    private CarServiceImpl carService;
    @Resource(name="salesService")
    private SalesServiceImpl salesService;
    @GetMapping("/")
    public String carMain(){

        return "content/car/car_main";
    }
    @GetMapping("/carRegistration")
    public String carRegistration(Model model){
        List<CarVO> carInfoList = carService.selectCarList();
        model.addAttribute("carInfoList",carInfoList);
        System.out.println(carInfoList);
        return "content/car/car_registration";
    }

    @PostMapping("/insertCar")
    public String insertCar(CarVO carVO){
        carService.insertCar(carVO);
        return "redirect:/car/carRegistration";
    }

    @GetMapping("/salesInfo")
    public String salesInfo(Model model){
        List<CarVO> carInfoList = carService.selectCarList();
        model.addAttribute("carInfoList",carInfoList);
        return "content/sales/sales_info";
    }




}
