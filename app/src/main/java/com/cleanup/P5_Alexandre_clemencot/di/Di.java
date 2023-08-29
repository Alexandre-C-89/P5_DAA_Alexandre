package com.cleanup.P5_Alexandre_clemencot.di;

import com.cleanup.P5_Alexandre_clemencot.database.dao.TaskDao;
import com.cleanup.P5_Alexandre_clemencot.repository.TaskDataRepository;

public class Di {

    private static TaskDao taskDao;
    private static TaskDataRepository taskDataRepository = new TaskDataRepository(taskDao);

    public static TaskDataRepository getTaskDataRepository() {
        return taskDataRepository;
    }

}