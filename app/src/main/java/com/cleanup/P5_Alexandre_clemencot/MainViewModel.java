package com.cleanup.P5_Alexandre_clemencot;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.cleanup.P5_Alexandre_clemencot.model.Task;
import com.cleanup.P5_Alexandre_clemencot.repository.DataRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executor;

public class MainViewModel extends ViewModel {

    private final DataRepository dataRepository;
    private final Executor executor;

    public MainViewModel(DataRepository dataSource, Executor executor) {
        this.dataRepository = dataSource;
        this.executor = executor;
    }

    public void filterTasksByProjectName(String roomName) {
        dataRepository.getFilteredMeetingsByRoom(roomName);
    }
    public void updateListBasedOnSearchText(String newRoomName) {
        dataRepository.getFilteredMeetingsByRoom(newRoomName);
    }

    public void resetFilter() {
        dataRepository.resetFilter();
    }
}
