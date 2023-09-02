package com.cleanup.P5_Alexandre_clemencot.home;

import static java.sql.DriverManager.println;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cleanup.P5_Alexandre_clemencot.injections.ViewModelFactory;
import com.cleanup.P5_Alexandre_clemencot.model.Task;
import com.cleanup.todoc.databinding.FragmentHomeBinding;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private HomeViewModel homeViewModel;

    private HomeAdapter adapter;

    private List<Task> tasksList = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        // Initialize the ViewModel with the MeetingRepository
        homeViewModel = new ViewModelProvider(this, new ViewModelFactory(getContext())).get(HomeViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Observe la liste de meeting
        homeViewModel.getTasks().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                // Mettre à jour la liste de meetings
                updateList(tasks);
            }
        });
        initRecyclerViews();
        initAddButton();

        // Récupérer la nouvelle réunion passée en tant qu'argument
        Bundle args = getArguments();
        if (args != null && args.containsKey("newMeeting")) {
            Task newTask = args.getParcelable("newMeeting");
            // Ajouter la nouvelle tâches à la liste des tâches
            homeViewModel.addTask(newTask);
        }
    }

    private void initRecyclerViews() {
        adapter = new HomeAdapter(tasksList);
        adapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Ici, tu peux gérer les clics sur les éléments de la liste
                // Avant la suppression ou effectuer d'autres actions si nécessaire
                deleteMeeting(position);
            }
        });

        RecyclerView recyclerView = binding.listTasks;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void updateList(List<Task> listTasks){
        tasksList.clear();
        tasksList.addAll(listTasks);
        adapter.notifyDataSetChanged();
    }

    // AJouter au HomeFragment - ne pas oublier le XML
    private void initAddButton() {

    }

    private void deleteMeeting(int position) {
        // Supprimer la réunion du ViewModel
        homeViewModel.deleteMeeting(position);
    }

    // 2 - Configuring ViewModel

    private void configureViewModel() {

        this.homeViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance(getContext())).get(HomeViewModel.class);

        this.homeViewModel.init();

    }

}