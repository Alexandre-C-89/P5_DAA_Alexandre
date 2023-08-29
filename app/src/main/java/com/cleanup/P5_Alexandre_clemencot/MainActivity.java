package com.cleanup.P5_Alexandre_clemencot;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import com.cleanup.P5_Alexandre_clemencot.home.HomeFragment;
import com.cleanup.todoc.R;
import com.cleanup.todoc.databinding.ActivityMainBinding;
import java.time.LocalDate;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private MainViewModel mainViewModel;

    /**
     * The sort method to be used to display tasks
     */
    @NonNull
    private SortMethod sortMethod = SortMethod.NONE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        /**
         * Toolbar
         */
        setSupportActionBar(binding.toolbar);
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_fragment, homeFragment)
                .addToBackStack(null)
                .commit();

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setContentView(binding.getRoot());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);

        MenuItem menuItem = menu.findItem(R.id.action_filter);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String taskName) {
                // Action lorsque l'utilisateur soumet la recherche
                mainViewModel.filterTasksByProjectName(taskName);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newRoomName) {
                // Action lorsque le texte de recherche change
                // Par exemple, vous pouvez mettre à jour la liste en fonction du texte de recherche en temps réel
                mainViewModel.updateListBasedOnSearchText(newRoomName);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_alphabetical:
                sortMethod = SortMethod.ALPHABETICAL;
                return true;
            case R.id.filter_alphabetical_inverted:
                sortMethod = SortMethod.ALPHABETICAL_INVERTED;
                return true;
            case R.id.filter_oldest_first:
                sortMethod = SortMethod.OLD_FIRST;
                return true;
            case R.id.filter_recent_first:
                sortMethod = SortMethod.RECENT_FIRST;
                return true;
                // Ajouter deux autres options
        }

        //updateTasks();
        return super.onOptionsItemSelected(item);
    }

    /**
     * List of all possible sort methods for task
     */
    private enum SortMethod {
        /**
         * Sort alphabetical by name
         */
        ALPHABETICAL,
        /**
         * Inverted sort alphabetical by name
         */
        ALPHABETICAL_INVERTED,
        /**
         * Lastly created first
         */
        RECENT_FIRST,
        /**
         * First created first
         */
        OLD_FIRST,
        /**
         * No sort
         */
        NONE
    }

    private void handleFilterToday() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // La date a été sélectionnée, vous pouvez effectuer les actions nécessaires ici
                // Par exemple, vous pouvez mettre à jour votre liste de réunions avec la date sélectionnée
                LocalDate selectedDate = LocalDate.of(year, monthOfYear + 1, dayOfMonth);
                filterMeetingsByDate(selectedDate);
            }
        };

        // Récupérez la date actuelle pour initialiser le DatePickerDialog
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Créez et affichez le DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
        datePickerDialog.show();
    }

    private void filterMeetingsByDate(LocalDate selectedDate) {
        mainViewModel.filterTasksByDate(selectedDate);
    }

}
