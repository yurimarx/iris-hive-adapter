## iris-hive-adapter
This is an InterSystems IRIS Interoperability solution for SQL DML and DDL into Big Data repositories using IRIS Custom Adapter.

It contains a Big Data repository with Hadoop employee data, Apache Hive modules to allows create and edit Big Data Hadoop databases and tables, select, insert, update, delete and query data using SQL sentences inside Object Script.

With this adapter Object Script and Productions will be able to create and/or populate Hadoop Big Data repositories using SQL commands!


## Credits
1. Tutorial - Apache Hive on Docker (author: Hrishi Shirodkar) - https://hshirodkar.medium.com/apache-hive-on-docker-4d7280ac6f8e
2. IRIS Template for Interoperability (author: Evgeny Shvarov) - https://openexchange.intersystems.com/package/iris-interoperability-template



## Installation: Docker
Clone/git pull the repo into any local directory

```
$ git clone https://github.com/yurimarx/iris-hive-adapter.git
```

Open the terminal in this directory and run:

```
$ docker-compose build
```

3. Run the IRIS container with your project:

```
$ docker-compose up
```

4. Open the [HiveProduction](http://localhost:52773/csp/irisapp/EnsPortal.ProductionConfig.zen?PRODUCTION=dc.irishiveadapter.HiveProduction)

5. Click Start to run to production

6. Now we will test the App! Run your REST Client app (like Postman) the following URLs and command in the body (using POST verb):    
- 6.1 To create a new table in the Big Data:  POST http://localhost:9980/?Type=DDL        
In the BODY:`**CREATE TABLE helloworld (message String)**
- 6.2 To insert in the table: POST http://localhost:9980/?Type=DDL.    
In the BODY: **INSERT INTO helloworld VALUES ('hello')**
- 6.3 To get the result list from the table: POST http://localhost:9980/?Type=DML.     
In the BODY: **SELECT * FROM helloworld**   
(P.S.: Type is **DML** here)

7. Enjoy!!
