package com.cleanup.P5_Alexandre_clemencot.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.cleanup.P5_Alexandre_clemencot.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Tasks")
    LiveData<List<Task>> getTasks();

    @Insert
    void createTask(Task task);

    @Query("DELETE FROM Tasks WHERE id = :taskId")
    int deleteTask(long taskId);

}
