Please run the student app jar beforre running this application using below command:-
java -jar studentApp.jar --server.port=3030
and change the port of the application where it is 8080

To execute the Build provide the below Maven Goal:
clean verify serenity:aggregate

To execute Tagged tests, provide the goal as shown in the below format
clean verify -Dtags="studentfeature:NEGATIVE,studentfeature:SMOKE" serenity:aggregate
