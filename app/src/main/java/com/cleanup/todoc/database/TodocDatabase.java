package com.cleanup.todoc.database;

import static androidx.room.Room.databaseBuilder;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.concurrent.Executors;

@Database(entities = {Project.class, Task.class}, version = 1, exportSchema = false)

public abstract class TodocDatabase extends RoomDatabase {

    // --- SINGLETON ---

    private static volatile TodocDatabase INSTANCE;

    // --- DAO ---

    public abstract ProjectDao projectDao();

    public abstract TaskDao taskDao();

    // --- INSTANCE ---

    public static TodocDatabase getInstance(Context context) {

        if (INSTANCE == null) {

            synchronized (TodocDatabase.class) {

                if (INSTANCE == null) {

                    INSTANCE = databaseBuilder(context.getApplicationContext(),

                            TodocDatabase.class, "MyDatabase.db")

                            .addCallback(prepopulateDatabase())

                            .build();

                }

            }

        }

        return INSTANCE;

    }

    private static Callback prepopulateDatabase() {

        return new Callback() {

            @Override

            public void onCreate(@NonNull SupportSQLiteDatabase db) {

                super.onCreate(db);

                Executors.newSingleThreadExecutor().execute(() -> INSTANCE.taskDao().createTask(new Task(1, 1, "Nettoyage", )));

            }

        };

    }

}
