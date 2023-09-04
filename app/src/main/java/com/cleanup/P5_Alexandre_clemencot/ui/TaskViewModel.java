package com.cleanup.P5_Alexandre_clemencot.ui;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.cleanup.P5_Alexandre_clemencot.model.Project;
import com.cleanup.P5_Alexandre_clemencot.model.Task;
import com.cleanup.P5_Alexandre_clemencot.repository.DataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    // REPOSITORIES
    private final DataRepository dataSource;

    private final Executor executor;

    // DATA

    @Nullable

    private LiveData<List<Project>> currentProject;

    public TaskViewModel(DataRepository dataSource, Executor executor) {

        this.dataSource = dataSource;

        this.executor = executor;

    }

    public void init(long projectId) {

        if (this.currentProject != null) {

            return;

        }

        currentProject = dataSource.getProjects(projectId);

    }

    // -------------

    // FOR TASK

    // -------------

    public LiveData<List<Task>> getTasks() {

        return dataSource.getTasks();

    }

    public void createTask(long id, long projectId, String taskName,long date) {

        executor.execute(() -> {

            dataSource.createTask(new Task(id, projectId, taskName, date));

        });

    }

    public void deleteItem(long taskId) {

        executor.execute(() -> dataSource.deleteTask(taskId));

    }

}
