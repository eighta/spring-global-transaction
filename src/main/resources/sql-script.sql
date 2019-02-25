
>>> C:\Program Files\PostgreSQL\11\bin>

#CREATE DB
$createdb -Upostgres alpha

#CONNECT TO DB
$psql alpha postgres

CREATE TABLE CUSTOMER(ID SERIAL PRIMARY KEY NOT NULL, FIRSTNAME VARCHAR(50) NOT NULL, LASTNAME VARCHAR(50) NOT NULL, UNIQUE(FIRSTNAME));
INSERT INTO CUSTOMER (firstname,lastname)VALUES('MILTON','OCHOA');

>>> MySQL C:\Yesta\Dev\mysql-8.0.15-winx64\bin>

C:\Yesta\Dev\mysql-8.0.15-winx64\bin>mysqld --console --initialize --user=mysql
2019-02-24T01:01:34.559862Z 0 [System] [MY-013169] [Server] C:\Yesta\Dev\mysql-8.0.15-winx64\bin\mysqld.exe (mysqld 8.0.15) initializing of server in progress as process 14620
2019-02-24T01:01:38.501432Z 1 [ERROR] [MY-012611] [InnoDB] Operating system error number 32 in a file operation.
2019-02-24T01:01:38.501445Z 1 [ERROR] [MY-012615] [InnoDB] The error means that another program is using InnoDB's files. This might be a backup or antivirus software or another instance of MySQL. Please close it to get rid of this error.
2019-02-24T01:02:20.465204Z 5 [Note] [MY-010454] [Server] A temporary password is generated for root@localhost: K&j&oNNi!4D;
2019-02-24T01:02:44.354277Z 0 [System] [MY-013170] [Server] C:\Yesta\Dev\mysql-8.0.15-winx64\bin\mysqld.exe (mysqld 8.0.15) initializing of server has completed

C:\Yesta\Dev\mysql-8.0.15-winx64\bin>mysqld --console

#CONNECT TO SERVER
$mysql -u root -p
pass: K&j&oNNi!4D;

#CHANGE PASS
mysql> ALTER USER root@localhost IDENTIFIED BY 'sophie';

#CREATE DATABASE
mysql> CREATE DATABASE beta;

#CONECTARSE A LA DATABASE
mysql> use BETA;

CREATE TABLE DOGS(ID SERIAL PRIMARY KEY NOT NULL, NAME VARCHAR(50) NOT NULL, UNIQUE(NAME));
INSERT INTO DOGS (name)VALUES('FIFI');