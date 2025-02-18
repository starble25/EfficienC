package com.app.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.dto.board.Board;
import com.app.service.board.BoardService;

@Controller
@RequestMapping("/board") // 공통 URL 프리픽스
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/form")
    public ModelAndView boardForm() {
        return new ModelAndView("board/boardForm");
    }

    @PostMapping("/submit")
    public ModelAndView submitBoard(@ModelAttribute Board board) {
        boardService.addBoard(board);
        return new ModelAndView("redirect:list"); // 수정: 목록 페이지로 이동
    }

    @GetMapping("/list") // 추가: 게시글 목록 조회 기능
    public ModelAndView showBoardList() {
        List<Board> boardList = boardService.getAllBoards();
        ModelAndView mav = new ModelAndView("board/boardList"); // 수정: board 폴더 안의 JSP 사용
        mav.addObject("boardList", boardList);
        return mav;
    }
    
    @PostMapping("/delete")
    public ModelAndView deleteBoard(@RequestParam("id") int id) {
        boardService.deleteBoard(id);
        return new ModelAndView("redirect:list"); // 삭제 후 목록으로 이동
    }
}


