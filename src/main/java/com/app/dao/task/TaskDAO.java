package com.app.dao.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dto.task.Task;

////-----DB 연동 전 코드 ----------
//public class TaskDAO {
//	private static List<Task> tasks = new ArrayList<>();
//    private static int idCounter = 1;
//
//    public List<Task> getAllTasks() {
//        return tasks;
//    }
//
//    public void addTask(String title) {
//        Task newTask = new Task(idCounter++, title, "", "TODO");
//        tasks.add(newTask);
//    }
//
//    public void updateTaskStatus(int id, String status) {
//        for (Task task : tasks) {
//            if (task.getId() == id) {
//                task.setStatus(status);
//                break;
//            }
//        }
//    }
//
//    public void deleteTask(int id) {
//        tasks.removeIf(task -> task.getId() == id);
//    }
//}


//@Repository
//public class TaskDAO {
//
//    @Autowired
//    private SqlSession sqlSession;
//
//    private static final String NAMESPACE = "com.app.dao.task.TaskDAO";
//
//    public List<Task> getAllTasks() {
//    	List<Task> taskList = sqlSession.selectList(NAMESPACE + ".getAllTasks");
//        return taskList;
//    }
//     
//    public int addTask(String title) { 
//    	int taskList = sqlSession.insert(NAMESPACE + ".addTask", title);
//		return taskList;
//    }
//
//    public int updateTaskStatus(int id, String status) {
//    	int taskList = sqlSession.update(NAMESPACE + ".updateTaskStatus", new Task());
//		return taskList;
//    }
//
//    public int deleteTask(int id) {
//    	int taskList = sqlSession.delete(NAMESPACE + ".deleteTask", id);
//		return taskList;
//    }
// 
//}
@Repository
public class TaskDAO {

    @Autowired
	private SqlSessionTemplate sqlSession;

//    @Autowired
//    public TaskDAO(SqlSessionTemplate sqlSession) {
//        this.sqlSession = sqlSession;
//        System.out.println("🚀 SqlSessionTemplate 주입됨: " + (sqlSession != null));
//    }

    private static final String NAMESPACE = "task_mapper";

    public List<Task> getAllTasks() {
        return sqlSession.selectList(NAMESPACE + ".getAllTasks");
    }
    
    public int addTask(String title) { 
        return sqlSession.insert(NAMESPACE + ".addTask", title);
    }

    public int updateTaskStatus(int id, String status) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("status", status);
        
        return sqlSession.update(NAMESPACE + ".updateTaskStatus", paramMap);
    }

    public int deleteTask(int id) {
        return sqlSession.delete(NAMESPACE + ".deleteTask", id);
    }
}
