package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface ProjectDao {

    @Query("SELECT * FROM project WHERE id = :projectId")
    LiveData<List<Project>> getProject(long projectId);

    @Insert
    long insertProject(Project project);

    @Update
    int updateProject(Project project);

    @Query("DELETE FROM Project WHERE id = :projectId")
    int deleteProject(long projectId);

}
