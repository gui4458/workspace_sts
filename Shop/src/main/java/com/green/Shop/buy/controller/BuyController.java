package com.green.Shop.buy.controller;
import com.green.Shop.buy.service.BuyServiceImpl;
import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.buy.vo.BuyVO;
import com.green.Shop.cart.service.CartServiceImpl;
import com.green.Shop.cart.vo.CartVO;
import com.green.Shop.cart.vo.CartViewVO;
import com.green.Shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/buy")
public class BuyController {
    @Resource(name="cartService")
    private CartServiceImpl cartService;

    @Resource(name="buyService")
    private BuyServiceImpl buyService;

    @GetMapping("/buyCarts")
    public String buyCarts(CartVO cartVO, HttpSession session){
//        구매 상세 테이블에 insert할 ITEM_CODE, BUY_CNT, TOTAL_PRICE 조회
        List<CartViewVO> cartViewList = cartService.selectCartListForBuy(cartVO);

//        구매 정보 테이블에 들어갈 BUY_PRICE(구매 총 가격)
        int buyPrice = 0;
        for (CartViewVO e :cartViewList){
            buyPrice += e.getTotalPrice();
        }
        BuyVO buyVO = new BuyVO();
//        구매자 ID
        MemberVO loginInfo =(MemberVO)session.getAttribute("loginInfo");
        String memberId = loginInfo.getMemberId();

//        다음에 들어갈 BUY_CODE 값
        int buyCode = buyService.selectNextBuyCode();
        buyVO.setMemberId(memberId);
        buyVO.setBuyCode(buyCode);
        buyVO.setBuyPrice(buyPrice);


        List<BuyDetailVO> buyDetailList = new ArrayList<>();
        for (CartViewVO e : cartViewList){
            BuyDetailVO vo = new BuyDetailVO();
            vo.setItemCode(e.getItemCode());
            vo.setBuyCnt(e.getCartCnt());
            vo.setTotalPrice(e.getTotalPrice());
            buyDetailList.add(vo);
        }

        buyVO.setBuyDetailList(buyDetailList);

        buyService.insertBuys(buyVO);




        return "redirect:/";
    }

    @PostMapping("/insertBuy")
    public String insertBuy(BuyVO buyVO,BuyDetailVO buyDetailVO,HttpSession session){
//      다음에 들어가야 하는 buy_code 값을 조회
        int buyCode = buyService.selectNextBuyCode();

        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        buyVO.setBuyCode(buyCode);
        buyVO.setMemberId(loginInfo.getMemberId());
        buyVO.setBuyPrice(buyDetailVO.getTotalPrice());
        buyDetailVO.setBuyCode(buyCode);
        buyService.insertBuy(buyVO,buyDetailVO);


        return "redirect:/";
    }

//    구매 이력 페이지
    @GetMapping("/history")
    public String history(Model model, HttpSession session){
        String page = "buyList";
        model.addAttribute("page",page);
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        List<BuyVO> buyInfo = buyService.selectBuyList(loginInfo.getMemberId());
        model.addAttribute("buyInfoList",buyInfo);
        return "content/buy/history";
    }




}
