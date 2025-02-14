package com.app.controller.login;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.app.dto.user.User;
import com.app.service.user.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /** 📌 회원가입 페이지 이동 */
    @GetMapping("/register")
    public String registerUser() {
        return "login/register";
    }

    /** 📌 회원가입 처리 */
    @PostMapping("/register")
    public String registerUserAction(User user) {
        // 단순 비밀번호 저장 (암호화 없이)
        int result = userService.saveUser(user);
        return (result > 0) ? "redirect:/login" : "login/register";
    }

    /** 📌 로그인 페이지 이동 */
    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    /** 📌 로그인 처리 */
    @PostMapping("/login")
    public String loginAction(@RequestParam String email, 
                              @RequestParam String pw, 
                              HttpSession session, 
                              Model model) {
        System.out.println("[로그인 시도] 이메일: " + email + ", 비밀번호(입력값): " + pw);

        User user = userService.findUserByEmail(email.toLowerCase());

        if (user == null) {
            model.addAttribute("error", "존재하지 않는 이메일입니다.");
            return "login/login";
        }

        System.out.println("[DB 조회] 비밀번호: " + user.getPw());

        // ✅ 비밀번호 검증 (암호화 없이 단순 비교)
        if (user.getPw().equals(pw)) {
            session.setAttribute("userEmail", user.getEmail());
            session.setAttribute("userName", user.getName());
            return "redirect:/calendar";
        } else {
            model.addAttribute("error", "이메일 또는 비밀번호가 잘못되었습니다.");
            return "login/login";
        }
    }

    /** 📌 로그아웃 처리 */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
