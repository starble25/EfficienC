package com.app.controller.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.dto.MenuItem;
import com.app.dto.board.Board;
import com.app.service.board.BoardService;

@Controller
@RequestMapping("/board") // 공통 URL 프리픽스
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/form")
    public ModelAndView boardForm(HttpServletRequest request) {
        // 메뉴 리스트 생성 (예시)
        List<MenuItem> menuList = new ArrayList<>();
        menuList.add(new MenuItem("홈", "/main", false));
        menuList.add(new MenuItem("공지사항", "/notice", false));
        menuList.add(new MenuItem("사내게시판", "/board/list", false));
        menuList.add(new MenuItem("캘린더", "/calendar", false));
        menuList.add(new MenuItem("ToDoList", "/tasks", false)); // 현재 활성화
        menuList.add(new MenuItem("주소록", "/address", false));
        // request 스코프에 저장하여 JSP에서 사용 가능하도록 함
        request.setAttribute("menuList", menuList);
    	
        return new ModelAndView("board/boardForm");
    }

    @PostMapping("/submit")
    public ModelAndView submitBoard(@ModelAttribute Board board) {
        boardService.addBoard(board);
        return new ModelAndView("redirect:list"); // 수정: 목록 페이지로 이동
    }

    @GetMapping("/list") // 추가: 게시글 목록 조회 기능
    public ModelAndView showBoardList(HttpServletRequest request) {
        // 메뉴 리스트 생성 (예시)
        List<MenuItem> menuList = new ArrayList<>();
        menuList.add(new MenuItem("홈", "/main", false));
        menuList.add(new MenuItem("공지사항", "/notice", false));
        menuList.add(new MenuItem("사내게시판", "/board/list", false));
        menuList.add(new MenuItem("캘린더", "/calendar", false));
        menuList.add(new MenuItem("ToDoList", "/tasks", false)); // 현재 활성화
        menuList.add(new MenuItem("주소록", "/address", false));

        // request 스코프에 저장하여 JSP에서 사용 가능하도록 함
        request.setAttribute("menuList", menuList);
    	
    	
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


