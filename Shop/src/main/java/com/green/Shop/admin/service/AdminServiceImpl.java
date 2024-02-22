package com.green.Shop.admin.service;

import com.green.Shop.buy.vo.BuyVO;
import com.green.Shop.item.vo.CategoryVO;
import com.green.Shop.item.vo.ItemVO;
import com.green.Shop.member.vo.MemberVO;
import com.green.Shop.search.vo.SearchVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
    @Autowired
    private SqlSessionTemplate sqlSession;
//  Transactional 어노테이션이 붙어있는 메소드 내부의 쿼리 실행은
//  모든 쿼리가 실행 성공 시 커밋
//  쿼리 중 하나라도 실패 시 롤백함
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertItem(ItemVO itemVO) {
        sqlSession.insert("adminMapper.insertItem",itemVO);
        sqlSession.insert("adminMapper.insertImgs", itemVO);
    }


    @Override
    public int selectNextItemCode() {
        return sqlSession.selectOne("adminMapper.selectNextItemCode");
    }

    @Override
    public List<BuyVO> selectBuyList(SearchVO searchVO) {
        return sqlSession.selectList("adminMapper.selectBuyList",searchVO);
    }

    @Override
    public BuyVO selectDetailBuyList(BuyVO buyVO) {
        return sqlSession.selectOne("adminMapper.selectDetailBuyList",buyVO);
    }

    @Override
    public List<ItemVO> selectUpdateInfo() {
        return sqlSession.selectList("adminMapper.selectUpdateInfo");
    }

    @Override
    public ItemVO clickItemInfo(ItemVO itemVO) {
        return sqlSession.selectOne("adminMapper.clickItemInfo",itemVO);
    }

    @Override
    public List<CategoryVO> cateName() {
        return sqlSession.selectList("adminMapper.cateName");
    }

    public void updateItem(ItemVO itemVO){
        sqlSession.update("adminMapper.updateItem",itemVO);
    }

    @Override
    public List<MemberVO> selectMemberList() {
        return sqlSession.selectList("memberMapper.selectMemberList");
    }
}
