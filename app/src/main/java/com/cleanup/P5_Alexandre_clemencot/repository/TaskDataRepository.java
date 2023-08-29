package com.cleanup.P5_Alexandre_clemencot.repository;

import androidx.lifecycle.LiveData;
import com.cleanup.P5_Alexandre_clemencot.database.dao.TaskDao;
import com.cleanup.P5_Alexandre_clemencot.model.Task;
import java.util.List;

public class TaskDataRepository {
    private final TaskDao taskDao;

    public TaskDataRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public LiveData<List<Task>> getTasks() { return this.taskDao.getTasks(); }

    public void createTask(Task task){ taskDao.createTask(task); }

    // --- DELETE ---

    public void deleteTask(long taskId){ taskDao.deleteTask(taskId); }

    // --- UPDATE ---

    public void updateTask(Task task){ taskDao.updateTask(task); }

}
