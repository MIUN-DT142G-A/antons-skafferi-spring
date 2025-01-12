# Webserver for restaurant project Antons Skafferi
To run the webserver you will need the latest version of
- Docker
- Docker-compose
- Permissions to run docker without sudo/root/admin
Make sure they are accessable via $PATH. These should be automatically installed if you install docker desktop.

When cloning the project to IntelliJ you might need to create a run config. Go up to the right hand corner and select edit configurations ![image](https://github.com/user-attachments/assets/a00e10ad-73c0-4300-98a8-d5ce85987384)

Create a new config with the '+' button and select Spring Boot, give it a name and select java 17, select class "se.antons_skafferi.AntonsSkafferiSpringApplication" as main class then it should be up and running.

To initialize the database you will for now need to use these commands in the terminal, but a combined script will be made, make sure the docker container is up and running before running these, these should work on all operating systems 
```
docker exec -i antons-skafferi-spring-mysql-1 mysql -u myuser -psecret mydatabase < sql/01-drops.sql
docker exec -i antons-skafferi-spring-mysql-1 mysql -u myuser -psecret mydatabase < sql/02-tables.sql
docker exec -i antons-skafferi-spring-mysql-1 mysql -u myuser -psecret mydatabase < sql/03-inserts.sql
```
To run all in one script use this command in the terminal Mac/Linux
```
cat sql/01-drops.sql sql/02-tables.sql sql/03-inserts.sql | docker exec -i antons-skafferi-spring-mysql-1 mysql -u myuser -psecret mydatabase
```
Windows (untested might not work)
```
(Get-Content sql/01-drops.sql, sql/02-tables.sql, sql/03-inserts.sql -Raw) | docker exec -i antons-skafferi-spring-mysql-1 mysql -u myuser -psecret mydatabase
```
The server can then be found at localhost:8080
The menu can be accessed at localhost:8080/api/menu/all

### ÅÄÖ incorrecly displayed
In case the api endpoints are not showing ÅÄÖ correctly make sure to remove mysql_data before running the docker exec commands.
You might also need to run the following command to start from a clean slate.
```
docker system prune --volumes
```


## Building
To build the project to a docker image, first go to the maven tab in IntelliJ and select lifecycle and then package.
This should create a .jar file in the target folder.
Then run the following command in the terminal, you might need sudo/root/admin permissions.
```
docker build -t webbserver .
```
This will build the docker image with the .jar file in it.
To upload the image to dockerhub you will need to login to dockerhub in the terminal. 
Then run the following command, 
```
docker tag webbserver ndrs03/webbserver
docker push ndrs03/webbserver
```
This will upload the image to dockerhub. You will need collaborator permissions to the repository to push.

If any changes are made to the sql files or compose, update the files in GitHub.
