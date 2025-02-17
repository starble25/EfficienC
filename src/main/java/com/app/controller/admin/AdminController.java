package com.app.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.app.dto.user.User;
import com.app.dto.user.UserSearchCondition;
import com.app.service.user.UserService;

@Controller
@RequestMapping("/admin") // ✅ 공통 경로 추가
public class AdminController {

    @Autowired
    UserService userService;

    // 사용자 관리/등록
    @GetMapping("/users/add")
    public String addUser() {
        return "admin/addUser";
    }

    // 사용자 추가
    @PostMapping("/users/add")
    public String addUserAction(User user) {
        int result = userService.saveUser(user);
        System.out.println("사용자 추가 처리 결과 : " + result);

        if (result > 0) {
            return "redirect:/admin/users";
        } else {
            return "admin/addUser";
        }
    }

    // ✅ 사용자 목록 (URL 변경: /admin/usersList → /admin/users)
    @GetMapping("/users")
    public String users(Model model) {
        List<User> userList = userService.findUserList();
        model.addAttribute("userList", userList);
        return "admin/usersList";
    }

    // 사용자 상세페이지
    @GetMapping("/user/{email}")
    public String user(@PathVariable String email, Model model) {
        User user = userService.findUserByEmail(email);
        model.addAttribute("user", user);
        return "admin/user";
    }

    // 사용자정보 수정 페이지
    @GetMapping("/modifyUser/{email}")
    public String modifyUser(@PathVariable String email, Model model) {
        User user = userService.findUserByEmail(email);
        model.addAttribute("user", user);
        return "admin/modifyUser";
    }

    // 사용자 정보 수정 처리
    @PostMapping("/modifyUser")
    public String modifyUserAction(User user) {
        int result = userService.modifyUser(user);
        System.out.println(user.getEmail());
        if (result > 0) {
            return "redirect:/admin/user/" + user.getEmail();
        } else {
            return "redirect:/admin/modifyUser/" + user.getEmail();
        }
    }

    /** (추가) 사용자 삭제 기능 */
    @PostMapping("/user/delete/{email}")
    public String deleteUser(@PathVariable String email) {
        int result = userService.deleteUser(email);
        if (result > 0) {
            return "redirect:/admin/users";
        } else {
            return "redirect:/admin/user/" + email + "?error=deleteFailed";
        }
    }

    /** (추가) 사용자 검색 기능 */
    @GetMapping("/users/search")
    public String searchUsers(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        UserSearchCondition searchCondition = new UserSearchCondition();
        searchCondition.setKeyword(keyword);

        List<User> userList = userService.findUserListBySearchCondition(searchCondition);
        model.addAttribute("userList", userList);
        return "admin/usersList";
    }

    /** (추가) 사용자 역할 변경 기능 */
    @PostMapping("/user/changeRole/{email}")
    public String changeUserRole(@PathVariable String email, @RequestParam String newRole) {
        int result = userService.updateUserRole(email, newRole);
        if (result > 0) {
            return "redirect:/admin/user/" + email + "?success=roleChanged";
        } else {
            return "redirect:/admin/user/" + email + "?error=roleChangeFailed";
        }
    }
}
