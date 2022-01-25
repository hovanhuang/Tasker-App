
# Tasker Full Stack App

This Tasker full stack maven project uses the following technology:
1. React framework for frond-end design. (reference: [React Crash Course](https://www.youtube.com/watch?v=w7ejDZ8SWv8&t=3573s))
2. [Dropwizard Framework](https://www.dropwizard.io/)
3. Dropwizard Hibernate for ORM.
4. H2 in-memory database

## React FrontEnd
The frontend is designed with 3 main components:
1. Task.js - individual task that is created with title and date content shown. If reminder is checked, green ribbon will be shown. Click the delete icon will delete the task.
2. Tasks.js - a div container for task list.
3. AddTask.js - a form for adding new task and submission.

### To Start Frontend
```
npm start
```

## Dropwizard Backend

The backend is designed with usage of Dropwizard framework, which is a light weight dependency injection framework with handy plugins, such as JDBI, Hibernate, database migration. H2 in-memory database is chosen in this project for demonstration of data persistance. 

Three RESTful endpoints are exposed as follow:
1. http://localhost:8080/task/all : accepts GET request and return all task stored in database.
2. http://localhost:8080/task/{id}: accepts DELETE request and delete task entry with {id}.
3. http://localhost:8080/task : accepts POST request with Json content type in the body.

### Before starting the server
**Database preparation by [Dropwizard Migration](https://www.dropwizard.io/en/latest/manual/migrations.html)**\
Since we use H2-in-memory database, it is safe to clean up the database 

```
java -jar target/Tasker-1.2-SNAPSHOT.jar db drop-all --confirm-delete-everything main.yml
```

After the first build, to apply pending changesets to your database schema, run command,
```
java -jar target/Tasker-1.2-SNAPSHOT.jar db migrate main.yml
```


### Starting the server
```
java -jar target/Tasker-1.2-SNAPSHOT.jar server main.yml 
```

## Dockerized



### build the docker image file

```
docker build -t tasker-app-frontend .
```

### check docker images

```
docker images
```

### To run docker image file and specify port mapping (local machine port : docker container port)
```
docker run -p 3000:3000 tasker-app-frontend
```

# Oracle Cloud Container Service
## Auth Token
```
7Xq2QO!SUFFTTSkn_2Fk
```

