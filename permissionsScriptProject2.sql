# set permissions for the bikdeb database
grant select on bikedb.* to client1;
grant select, update on bikedb.* to client2;

# set permissions for the project2 database
grant select on project2.* to client1;
grant select, update on project2.* to client2;

# set permissions for the operationslog database
grant select, insert, update on operationslog.* to project2app;
grant select on operationslog.* to theaccountant;
