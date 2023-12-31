package com.cleanup.P5_Alexandre_clemencot.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cleanup.P5_Alexandre_clemencot.database.DummyGenerator;
import com.cleanup.P5_Alexandre_clemencot.database.dao.ProjectDao;
import com.cleanup.P5_Alexandre_clemencot.database.dao.TaskDao;
import com.cleanup.P5_Alexandre_clemencot.model.Project;
import com.cleanup.P5_Alexandre_clemencot.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataRepository {
    private final TaskDao taskDao;

    private final ProjectDao projectDao;

    private final MutableLiveData<List<Task>> tasksLiveData = new MutableLiveData<>();

    private final List<Task> tasks = DummyGenerator.generateTask();


    public DataRepository(TaskDao taskDao, ProjectDao projectDao) {
        this.taskDao = taskDao;
        this.projectDao = projectDao;
    }

    public LiveData<List<Task>> getTasks() { return this.taskDao.getTasks(); }

    public LiveData<List<Project>> getProjects(long projectId) { return this.projectDao.getProjects(); }

    public void createTask(Task task){ taskDao.createTask(task); }

    // --- DELETE ---

    public void deleteTask(long taskId){ taskDao.deleteTask(taskId); }

    /**
     * Méthode trier par jour
     */

    /**
     * Méthode Trier par nom
     */


    /**
     * Méthode filter par nom
     */
    public void resetFilter() {
        tasksLiveData.setValue(tasks);
    }

}
