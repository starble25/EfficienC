package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // 기본 루트 URL -> /main으로 리다이렉트
    @GetMapping("/")
    public String root() {
        return "redirect:/main";
    }

    // 메인 페이지
    @GetMapping("/main")
    public String main() {
        return "layouts/layout"; // /WEB-INF/views/layouts/layout.jsp
    }
}
