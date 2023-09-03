package com.cleanup.P5_Alexandre_clemencot.database;

import com.cleanup.P5_Alexandre_clemencot.model.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyGenerator {

        public static List<Task> DUMMY_TASK = Arrays.asList(
                new Task( 0, 0, "Supprimer l'erreur sur la searchView", 10L),
                new Task( 1, 1, "Supprimer l'erreur sur la searchView", 10L),
                new Task( 2, 2, "Supprimer l'erreur sur la searchView", 10L),
                new Task( 3, 3, "Supprimer l'erreur sur la searchView", 10L)
        );

        /**
         * Je créer des tasks par défault
         * qui seront présent au lancement de l'application
         */

        public static List<Task> generateTask() {
            return new ArrayList<>(DUMMY_TASK);
        }

}
