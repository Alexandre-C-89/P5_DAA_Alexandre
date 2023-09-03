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

    public LiveData<List<Project>> getProjects(long projectId) { return this.projectDao.getProjects(projectId); }

    public void createTask(Task task){ taskDao.createTask(task); }

    // --- DELETE ---

    public void deleteTask(long taskId){ taskDao.deleteTask(taskId); }

    /**
     * Méthode filter par jour
     */
    public void filterMeetingsByDate(LocalDate selectedDate) {
        List<Task> filteredMeetings = new ArrayList<>();
        for (Task task : tasks) {
            LocalDate taskDate = task.getDate();
            if (taskDate.isEqual(selectedDate)) {
                filteredMeetings.add(task);
            }
        }
        tasksLiveData.setValue(filteredMeetings);
    }

    /**
     * Méthode filter par nom
     */
    public void getFilteredMeetingsByRoom(final String newTaskName) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            String taskName = task.getName();
            if (taskName.contains(newTaskName)) {
                filteredTasks.add(task);
                // SearchView affiche dans ma toolbar un edit Text
            }
        }
        tasksLiveData.setValue(filteredTasks);
    }

    /**
     * Méthode filter par nom
     */
    public void resetFilter() {
        tasksLiveData.setValue(tasks);
    }

}
