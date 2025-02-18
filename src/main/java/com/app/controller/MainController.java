package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.dto.MenuItem;

@Controller
public class MainController {

    @GetMapping("/main")
    public String main(HttpServletRequest request) {
        // 메뉴 리스트 생성 (예시)
        List<MenuItem> menuList = new ArrayList<>();
        menuList.add(new MenuItem("홈", "/main", false));
        menuList.add(new MenuItem("공지사항", "/notice", false));
        menuList.add(new MenuItem("사내게시판", "/board", false));
        menuList.add(new MenuItem("마이페이지", "/mypage", false));
        menuList.add(new MenuItem("캘린더", "/calendar", false));
        menuList.add(new MenuItem("ToDoList", "/task/list", true)); // 현재 활성화
        menuList.add(new MenuItem("주소록", "/address", false));
        menuList.add(new MenuItem("전자결제", "/payment", false));

        // request 스코프에 저장하여 JSP에서 사용 가능하도록 함
        request.setAttribute("menuList", menuList);

        // 기존 리턴값 그대로
        return "main";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/main";
    }
}
