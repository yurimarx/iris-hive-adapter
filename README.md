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
$ docker-compose up -d
```


