package com.app.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import com.app.dto.user.User;
import com.app.service.user.UserService;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String registerUser() {
        return "login/register";
    }

    @PostMapping("/register")
    public String registerUserAction(User user) {
        System.out.println(user.toString());

        int result = userService.saveUser(user);
        System.out.println(result);

        if (result > 0) {
            return "redirect:/login";
        } else {
            return "/register";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    /** 📌 로그인 처리 */
    @PostMapping("/login")
    public String loginAction(@RequestParam(required = false) String email, 
                              @RequestParam(required = false) String pw, 
                              HttpSession session, 
                              Model model) {
        System.out.println("로그인 시도 - email: " + email + ", pw: " + pw); // ✅ 디버깅 로그 추가

        if (email == null || pw == null) {
            model.addAttribute("error", "이메일과 비밀번호를 입력하세요.");
            return "login/login";
        }

        User user = userService.findByEmail(email);

        if (user != null && user.getPw().equals(pw)) {
            session.setAttribute("userEmail", user.getEmail());
            session.setAttribute("userName", user.getName());
            return "redirect:/calendar";
        } else {
            model.addAttribute("error", "이메일 또는 비밀번호가 잘못되었습니다.");
            return "login/login";
        }
    }

    /** 📌 로그아웃 기능 */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // ✅ 세션 초기화 (로그아웃)
        return "redirect:/login"; // 로그인 페이지로 이동
    }

    @GetMapping("/findPassword")
    public String findPw() {
        return "login/findPassword";
    }

    @PostMapping("/findPassword")
    public String findPw2() {
        return "login/findPassword2";
    }
}
