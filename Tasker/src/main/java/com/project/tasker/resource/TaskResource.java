package com.project.tasker.resource;

import com.project.tasker.core.Task;
import com.project.tasker.dao.TaskDao;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/task")
public class TaskResource {
    private final TaskDao taskDao;

    public TaskResource(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public List<Task> getTasks() {
        return taskDao.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Task createTask(@Valid Task task) {
        return taskDao.create(task);
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public void deleteTask(@PathParam("id") long id){
        if(taskDao.findById(id).isPresent()){
            taskDao.delete(taskDao.findById(id).get());
        }
    }
}
