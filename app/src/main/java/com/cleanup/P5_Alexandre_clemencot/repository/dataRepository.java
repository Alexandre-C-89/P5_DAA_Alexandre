package com.cleanup.P5_Alexandre_clemencot.repository;

import androidx.lifecycle.LiveData;

import com.cleanup.P5_Alexandre_clemencot.database.dao.ProjectDao;
import com.cleanup.P5_Alexandre_clemencot.database.dao.TaskDao;
import com.cleanup.P5_Alexandre_clemencot.model.Project;
import com.cleanup.P5_Alexandre_clemencot.model.Task;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class dataRepository {
    private final TaskDao taskDao;

    private final ProjectDao projectDao;

    public dataRepository(TaskDao taskDao, ProjectDao projectDao) {
        this.taskDao = taskDao;
        this.projectDao = projectDao;
    }
    @NotNull
    public LiveData<List<Task>> getTasks() { return this.taskDao.getTasks(); }

    public LiveData<List<Project>> getProjects(long projectId) { return this.projectDao.getProjects(projectId); }

    public void createTask(Task task){ taskDao.createTask(task); }

    // --- DELETE ---

    public void deleteTask(long taskId){ taskDao.deleteTask(taskId); }

    // --- UPDATE ---

    public void updateTask(Task task){ taskDao.updateTask(task); }





}
