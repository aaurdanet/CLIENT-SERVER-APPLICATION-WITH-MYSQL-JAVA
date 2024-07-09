# SQL commands to create and populate the operations log database for Project Two
# CNT 4714 - Summer 2024
#
# delete the database if it already exists
drop database if exists operationslog;

# create a new database named operationslog
create database operationslog;

# switch to the new database
use operationslog;

# create the schemas for the four relations in this database
create table operationscount (
    login_username varchar(25),
    num_queries integer,
	num_updates integer,
    primary key (login_username)
);

# uncomment the following line if you want to see the results of creating  database
#select * from operationscount;
