package com.green.Shop.item.service;

import com.green.Shop.item.vo.CategoryVO;
import com.green.Shop.item.vo.ImgVO;
import com.green.Shop.item.vo.ItemVO;

import java.util.List;

public interface ItemService {
    List<CategoryVO> selectCategory();

    List<ItemVO> selectItemList();

    ItemVO selectDetail(ItemVO itemVO);

}
