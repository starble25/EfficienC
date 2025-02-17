package com.app.service.tasks;

import com.app.dao.tasks.TaskDAO;
import com.app.dto.tasks.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskDAO taskDAO;

    public List<TaskDTO> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    public void addTask(String title) {
        taskDAO.insertTask(title);
    }

    public void deleteTask(int id) {
        taskDAO.deleteTask(id);
    }
}
