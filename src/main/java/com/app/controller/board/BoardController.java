package com.app.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.dao.board.BoardDAO;
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



//@Controller
//@RequestMapping("/board") // 추가
//public class BoardController {
//    @Autowired
//    private BoardService boardService;
//
////    @RequestMapping("/form")  // URL을 변경
////    public ModelAndView showBoardForm() {
////        return new ModelAndView("boardForm");
////    }
//    
//    @GetMapping("/form")
//    public String boardForm() {
//        return "board/boardForm"; 
//    }
//
//    @PostMapping("/submit")  
//    public ModelAndView submitBoard(@ModelAttribute Board board) {
//        boardService.addBoard(board);
//        return new ModelAndView("redirect:/board/form");
//    }
//}


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
    
    
//    board detail servlet
    @WebServlet("/detail")
    public class BoardDetailServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
        
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                response.sendRedirect("boardList");
                return;
            }
            
            int id = Integer.parseInt(idParam);
            BoardDAO boardDAO = new BoardDAO();
            Board board = boardDAO.getBoardById(id);
            
            if (board == null) {
                response.sendRedirect("boardList");
                return;
            }
            System.out.println("detail");
            request.setAttribute("board", board);
            request.getRequestDispatcher("detail").forward(request, response);
        }
    }

}


