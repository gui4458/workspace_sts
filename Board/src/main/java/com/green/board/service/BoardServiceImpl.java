package com.green.board.service;

import com.green.board.vo.BoardVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public int insertBoard(BoardVO boardVO) {

        return sqlSession.insert("boardMapper.insertBoard",boardVO);
    }

    @Override
    public List<BoardVO> selectBoardList() {
        return sqlSession.selectList("boardMapper.selectBoardList");
    }

    @Override
    public BoardVO selectBoard(int boardNum) {
        return sqlSession.selectOne("boardMapper.selectBoard", boardNum);
    }

    @Override
    public int deleteBoard(int boardNum) {
        return sqlSession.delete("boardMapper.deleteBoard", boardNum);
    }

    @Override
    public void boardUpdate(BoardVO boardVO) {
        sqlSession.update("boardMapper.boardUpdate", boardVO);
    }

    @Override
    public void cntUpdate(int boardNum) {
        sqlSession.update("boardMapper.cntUpdate", boardNum);
    }


}
