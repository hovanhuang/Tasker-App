package com.project.tasker.core;

import javax.persistence.*;
@Entity
@Table(name = "task")
@NamedQueries(
        {
                @NamedQuery(
                        name = "com.project.tasker.core.Task.findAll",
                        query = "SELECT t FROM Task t"
                )
        })
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "reminder")
    private boolean reminder;

    public Task() {
    }

    public Task(String title, String date, boolean reminder) {
        this.title = title;
        this.date = date;
        this.reminder = reminder;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isReminder() {
        return reminder;
    }

    public void setReminder(boolean reminder) {
        this.reminder = reminder;
    }
}
