package com.app.dao.tasks;

import com.app.dto.tasks.TaskDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TaskDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "tasksMapper.";

    public List<TaskDTO> getAllTasks() {
        return sqlSession.selectList(NAMESPACE + "getAllTasks");
    }

    public void insertTask(String title) {
        sqlSession.insert(NAMESPACE + "insertTask", title);
    }

    public void deleteTask(int id) {
        sqlSession.delete(NAMESPACE + "deleteTask", id);
    }
}
