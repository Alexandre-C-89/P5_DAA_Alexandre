package com.cleanup.P5_Alexandre_clemencot.model;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * Task correspond à Item dans le cours OC
 */
@Entity(tableName = "Tasks",
        foreignKeys = {@ForeignKey(entity = Project.class,
        parentColumns = "id",
        childColumns = "projectId",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)
})
public class Task {

    /**
     * The unique identifier of the task
     */
    @PrimaryKey(autoGenerate = true)
    private long id;

    /**
     * Cette id me permet de savoir à quel
     * projet cette tâche est affecter
     */
    private long projectId;

    /**
     * Nom de la tâche
     */
    @NonNull
    private String name;

    /**
     * date de la création de la tâche
     */
    private LocalDate date;

    /**
     * Instantiates a new Task.
     *
     * @param id                the unique identifier of the task to set
     * @param projectId         the unique identifier of the project associated to the task to set
     * @param name              the name of the task to set
     * @param localeDate the timestamp when the task has been created to set
     */
    public Task(long id, long projectId, @NonNull String name, LocalDate localeDate) {
        this.setId(id);
        this.setProjectId(projectId);
        this.setName(name);
        this.setDate(localeDate);
    }

    /**
     * Returns the unique identifier of the task.
     *
     * @return the unique identifier of the task
     *
     * GETTER et SETTER
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the task.
     *
     * @param id the unique idenifier of the task to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets the unique identifier of the project associated to the task.
     *
     * @param projectId the unique identifier of the project associated to the task to set
     */
    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    /**
     * Returns the project associated to the task.
     *
     * @return the project associated to the task
     */
    @Nullable
    public Project getProject() {
        return Project.getProjectById(projectId);
    }

    /**
     * Returns the name of the task.
     *
     * @return the name of the task
     */
    @NonNull
    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public long getProjectId() {
        return projectId;
    }

    /**
     * Sets the name of the task.
     *
     * @param name the name of the task to set
     */
    public void setName(@NonNull String name) {
        this.name = name;
    }

    /**
     * Sets the timestamp when the task has been created.
     *
     * @param date the timestamp when the task has been created to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

}
