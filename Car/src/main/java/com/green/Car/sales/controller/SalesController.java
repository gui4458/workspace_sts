package com.green.Car.sales.controller;

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
@RequestMapping("/sales")
public class SalesController {
    @Resource(name="salesService")
    private SalesServiceImpl salesService;


    // 판매목록
    @GetMapping("/salesList")
    public String salesList(Model model){
        List<SalesVO> salesList = salesService.salesList();
        model.addAttribute("salesList",salesList);
        System.out.println(salesList);
        return "content/sales/sales_list";
    }
    //    판매 정보 등록
    @PostMapping("/insertSales")
    public String insertSales(SalesVO salesVO){
        salesService.insertSales(salesVO);
        return "redirect:/sales/salesList";
    }



}
