# message-board-app

Short description
-------------------------------
This is a simple message board application.


Build & Deployment
------------------------
- Clone the repository
- Change directory to your local working copy (e.g., C:\repo\message-board-app\)
- Run the following commands:
```
$ mvn clean package
$ java -jar backend\target\backend-0.0.1-SNAPSHOT.war
$ java -jar web\target\web-0.0.1-SNAPSHOT.war
```

By default, the backend module will be deployed on 8080 and the web module on 9090. If you would like to change these configurations, please change the port in application.properties (available in both web and backend module).

```
\backend\src\main\resources\application.properties
- server.port=8080

\web\src\main\resources\application.properties
- server.port=9090
- webservice.address=http://localhost:8080
```

Enter: http://localhost:9090

There is a single defined user using Spring InMemoryUserDetailsManager. You can login with username: "guest" and password: "1234"