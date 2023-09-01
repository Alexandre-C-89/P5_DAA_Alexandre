package com.cleanup.P5_Alexandre_clemencot.di;

import com.cleanup.P5_Alexandre_clemencot.database.dao.ProjectDao;
import com.cleanup.P5_Alexandre_clemencot.database.dao.TaskDao;
import com.cleanup.P5_Alexandre_clemencot.repository.dataRepository;

public class Di {

    private static TaskDao taskDao;
    private static ProjectDao projectDao;
    private static dataRepository dataRepository = new dataRepository(taskDao, projectDao);

    public static dataRepository getDataRepository() {
        return dataRepository;
    }

}