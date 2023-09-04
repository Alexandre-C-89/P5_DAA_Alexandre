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
    private long date;

    /**
     * Instantiates a new Task.
     *
     * @param id                the unique identifier of the task to set
     * @param projectId         the unique identifier of the project associated to the task to set
     * @param name              the name of the task to set
     * @param date the timestamp when the task has been created to set
     */
    public Task(long id, long projectId, @NonNull String name, long date) {
        this.setId(id);
        this.setProjectId(projectId);
        this.setName(name);
        this.setDate(date);
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

    public long getDate() {
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
    public void setDate(long date) {
        this.date = date;
    }

    /**
     * Comparator to sort task from A to Z
     */
    public static class TaskAZComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return left.name.compareTo(right.name);
        }
    }

    /**
     * Comparator to sort task from Z to A
     */
    public static class TaskZAComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return right.name.compareTo(left.name);
        }
    }

    /**
     * Comparator to sort task from last created to first created
     */
    public static class TaskRecentComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return (int) (right.date - left.date);
        }
    }

    /**
     * Comparator to sort task from first created to last created
     */
    public static class TaskOldComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return (int) (left.date - right.date);
        }
    }

}
