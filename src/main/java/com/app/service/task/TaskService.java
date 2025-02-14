package com.app.service.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.task.TaskDAO;
import com.app.dto.task.Task;

//------DB 연동 전 코드----------
public class TaskService {
	 private TaskDAO taskDAO = new TaskDAO();

	    public List<Task> getAllTasks() {
	        return taskDAO.getAllTasks();
	    }

	    public void addTask(String title) {
	        taskDAO.addTask(title);
	    }

	    public void updateTaskStatus(int id, String status) {
	        taskDAO.updateTaskStatus(id, status);
	    }

	    public void deleteTask(int id) {
	        taskDAO.deleteTask(id);
	    }
	}




//@Service
//public class TaskService {
//
//    @Autowired
//    private TaskDAO taskDAO;
//
//    public List<TaskDTO> getAllTasks() {
//        return taskDAO.getAllTasks();
//    }
//
//    public void addTask(String title) {
//        taskDAO.addTask(title);
//    }
//
//    public void updateTaskStatus(int id, String status) {
//        taskDAO.updateTaskStatus(id, status);
//    }
//
//    public void deleteTask(int id) {
//        taskDAO.deleteTask(id);
//    }
//}