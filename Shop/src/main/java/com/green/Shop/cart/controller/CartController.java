package com.green.Shop.cart.controller;

import com.green.Shop.cart.service.CartServiceImpl;
import com.green.Shop.cart.vo.CartVO;
import com.green.Shop.cart.vo.CartViewVO;
import com.green.Shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Resource(name="cartService")
    private CartServiceImpl cartService;
//  장바구니 상품 등록
    @ResponseBody
    @PostMapping("/insertCart")
    public void insertCart(CartVO cartVO, HttpSession session){
        MemberVO info = (MemberVO)session.getAttribute("loginInfo");
        cartVO.setMemberId(info.getMemberId());


        cartService.insertCart(cartVO);
    }
//  장바구니 목록 조회
    @GetMapping("/list")
    public String list(Model model,HttpSession session
                    ,@RequestParam(name="page", required = false, defaultValue = "cartList")String page){
        MemberVO loginInfo =(MemberVO)session.getAttribute("loginInfo");
        List<CartViewVO> items = cartService.selectCart(loginInfo.getMemberId());
        model.addAttribute("items",items);

//        총 가격을 계산한 후 html 전달
        int sum = 0;
        for (CartViewVO e : items){
            sum += e.getTotalPrice();
        }
        model.addAttribute("sum",sum);
        model.addAttribute("page",page);
        return "content/cart/cart_list";
    }
//    삭제
    @GetMapping("/delete")
    public String delete(@RequestParam(name="cartCode")int cartCode){
        cartService.delete(cartCode);
        return "redirect:/cart/list";
    }
//    장바구니 수량 업데이트
    @ResponseBody
    @PostMapping("/cartUpdate")
    public void cartUpdate(CartVO cartVO){
        cartService.cartUpdate(cartVO);

    }

    @GetMapping("/deleteCarts")
    public String deleteCarts(CartVO cartVO) {
                                                        //  ==   ArrayList<Integer> cartCodes

        cartService.deleteCarts(cartVO);

        return "redirect:/cart/list";
    }
}
