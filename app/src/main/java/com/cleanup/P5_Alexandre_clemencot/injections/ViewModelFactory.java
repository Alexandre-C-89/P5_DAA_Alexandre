package com.cleanup.P5_Alexandre_clemencot.injections;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.cleanup.P5_Alexandre_clemencot.database.TodocDatabase;
import com.cleanup.P5_Alexandre_clemencot.home.HomeViewModel;
import com.cleanup.P5_Alexandre_clemencot.repository.dataRepository;
import com.cleanup.P5_Alexandre_clemencot.ui.TaskViewModel;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final dataRepository dataSource;

    private final Executor executor;


    public ViewModelFactory(Context context) {

        TodocDatabase database = TodocDatabase.getInstance(context);

        this.dataSource = new dataRepository(database.taskDao(), database.projectDao());

        this.executor = Executors.newSingleThreadExecutor();

    }

    @Override
    public <T extends ViewModel>  T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(dataSource);
        }
        if (modelClass.isAssignableFrom(TaskViewModel.class)) {

            return (T) new TaskViewModel(dataSource, executor);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
