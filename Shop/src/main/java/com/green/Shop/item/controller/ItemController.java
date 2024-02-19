package com.green.Shop.item.controller;

import com.green.Shop.item.service.ItemServiceImpl;
import com.green.Shop.item.vo.ImgVO;
import com.green.Shop.item.vo.ItemVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
//   상품과 관련된 모든 페이지 이동 처리 컨트롤러
    @Resource(name="itemService")
    private ItemServiceImpl itemService;
//    상품 목록 페이지
    @GetMapping("list")
    public String list(Model model){
        List<ItemVO> itemList = itemService.selectItemList();
        model.addAttribute("itemList",itemList);



        return "content/item/item_list";
    }
    @GetMapping("/selectDetail")
    public String selectDetail(ItemVO itemVO, Model model){
        ItemVO item = itemService.selectDetail(itemVO);
        model.addAttribute("item",item);
        return "content/item/item_detail1";
    }

}
