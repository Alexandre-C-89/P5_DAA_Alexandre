package com.cleanup.todoc.ui;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.ProjectDataRepository;
import com.cleanup.todoc.repository.TaskDataRepository;
import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    // REPOSITORIES

    private final TaskDataRepository taskDataSource;

    private final ProjectDataRepository projectDataSource;

    private final Executor executor;

    // DATA

    @Nullable

    private LiveData<List<Project>> currentProject;

    public TaskViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {

        this.taskDataSource = taskDataSource;

        this.projectDataSource = projectDataSource;

        this.executor = executor;

    }

    public void init(long projectId) {

        if (this.currentProject != null) {

            return;

        }

        currentProject = projectDataSource.getProjects(projectId);

    }

    // -------------

    // FOR TASK

    // -------------

    public LiveData<Project> getProjects() { return this.getProjects();  }

    // -------------

    // FOR ITEM

    // -------------

    public LiveData<List<Task>> getTasks() {

        return taskDataSource.getTasks();

    }

    public void createTask(long id, long projectId, String taskName,long timestamp) {

        executor.execute(() -> {

            taskDataSource.createTask(new Task(id, projectId, taskName, timestamp));

        });

    }

    public void deleteItem(long taskId) {

        executor.execute(() -> taskDataSource.deleteTask(taskId));

    }

    public void updateTask(Task task) {

        executor.execute(() -> taskDataSource.updateTask(task));
    }

}
