package com.green.Shop.item.controller;

import com.green.Shop.item.service.ItemServiceImpl;
import com.green.Shop.item.vo.ImgVO;
import com.green.Shop.item.vo.ItemVO;
import com.green.Shop.study.fetch.controller.MemberVO;
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






    @GetMapping("/test1")
    public String test1(Model model){
        System.out.println("test1() 메서드 실행");
        model.addAttribute("age",30);
        model.addAttribute("name","hong");
        model.addAttribute("memberVO",new MemberVO());
        return "/content/member/login";
    }
    @GetMapping("/test2")
    public String test2(){
        System.out.println("test2() 메서드 실행");

        return "";
    }
    @GetMapping("/test3")
    public String test3(){
        System.out.println("test3() 메서드 실행");

        return "";
    }






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
