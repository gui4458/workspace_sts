package com.green.Board2.service;

import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.SearchVO;

import java.util.List;

public interface BoardService {
    void insertBoard(BoardVO boardVO);

    List<BoardVO> selectBoardList(SearchVO searchVO);

    BoardVO selectBoardDetail(int boardNum);

    void deleteBoard(int boardNum);

    void boardUpdate(BoardVO boardVO);

    void cntUp(int boardNum);

//    List<BoardVO> Search(BoardVO boardVO);
//    게시글 수 조회
    int selectBoardCnt(SearchVO searchVO);

}
