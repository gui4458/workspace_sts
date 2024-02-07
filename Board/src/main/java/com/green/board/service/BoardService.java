package com.green.board.service;

import com.green.board.vo.BoardVO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface BoardService {

    //글추가
    int insertBoard(BoardVO boardVO);

    List<BoardVO> selectBoardList();

    BoardVO selectBoard(int boardNum);

    int deleteBoard(int boardNum);

    void boardUpdate(BoardVO boardVO);

    void cntUpdate(int boardNum);


}
