package com.cleanup.P5_Alexandre_clemencot.model;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
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
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long projectId;
    @NonNull
    private String taskDescription;
    private long date;

    @Ignore
    public Task(long projectId, @NonNull String taskDescription,long date) {
        this(0, projectId, taskDescription, date);
    }

    public Task(long id, long projectId, @NonNull String taskDescription, long date) {
        this.setId(id);
        this.setProjectId(projectId);
        this.setName(taskDescription);
        this.setDate(date);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    @Nullable
    public Project getProject() {
        return Project.getProjectById(projectId);
    }
    @NonNull
    public String getTaskDescription() {
        return taskDescription;
    }

    public long getDate() {
        return date;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setName(@NonNull String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public static class TaskAZComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return left.taskDescription.compareTo(right.taskDescription);
        }
    }

    public static class TaskZAComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return right.taskDescription.compareTo(left.taskDescription);
        }
    }

    public static class TaskRecentComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return (int) (right.date - left.date);
        }
    }

    public static class TaskOldComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            return (int) (left.date - right.date);
        }
    }

}
