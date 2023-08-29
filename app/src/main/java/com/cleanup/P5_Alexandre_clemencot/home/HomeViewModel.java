package com.cleanup.P5_Alexandre_clemencot.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.cleanup.P5_Alexandre_clemencot.di.Di;
import com.cleanup.P5_Alexandre_clemencot.model.Task;
import com.cleanup.P5_Alexandre_clemencot.repository.TaskDataRepository;
import java.time.LocalDate;
import java.util.List;

public class HomeViewModel extends ViewModel {


    private final TaskDataRepository taskdataRepository;

    public HomeViewModel() {
        taskdataRepository = Di.getTaskDataRepository();
    }

    /**
     * Liste de meeting
     */
    public LiveData<List<Task>> getTasks() {
        return taskdataRepository.getTasks();
    }

    public void addTask(Task newTask) {
        taskdataRepository.createTask(newTask);
    }

    public void deleteMeeting(int position) {
        taskdataRepository.deleteTask(position);
    }
}
