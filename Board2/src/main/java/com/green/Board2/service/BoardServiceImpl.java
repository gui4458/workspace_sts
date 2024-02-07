package com.green.Board2.service;


import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.SearchVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
    @Autowired
    private SqlSessionTemplate sqlSession;
    @Override
    public void insertBoard(BoardVO boardVO) {
        sqlSession.insert("boardMapper.insertBoard",boardVO);
    }

    @Override
    public List<BoardVO> selectBoardList(SearchVO searchVO) {
        return sqlSession.selectList("boardMapper.selectBoardList", searchVO);
    }

    @Override
    public BoardVO selectBoardDetail(int boardNum) {
        return sqlSession.selectOne("boardMapper.selectBoardDetail",boardNum);
    }

    @Override
    public void deleteBoard(int boardNum) {
        sqlSession.delete("boardMapper.deleteBoard",boardNum);

    }

    @Override
    public void boardUpdate(BoardVO boardVO) {
        sqlSession.update("boardMapper.boardUpdate",boardVO);
    }

    @Override
    public void cntUp(int boardNum) {
        sqlSession.update("boardMapper.cntUp",boardNum);
    }

//    @Override
//    public List<BoardVO> Search(BoardVO boardVO) {
//        return sqlSession.selectList("boardMapper.Search",boardVO);
//
//    }

    @Override
    public int selectBoardCnt(SearchVO searchVO) {
        return sqlSession.selectOne("boardMapper.selectBoardCnt",searchVO);
    }

}
