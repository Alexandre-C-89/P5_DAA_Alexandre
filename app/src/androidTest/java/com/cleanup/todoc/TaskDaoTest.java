package com.cleanup.todoc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import com.cleanup.P5_Alexandre_clemencot.database.TodocDatabase;
import com.cleanup.P5_Alexandre_clemencot.model.Project;
import com.cleanup.P5_Alexandre_clemencot.model.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    // FOR DATA

    private TodocDatabase database;

    private static long TASK_ID = 2;

    private static Task TASK_DEMO = new Task(2, 1, "faire les courses", 12);
    private static Project PROJECT_DEMO = new Project(4, "Zero",0xFFA3CAD2);

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
    @Test

    public void insertAndGetTask() throws InterruptedException {

        // BEFORE : Adding a new user
        this.database.projectDao().insertProject(new Project(1L, "tartanpion", 0xFFEADAD1));
        this.database.taskDao().createTask(TASK_DEMO);

        // TEST

        List<Task> task = LiveDataTestUtil.getValue(this.database.taskDao().getTasks());
        assertFalse(task.isEmpty());
        assertTrue(task.get(0).getName().equals(TASK_DEMO.getName()));

    }

    /**
     * Delete test
     */
    @Test

    public void deleteTask() throws InterruptedException {

        // BEFORE : Adding a new user
        this.database.projectDao().insertProject(new Project(1L, "tartanpion", 0xFFEADAD1));
        this.database.taskDao().createTask(TASK_DEMO);

        // TEST

        this.database.taskDao().deleteTask(2L);
        List<Task> task = LiveDataTestUtil.getValue(this.database.taskDao().getTasks());
        assertTrue(task.isEmpty());

    }

    /**
     * Ecrire les tests pour les projets
     */

    @Test

    public void insertAndGetProjet() throws InterruptedException {

        // BEFORE : Adding a new user
        this.database.projectDao().insertProject(PROJECT_DEMO);

        // TEST

        List<Project> project = LiveDataTestUtil.getValue(this.database.projectDao().getProjects(4L));
        assertFalse(project.isEmpty());
        assertEquals(project.get(0).getName(), PROJECT_DEMO.getName());

    }

}
