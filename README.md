# message-board-app

Short Description
-------------------------------
This is a simple message board application. See the "message-board-app-requirements.pdf" for full description of the user requirements.


Build & Deployment
------------------------
- Clone the repository / Download ZIP
- Change directory to your local working copy (e.g., C:\repo\message-board-app\)
- Run the following commands:
```
$ mvn clean package
$ java -jar backend\target\backend-0.0.1-SNAPSHOT.war
$ java -jar web\target\web-0.0.1-SNAPSHOT.war
```
Enter http://localhost:9090 (login as username: "guest" and password: "1234")

By default, the backend module will be deployed on 8080 and the web module on 9090. If you would like to change these configurations, use application.properties available in both web and backend modules (see below):

```
\backend\src\main\resources\application.properties
- server.port=8080

\web\src\main\resources\application.properties
- server.port=9090
- webservice.address=http://localhost:8080
```

