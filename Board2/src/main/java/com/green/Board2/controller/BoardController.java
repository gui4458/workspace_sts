package com.green.Board2.controller;

import com.green.Board2.service.BoardService;
import com.green.Board2.service.BoardServiceImpl;
import com.green.Board2.service.ReplyServiceImpl;
import com.green.Board2.vo.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Resource(name = "boardService")
    private BoardServiceImpl boardService;
    @Resource(name = "replyService")
    private ReplyServiceImpl replyService;

// 게시글 리스트
    @RequestMapping("/list")
    public String boardList(SearchVO searchVO , Model model){
        List<BoardVO> list = boardService.selectBoardList(searchVO);

//        전체 데이터 수
        int totalDataCnt = boardService.selectBoardCnt(searchVO);
        searchVO.setTotalDataCnt(totalDataCnt);
//        페이지 정보 세팅
        searchVO.setPageInfo();
        model.addAttribute("boardList",list);
        return "list";
    }
//게시글 작성 페이지
    @GetMapping("/boardForm")
    public String boardForm(){
        return "boardForm";
    }
//게시글 등록하기
    @PostMapping("/boardAdd")
    public String boardAdd(BoardVO boardVO){
        boardService.insertBoard(boardVO);
        return "redirect:/board/list";
    }

//    디테일
    @GetMapping("/boardDetail")
    public String boardDetail(BoardVO boardVO, Model model){
        //        디테일 페이지 이동시 조회수 증가
        boardService.cntUp(boardVO.getBoardNum());
        List<ReplyVO> reply = replyService.selectReply(boardVO.getBoardNum());
        boardService.selectBoardDetail(boardVO.getBoardNum());
        BoardVO board = boardService.selectBoardDetail(boardVO.getBoardNum());
        model.addAttribute("boardInfo", board);
        model.addAttribute("replyInfo", reply);

        return "boardDetail";
    }
    //삭제
    @GetMapping("/deleteBoard")
    public String deleteBoard(BoardVO boardVO){
        boardService.deleteBoard(boardVO.getBoardNum());
        return "redirect:/board/list";
    }
//  수정 페이지 가기
    @GetMapping("/updateForm")
    public String updateForm(BoardVO boardVO, Model model){
        BoardVO board = boardService.selectBoardDetail(boardVO.getBoardNum());
        model.addAttribute("board", board);
        return "updateForm";
    }

// 수정
    @PostMapping("/boardUpdate")
    public String boardUpdate(BoardVO boardVO){
        boardService.boardUpdate(boardVO);
        return "redirect:/board/boardDetail?boardNum=" + boardVO.getBoardNum();
    }

//    검색
//    @PostMapping("/titleSearch")
//    public String titleSearch(BoardVO boardVO, Model model){
////        , @RequestParam("name=searchOption")String searchOption
//
//        boardService.Search(boardVO);
//        List<BoardVO> boards = boardService.Search(boardVO);
//        model.addAttribute("list", boards);
////        model.addAttribute("searchOption", searchOption);
//        return "search";
//    }


}
