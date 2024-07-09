# CLIENT-SERVER-APPLICATION-WITH-MYSQL-JAVA
An intuitive Java application for executing SQL commands on MySQL databases via JDBC. Designed for secure, role-based access and efficient query management

Project Integration Guide
Overview

This project consists of multiple Java and SQL files that work together to manage various applications and their configurations. The following guide explains how to integrate and use all the files in this project.
File List
Java Files

    ResultSetTableModel.java
    SQLAccountantApplication.java
    SQLClientApplication.java

SQL Files

    project2operationslog.sql
    permissionsScriptProject2.sql
    project2dbscript.sql
    client creation.sql
    bikedbscript.sql

Properties Files

    root.properties
    theaccountant.properties
    operationslog.properties
    project2.properties
    client1.properties
    client2.properties
    bikedb.properties

Setup Instructions
Step 1: Setting Up the Database

    Create the Database:
        Execute project2dbscript.sql to create the main database schema.
        Run project2operationslog.sql to create the operations log tables.
        Execute permissionsScriptProject2.sql to set up the necessary permissions.
        Run client creation.sql to create client-related tables.
        Execute bikedbscript.sql to create tables related to the bike database.

Step 2: Configuring the Properties Files

    root.properties:
        Contains the root configuration settings for the project.
    theaccountant.properties:
        Configuration settings for the accountant module.
    operationslog.properties:
        Configuration settings for logging operations.
    project2.properties:
        General configuration settings for the project.
    client1.properties and client2.properties:
        Configuration settings specific to client modules.
    bikedb.properties:
        Configuration settings for the bike database.

Step 3: Compiling the Java Files

    ResultSetTableModel.java:
        A utility class to handle result sets in table models.
        Compile this file using a Java compiler: javac ResultSetTableModel.java
    SQLAccountantApplication.java:
        Main application for managing accountant-related tasks.
        Compile this file: javac SQLAccountantApplication.java
    SQLClientApplication.java:
        Main application for managing client-related tasks.
        Compile this file: javac SQLClientApplication.java

Step 4: Running the Applications

    Starting the Applications:
        Ensure that all the necessary databases and tables are created.
        Make sure the properties files are correctly configured and located in the classpath.
        Run the applications using the Java command: java SQLAccountantApplication and java SQLClientApplication.

Step 5: Verifying the Integration

    Check Database Connections:
        Verify that the applications can connect to the databases specified in the properties files.
    Run Sample Queries:
        Execute sample queries to ensure that the databases and applications are interacting correctly.
    Review Logs:
        Check the logs generated in the operations log to ensure that operations are being recorded as expected.

Additional Notes

    Ensure that your Java environment is set up correctly and that the necessary libraries are included in the classpath.
    Modify the properties files as needed to match your specific environment settings.
    If any issues arise, consult the logs and configuration files for troubleshooting.
