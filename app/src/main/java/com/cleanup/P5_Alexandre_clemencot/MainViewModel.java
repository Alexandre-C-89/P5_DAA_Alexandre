package com.cleanup.P5_Alexandre_clemencot;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.cleanup.P5_Alexandre_clemencot.di.Di;
import com.cleanup.P5_Alexandre_clemencot.model.Task;
import com.cleanup.P5_Alexandre_clemencot.repository.dataRepository;

import java.time.LocalDate;
import java.util.List;

public class MainViewModel extends ViewModel {

    private final dataRepository taskdataRepository;

    public MainViewModel() {
        taskdataRepository = Di.getDataRepository();
    }

    /**
     * Liste de meeting
     */
    public LiveData<List<Task>> getTasks() {
        return taskdataRepository.getTasks();
    }

    /**
     * Création de meeting
     */
    public void addTask(Task task) {

        taskdataRepository.createTask(task);
    }

    public void filterTasksByProjectName(String roomName) {
    }

    public void filterTasksByDate(LocalDate date) {
    }

    public void updateListBasedOnSearchText(String newRoomName) {
    }
}
