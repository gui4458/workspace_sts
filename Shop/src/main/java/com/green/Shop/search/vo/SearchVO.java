package com.green.Shop.search.vo;

import com.green.Shop.buy.vo.BuyVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SearchVO extends BuyVO {
    private String searchOption;
    private String searchText;
    private String from;
    private String to;

}
