package com.green.Shop.item.service;

import com.green.Shop.item.vo.ImgVO;
import com.green.Shop.item.vo.ItemVO;

import java.util.List;

public interface ItemService {
    List<ItemVO> selectCategory();

    List<ItemVO> selectItemList();

    ItemVO selectDetail(ItemVO itemVO);

}
