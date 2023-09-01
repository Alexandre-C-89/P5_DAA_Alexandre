package com.cleanup.P5_Alexandre_clemencot.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.cleanup.P5_Alexandre_clemencot.di.Di;
import com.cleanup.P5_Alexandre_clemencot.model.Task;
import com.cleanup.P5_Alexandre_clemencot.repository.dataRepository;
import java.util.List;

public class HomeViewModel extends ViewModel {


    private final dataRepository dataRepository;

    public HomeViewModel(dataRepository dataSource) {
        dataRepository = Di.getDataRepository();
    }

    /**
     * Liste de meeting
     */
    public LiveData<List<Task>> getTasks() {
        return dataRepository.getTasks();
    }

    public void addTask(Task newTask) {
        dataRepository.createTask(newTask);
    }

    public void deleteMeeting(int position) {
        dataRepository.deleteTask(position);
    }
}
