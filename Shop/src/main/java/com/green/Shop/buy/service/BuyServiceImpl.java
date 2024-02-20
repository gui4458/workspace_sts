package com.green.Shop.buy.service;

import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.buy.vo.BuyVO;
import com.green.Shop.cart.vo.CartViewVO;
import com.green.Shop.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("buyService")
public class BuyServiceImpl implements BuyService {
    @Autowired
    private SqlSessionTemplate sqlSession;


    @Override
    public int selectNextBuyCode() {
        return sqlSession.selectOne("buyMapper.selectNextBuyCode");
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertBuys(BuyVO buyVO) {


        for(BuyDetailVO e : buyVO.getBuyDetailList()){
            int a = sqlSession.selectOne("buyMapper.CntCheck",e.getItemCode());
            if (a - e.getBuyCnt() < 0){

                return ;
            }
            sqlSession.update("buyMapper.minusCnt",e);
        }
        sqlSession.insert("buyMapper.insertBuy",buyVO);
        sqlSession.insert("buyMapper.insertBuyDetails",buyVO);
        sqlSession.delete("buyMapper.deleteCart",buyVO);
        sqlSession.update("buyMapper.statusChange");
//        장바구니 구매시 재고 마이너스(쿼리에서)
//        sqlSession.update("buyMapper.minusCnts",buyVO);


    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertBuy(BuyVO buyVO, BuyDetailVO buyDetailVO) {
        sqlSession.insert("buyMapper.insertBuy",buyVO);
        sqlSession.insert("buyMapper.insertBuyDetail",buyDetailVO);
        sqlSession.update("buyMapper.minusCnt",buyDetailVO);
        sqlSession.update("buyMapper.statusChange");

    }

    @Override
    public List<BuyVO> selectBuyList(String memberId) {
       return sqlSession.selectList("buyMapper.selectBuyList",memberId);
    }




}
