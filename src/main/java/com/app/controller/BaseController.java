package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {

    // 홈 페이지
    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("layouts/layout");
        mv.addObject("contentPage", "/WEB-INF/views/user/main.jsp");
        return mv;
    }

    // 마이페이지
    @GetMapping("/user/mypage")
    public ModelAndView myPage() {
        ModelAndView mv = new ModelAndView("layouts/layout");
        mv.addObject("contentPage", "/WEB-INF/views/user/mypage.jsp");
        return mv;
    }

    // 사용자 목록 페이지 (관리자)
    @GetMapping("/admin/usersList")
    public ModelAndView userList() {
        ModelAndView mv = new ModelAndView("layouts/layout");
        mv.addObject("contentPage", "/WEB-INF/views/admin/usersList.jsp");
        return mv;
    }
}
