package com.cleanup.P5_Alexandre_clemencot;

import android.os.Bundle;
import android.view.Menu;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.cleanup.P5_Alexandre_clemencot.ui.home.HomeFragment;
import com.cleanup.P5_Alexandre_clemencot.injections.ViewModelFactory;
import com.cleanup.todoc.R;
import com.cleanup.todoc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_fragment, homeFragment)
                .addToBackStack(null)
                .commit();

        mainViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(MainViewModel.class);

        setContentView(binding.getRoot());
    }

}
