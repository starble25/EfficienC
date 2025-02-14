package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("layouts/layout");
        mv.addObject("contentPage", "/WEB-INF/views/user/main.jsp");
        return mv;
    }

    @GetMapping("/user/mypage")
    public ModelAndView myPage() {
        ModelAndView mv = new ModelAndView("layouts/layout");
        mv.addObject("contentPage", "/WEB-INF/views/user/mypage.jsp");
        return mv;
    }

    @GetMapping("/calendar/calendar")
    public ModelAndView calendar() {
        ModelAndView mv = new ModelAndView("layouts/layout");
        mv.addObject("contentPage", "/WEB-INF/views/calendar/calendar.jsp");
        return mv;
    }

    @GetMapping("/admin/usersList")
    public ModelAndView userList() {
        ModelAndView mv = new ModelAndView("layouts/layout");
        mv.addObject("contentPage", "/WEB-INF/views/admin/usersList.jsp");
        return mv;
    }
}
