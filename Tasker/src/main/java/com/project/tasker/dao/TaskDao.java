package com.project.tasker.dao;

import com.project.tasker.core.Task;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Optional;

public class TaskDao extends AbstractDAO<Task> {
    Logger logger = LoggerFactory.getLogger(TaskDao.class);
    public TaskDao(SessionFactory factory) {
        super(factory);
    }

    public Task create(Task task) {
        return persist(task);
    }

    public List<Task> findAll() {
        return list(namedTypedQuery("com.project.tasker.core.Task.findAll"));
    }

    public Optional<Task> findById(long id) {
        return Optional.ofNullable(get(id));
    }

    public void delete(Task task) {
        try{
            deleteEntity(task);
            logger.info("Task is deleted");
        }catch (HibernateException hibernateException){
            logger.info("Task delete operation Failed");
        }
    }
}
