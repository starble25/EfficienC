package com.app.controller.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.dto.board.Board;
import com.app.service.board.BoardService;
//@Controller
//public class BoardController {
//	   @Autowired
//	    private BoardService boardService;
//
//	    @RequestMapping("/boardForm")
//	    public ModelAndView showBoardForm() {
//	        return new ModelAndView("boardForm");
//	    }
//
//	    @PostMapping("/submitBoard")
//	    public ModelAndView submitBoard(@ModelAttribute Board board) {
//	        boardService.addBoard(board);
//	        return new ModelAndView("redirect:/boardForm");
//	    }
//	}
@Controller
@RequestMapping("/board") // 추가
public class BoardController {
    @Autowired
    private BoardService boardService;

//    @RequestMapping("/form")  // URL을 변경
//    public ModelAndView showBoardForm() {
//        return new ModelAndView("boardForm");
//    }
    
    @GetMapping("/form")
    public String boardForm() {
        return "board/boardForm"; 
    }

    @PostMapping("/submit")  
    public ModelAndView submitBoard(@ModelAttribute Board board) {
        boardService.addBoard(board);
        return new ModelAndView("redirect:/board/form");
    }
}
