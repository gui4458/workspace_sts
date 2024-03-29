package com.green.Shop.item.vo;

import com.green.Shop.buy.vo.BuyDetailVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ItemVO{
    private int itemCode;
    private String itemName;
    private int itemPrice;
    private int itemStock;
    private String itemIntro;
    private String regDate;
    private int cateCode;
    private CategoryVO categoryVO;
    private int itemStatus;
    private String strStatus;
    private List<ImgVO> imgList;


}
