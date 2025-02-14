package com.app.controller.login;

import java.util.Random;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.app.dto.user.User;
import com.app.service.user.UserService;

@Controller
public class FindController {
    @Autowired
    JavaMailSenderImpl mailSender;
    
    @Autowired
    UserService userService;

    /** 📌 비밀번호 찾기 페이지 */
    @GetMapping("/findPassword")
    public String findPassword() {
        return "login/findPassword";
    }

    /** 📌 비밀번호 찾기 요청 (중복된 경로 제거) */
    @PostMapping("/findPasswordProcess") // 경로 변경!
    public String findPwdAction(@RequestParam String email, 
                                @RequestParam String name, 
                                @RequestParam String jumin,
                                HttpSession session, 
                                Model model) {

        User checkUser = userService.checkUserAuth(new User(email, name, jumin));

        if (checkUser != null) {
            Random random = new Random();
            int checkNum = random.nextInt(888888) + 111111;
            session.setAttribute("authCode", checkNum);
            session.setAttribute("email", email);

            String setFrom = "cording1kyu@gmail.com";
            String toMail = email;
            String title = "비밀번호 찾기 인증 이메일 입니다.";
            String content = "인증 코드는 " + checkNum + " 입니다.<br>해당 인증 코드를 인증 코드 확인란에 기입하여 주세요.";

            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
                helper.setFrom(setFrom);
                helper.setTo(toMail);
                helper.setSubject(title);
                helper.setText(content, true);
                mailSender.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "redirect:/authUser";
        } else {
            model.addAttribute("error", "입력한 정보가 일치하지 않습니다.");
            return "/login/findPassword";
        }
    }

    /** 📌 인증 코드 입력 페이지 */
    @GetMapping("/authUser")
    public String authUser(HttpSession session) {
        return "login/authUser";
    }

    /** 📌 인증 코드 확인 */
    @PostMapping("/authUser")
    public String authUserAction(HttpSession session, @RequestParam int inputCode) {
        int authCode = (int) session.getAttribute("authCode");
        if (authCode == inputCode) {
            return "redirect:/newPassword";
        } else {
            return "/login/fail";
        }
    }

    /** 📌 새 비밀번호 입력 페이지 */
    @GetMapping("/newPassword")
    public String newPassword(HttpSession session) {
        return "login/newPassword";
    }

    /** 📌 비밀번호 변경 */
    @PostMapping("/newPassword")
    public String newPasswordAction(HttpSession session, @RequestParam String pw) {
        String email = (String) session.getAttribute("email");
        User user = userService.findUserByEmail(email);
        if (user != null) {
            user.setPw(pw);
            int result = userService.changeUserPassword(user);
            if (result == 1) {
                return "redirect:/login";
            }
        }
        return "login/fail";
    }
}
