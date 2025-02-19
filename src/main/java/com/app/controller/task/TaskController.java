package com.app.controller.task;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dto.MenuItem;
import com.app.dto.task.Task;
import com.app.service.task.TaskService;


//------DB 연동 전 코드----------
@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping
    public String showBoard(Model model, HttpServletRequest request) {
        // 메뉴 리스트 생성 (예시)
        List<MenuItem> menuList = new ArrayList<>();
        menuList.add(new MenuItem("홈", "/main", false));
        menuList.add(new MenuItem("공지사항", "/notice", false));
        menuList.add(new MenuItem("사내게시판", "/board/list", false));
        menuList.add(new MenuItem("마이페이지", "/mypage", false));
        menuList.add(new MenuItem("캘린더", "/calendar", false));
        menuList.add(new MenuItem("ToDoList", "/tasks", false)); // 현재 활성화
        menuList.add(new MenuItem("주소록", "/address", false));
        menuList.add(new MenuItem("전자결제", "/payment", false));

        // request 스코프에 저장하여 JSP에서 사용 가능하도록 함
        request.setAttribute("menuList", menuList);
    	
    	
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "task/board";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam String title) {
        taskService.addTask(title);
        return "redirect:/tasks";
    }

    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam int id, @RequestParam String status) {
        taskService.updateTaskStatus(id, status);
        return "redirect:/tasks";
    }

    @PostMapping("/delete")
    public String deleteTask(@RequestParam int id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}

//11
//@Controller
//public class TaskController {
//
//    @Autowired
//    private TaskService taskService;
//
//    
//    @GetMapping("/tasks")
//    public String showBoard(Model model) {
//        List<TaskDTO> tasks = taskService.getAllTasks();
//        model.addAttribute("tasks", tasks);
//        return "task/board";
//    }
//
//    @PostMapping("/add")
//    public String addTask(@RequestParam String title) {
//        taskService.addTask(title);
//        return "redirect:/tasks";
//    }
//
//    @PostMapping("/updateStatus")
//    public String updateStatus(@RequestParam int id, @RequestParam String status) {
//        taskService.updateTaskStatus(id, status);
//        return "redirect:/tasks";
//    }
//
//    @PostMapping("/delete")
//    public String deleteTask(@RequestParam int id) {
//        taskService.deleteTask(id);
//        return "redirect:/tasks";
//    }
//}
