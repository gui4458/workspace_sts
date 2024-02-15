package com.green.Shop.buy.service;


import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.buy.vo.BuyVO;
import com.green.Shop.cart.vo.CartViewVO;

import java.util.List;

public interface BuyService {
//    다음에 들어갈 buy_code 조회
    int selectNextBuyCode();
//    장바구니 상품 구매
    void insertBuys(BuyVO buyVO);
//  바로 구매
    void insertBuy(BuyVO buyVO, BuyDetailVO buyDetailVO);

    List<BuyVO> selectBuyList(String memberId);
}
