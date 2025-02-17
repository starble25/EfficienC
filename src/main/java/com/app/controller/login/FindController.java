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

	@GetMapping("/findPassword")
	public String findPassword() {

		return "login/findPassword";
	}

	@GetMapping("/authUser")
	public String authUser(HttpSession session) {

		return "login/authUser";
	}

	@GetMapping("/newPassword")
	public String newPassword(HttpSession session) {
		String email = (String) session.getAttribute("email");
		User user = userService.findUserByEmail(email);
		session.setAttribute("email", user.getEmail());
		return "login/newPassword";
	}

	@PostMapping("/findPassword")
	public String findPwdAction(@RequestParam String email, @RequestParam String name, @RequestParam String jumin,
			HttpSession session, User user) {

		User checkUser = userService.checkUserAuth(user); // DB에 저장된 데이터

		session.setAttribute("name", name);
		session.setAttribute("jumin", jumin); // 입력한 데이터 세션에 저장
		session.setAttribute("email", email);

		String inputName = (String) session.getAttribute("name");
		String inputJumin = (String) session.getAttribute("jumin"); // 세션에 저장된 데이터 형변환
		String inputEmail = (String) session.getAttribute("email");

		if (inputName.equals(checkUser.getName()) && inputJumin.equals(checkUser.getJumin())
				&& inputEmail.equals(checkUser.getEmail())) { // 두 데이터 비교후 같을 시
																// 이메일 발송 및 인증번호 입력 사이트 이동
			System.out.println("전달 받은 이메일 주소 : " + email);

			// 난수의 범위 111111 ~ 999999 (6자리 난수)
			Random random = new Random();
			int checkNum = random.nextInt(888888) + 111111;

			// 이메일 보낼 양식
			String setFrom = "cording1kyu@gmail.com"; // 2단계 인증 x, 메일 설정에서 POP/IMAP 사용 설정에서 POP/SMTP 사용함으로 설정o
			String toMail = email;
			String title = "비밀번호 찾기 인증 이메일 입니다.";
			String content = "인증 코드는 " + checkNum + " 입니다." + "<br>" + "해당 인증 코드를 인증 코드 확인란에 기입하여 주세요.";

			// 인증번호 발신
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
			System.out.println("랜덤숫자 : " + checkNum);

			session.setAttribute("authCode", checkNum);

			return "redirect:authUser";

		} else {

			return "/findPassword";
		}
	}

	@PostMapping("/authUser")
	public String authUserAction(HttpSession session, @RequestParam int inputCode) {

		int authCode = (int) session.getAttribute("authCode");

		if (authCode == inputCode) {
			return "redirect:newPassword";
		} else {
			return "/login/fail";
		}

	}

	@PostMapping("/newPassword")
	public String newPasswordAction(HttpSession session, @RequestParam String pw) {
		String email = (String) session.getAttribute("email");
		User user = userService.findUserByEmail(email);
		System.out.println(user);
		user.setPw(pw);
		int result = userService.changeUserPassword(user);
		if (result == 1) {
			System.out.println("비밀번호 변경완료");
			System.out.println(user);
			return "redirect:login";
		} else {
			return "login/fail";
		}
	}
	
}
