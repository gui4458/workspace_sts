package com.green.Shop.admin.service;

import com.green.Shop.buy.vo.BuyVO;
import com.green.Shop.item.vo.ImgVO;
import com.green.Shop.item.vo.ItemVO;
import com.green.Shop.search.vo.SearchVO;

import java.util.List;

public interface AdminService {
//    상품등록 및 상품 이미지 등록
    void insertItem(ItemVO itemVO);

//    상품 이미지 등록

//    다음에 들어갈 ITEM_CODE 조회
    int selectNextItemCode();

    List<BuyVO> selectBuyList(SearchVO searchVO);

    BuyVO selectDetailBuyList(BuyVO buyVO);
}
