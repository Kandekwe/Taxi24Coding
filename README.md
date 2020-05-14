# Taxi24App API

Bank of Kigali Coding Exercise

This project was completed using Spring boot,Maven,Mysql 5.7 and postman to test endpoints

       SETTING UP WALK -THROUGH

* Start by cloning the project in your favorite directory

* Prepare your database and name it taxi24app, you can copy the scripts and create it manually or you can directly import it through this command "mysql -u root -p taxi24app < taxi24app.sql"

* Open \taxi24Coding\src\main\resources\application.yml again and change the port if your port is in use and change to a free one, assume that i am using 8080

* Update you working e-amil into TAXI_RIDER table e-mail field in order to receive invoice e-mails


        RUNNING THE PROJECT

* Enter your working directory and run  "mvn spring-boot:run" this will run the application

* After successfully running the application the following end points will be able to be tested, ensure to have a well working postman

 -http://localhost:8080/taxi24/api/taxidrivers/v1/fetchall (it will Get all drivers)
 -http://localhost:8080/taxi24/api/taxidrivers/v1/fetchallavailable(it will Get available Drivers)
 -http://localhost:8080/taxi24/api/taxidrivers/v1/fetchnearestbylocation(it will Get nearest driver within 3km)
 -http://localhost:8080/taxi24/api/taxidrivers/v1/fetchbyid(it will Get driver by ID )

 -http://localhost:8080/taxi24/api/riders/fetchall(It will Get all riders)
 -http://localhost:8080/taxi24/api/riders/fetchbyid(it will Get rider per id)
 -http://localhost:8080/taxi24/api/riders/fetchdriversbyrider(it will Get 3 closest drivers)

 -http://localhost:8080/taxi24/api/trips/v1/starttrip(it will Post starting of a trip)
 -http://localhost:8080/taxi24/api/trips/v1/createrequest (it Post a request to assign a driver to a rider)
 -http://localhost:8080/taxi24/api/trips/v1/completetrip(it will update a trip after completion)
 -http://localhost:8080/taxi24/api/trips/v1/fetchallactivetrips(it will Get all active trips)
 -http://localhost:8080/taxi24/api/trips/v1/fetchtripsbystatus(it will Get trips by status)


           TESTING APIs

 Testing will require postman and handle the above urls with respective parameters

 Here also you can test available APIs with swagger http://localhost:8080/taxi24/swagger-ui.html



