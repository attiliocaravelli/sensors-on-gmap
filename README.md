Problem
a) Sketch up a reasonably normalized entity relationship diagram of a database
   that would host data coming from temperature sensors spread around Germany, 
   with simple user management.
b) Generate the database and fill it with at least 2 users and 3 sensors 
   with dummy readings.
c) Create a simple GUI where the sensors are displayed on a map, 
   and their data plotted below the map if you click them on the map.

Input Data
Database
Output Data
Database
Data Format
JSON
Architecture
REST
Subtasks
1. Normalized entity relationship diagram of a database that would host data
   coming from temperature sensors spread around Germany, with simple
   user management.
2. Generate the database and fill it with at least 2 users and 3 sensors 
   with dummy readings.
3. Create a simple GUI where the sensors are displayed on a map, and their 
   data plotted below the map if you click them on the map.
Technologies
Java
Spring
Spring Boot
Spring Security
Hibernate
Thymeleaf HTML engine
AngularJS
Gmaps API
Maven

Assumptions
JDK 7, Junit 4
Deployment
I used Spring Boot and H2 Database in order to have a faster development.
    The table is automatically created with hibernate 
     specifications
The form is validated with annotation in the model class
     Launching the web application: 
			mvn clean install spring-boot:run
			http://localhost:8080/
			user: sa pass: sa 
                                db name: sensors

Subtask n. 2 
Implemented in the class DataGenerator
three users: 
userID = test0@gmail.com  → NO Active → NO possible the login
userID = test0@gmail.com  → ADMIN → It will be forbidden the view “Console”
userID = test0@gmail.com  → DBA → It will be granted all views
password = test

Subtask n. 3
I created a RESTful server in order to test the GUI created 
by AngularJS and Thymeleaf.

