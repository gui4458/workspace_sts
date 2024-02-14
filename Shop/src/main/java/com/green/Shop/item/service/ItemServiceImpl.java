package com.green.Shop.item.service;

import com.green.Shop.item.vo.CategoryVO;
import com.green.Shop.item.vo.ImgVO;
import com.green.Shop.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements ItemService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<CategoryVO> selectCategory() {
         return sqlSession.selectList("itemMapper.selectCategory");

    }

    @Override
    public List<ItemVO> selectItemList() {
        return sqlSession.selectList("itemMapper.selectItemList");
    }

    @Override
    public ItemVO selectDetail(ItemVO itemVO) {
        return sqlSession.selectOne("itemMapper.selectDetail",itemVO);
    }

}
