package com.cleanup.P5_Alexandre_clemencot.repository;

import androidx.lifecycle.LiveData;

import com.cleanup.P5_Alexandre_clemencot.database.dao.ProjectDao;
import com.cleanup.P5_Alexandre_clemencot.model.Project;

import java.util.List;

public class ProjectDataRepository {

    private final ProjectDao projectDao;

    public ProjectDataRepository(ProjectDao projectDao) { this.projectDao = projectDao; }


    public LiveData<List<Project>> getProjects(long projectId) { return this.projectDao.getProjects(projectId); }

}
