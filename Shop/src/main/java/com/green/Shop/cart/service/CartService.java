package com.green.Shop.cart.service;

import com.green.Shop.cart.vo.CartVO;
import com.green.Shop.cart.vo.CartViewVO;

import java.util.List;

public interface CartService {
//    장바구니에 상품 등록
    void insertCart(CartVO cartVO);
//  장바구니 목록 조회
    List<CartViewVO> selectCart(String memberId);

    void delete(int cartCode);

    void cartUpdate(CartVO cartVO);

    void deleteCarts(CartVO cartVO);

    List<CartViewVO> selectCartListForBuy(CartVO cartVO);

}
