package com.cleanup.P5_Alexandre_clemencot.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.cleanup.P5_Alexandre_clemencot.model.Task;
import com.cleanup.P5_Alexandre_clemencot.repository.DataRepository;
import java.util.List;
import java.util.concurrent.Executor;

public class HomeViewModel extends ViewModel {


    private final DataRepository dataRepository;
    private final Executor executor;

    public HomeViewModel(DataRepository dataSource, Executor executor) {
        this.dataRepository = dataSource;
        this.executor = executor;
    }

    /**
     * Liste de meeting
     */
    public LiveData<List<Task>> getTasks() {
        return dataRepository.getTasks();
    }

    public void addTask(Task newTask) {
        executor.execute(() -> dataRepository.createTask(newTask));
    }

    public void deleteMeeting(long position) {
        executor.execute(() -> dataRepository.deleteTask(position));
    }
}
