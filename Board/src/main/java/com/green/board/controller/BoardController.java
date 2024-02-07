package com.green.board.controller;

import com.green.board.service.BoardService;
import com.green.board.service.BoardServiceImpl;
import com.green.board.vo.BoardVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {
    @Resource(name = "boardService")
    private BoardServiceImpl boardService;

    //게시글 목록 페이지로 이동
    @GetMapping("/")
    public String boardList(Model model){
        List<BoardVO> list = boardService.selectBoardList();
        model.addAttribute("boardList", list);
    return "board_list";
    }
//글등록페이지
    @GetMapping("/regboard")
    public String regboard(){
        return "board_write_form";
    }


//글등록
    @PostMapping("/insertBoard")
    public String insertBoard(BoardVO boardVO){
        boardService.insertBoard(boardVO);

        return "redirect:/";
    }

//디테일
    @GetMapping("/board_detail")
    public String board_detail(BoardVO boardVO, Model model){
        BoardVO board = boardService.selectBoard(boardVO.getBoardNum());
        model.addAttribute("boardInfo",board);
        boardService.cntUpdate(boardVO.getBoardNum());



        return "board_detail";
    }
//삭제
    @GetMapping("/deleteBoard")
    public String deleteBoard(BoardVO boardVO){
        boardService.deleteBoard(boardVO.getBoardNum());
        return "redirect:/";
    }
//    수정페이지
    @GetMapping("/getUpdate")
    public String getUpdate(@RequestParam(name = "boardNum")int boardNum
                            , Model model){
        BoardVO boardVO = boardService.selectBoard(boardNum);
        model.addAttribute(boardVO);
        return "update_form";
    }
//    수정
    @PostMapping("/boardUpdate")
    public String boardUpdate(BoardVO boardVO) {
        boardService.boardUpdate(boardVO);
        return "redirect:/board_detail?boardNum=" + boardVO.getBoardNum();
    }
}
