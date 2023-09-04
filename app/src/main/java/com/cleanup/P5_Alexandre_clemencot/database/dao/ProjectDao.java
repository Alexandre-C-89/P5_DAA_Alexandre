package com.cleanup.P5_Alexandre_clemencot.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.cleanup.P5_Alexandre_clemencot.model.Project;
import java.util.List;

@Dao
public interface ProjectDao {

    @Query("SELECT * FROM Projects")
    LiveData<List<Project>> getProjects();

    @Insert
    long insertProject(Project project);

}
