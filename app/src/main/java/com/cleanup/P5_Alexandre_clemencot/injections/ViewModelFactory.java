package com.cleanup.P5_Alexandre_clemencot.injections;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.P5_Alexandre_clemencot.MainViewModel;
import com.cleanup.P5_Alexandre_clemencot.database.TodocDatabase;
import com.cleanup.P5_Alexandre_clemencot.home.HomeViewModel;
import com.cleanup.P5_Alexandre_clemencot.repository.DataRepository;
import com.cleanup.P5_Alexandre_clemencot.ui.TaskViewModel;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final DataRepository dataSource;

    private final Executor executor;

    private static ViewModelFactory factory;


    public static ViewModelFactory getInstance(Context context) {

        if (factory == null) {

            synchronized (ViewModelFactory.class) {

                if (factory == null) {

                    factory = new ViewModelFactory(context);

                }

            }

        }

        return factory;

    }

    public ViewModelFactory(Context context) {

        TodocDatabase database = TodocDatabase.getInstance(context);

        this.dataSource = new DataRepository(database.taskDao(), database.projectDao());

        this.executor = Executors.newSingleThreadExecutor();

    }


    @Override
    public <T extends ViewModel>  T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(dataSource, executor);
        }
        if (modelClass.isAssignableFrom(MainViewModel.class)) {

            return (T) new MainViewModel(dataSource, executor);
        }
        if (modelClass.isAssignableFrom(TaskViewModel.class)) {

            return (T) new TaskViewModel(dataSource, executor);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
