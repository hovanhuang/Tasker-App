FROM openjdk:17

RUN mkdir /tasker-app-backend

COPY . /tasker-app-backend

WORKDIR /tasker-app-backend
#Clean up H2 database before start
CMD java -jar target/Tasker-1.2-SNAPSHOT.jar db drop-all --confirm-delete-everything main.yml
#Apply all changes to the database
CMD java -jar target/Tasker-1.2-SNAPSHOT.jar db migrate main.yml

CMD java -jar target/Tasker-1.2-SNAPSHOT.jar server main.yml