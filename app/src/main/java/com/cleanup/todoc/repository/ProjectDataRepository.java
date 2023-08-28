package com.cleanup.todoc.repository;

import androidx.lifecycle.LiveData;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectDataRepository {

    private final ProjectDao projectDao;

    public ProjectDataRepository(ProjectDao projectDao) { this.projectDao = projectDao; }


    public LiveData<List<Project>> getProjects(long projectId) { return this.projectDao.getProjects(projectId); }

}
