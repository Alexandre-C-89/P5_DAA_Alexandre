package com.cleanup.todoc;

import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    // FOR DATA

    private TodocDatabase database;

    private static long TASK_ID = 2;

    private static Task TASK_DEMO = new Task(2, 1, "faire les courses", 12);

    @Test

    public void insertAndGetTask() throws InterruptedException {

        // BEFORE : Adding a new user

        this.database.taskDao().createTask(TASK_DEMO);

        // TEST

        //Task task = LiveDataTestUtil.getValue(this.database.taskDao().getTask(TASK_ID));

        //assertTrue(task.getName().equals(TASK_DEMO.getName()) && task.getId() == TASK_ID);

    }
    @Rule

    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before

    public void initDb() throws Exception {

        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),

                        TodocDatabase.class)

                .allowMainThreadQueries()

                .build();

    }

    @After

    public void closeDb() throws Exception {

        database.close();

    }

}
