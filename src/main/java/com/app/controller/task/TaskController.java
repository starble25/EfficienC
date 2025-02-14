package com.app.controller.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dto.task.Task;
import com.app.service.task.TaskService;


//------DB 연동 전 코드----------
@Controller
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService = new TaskService();

    @GetMapping
    public String showBoard(Model model) {
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
