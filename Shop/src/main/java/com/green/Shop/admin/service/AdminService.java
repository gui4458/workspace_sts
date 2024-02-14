package com.green.Shop.admin.service;

import com.green.Shop.buy.vo.BuyVO;
import com.green.Shop.item.vo.CategoryVO;
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
// 상품 정보 변경 화면에서 상품 목록 조회
    List<ItemVO> selectUpdateInfo();
//    클릭한아이템정보
    ItemVO clickItemInfo(ItemVO itemVO);

    List<CategoryVO> cateName();

    void updateItem(ItemVO itemVO);
}
