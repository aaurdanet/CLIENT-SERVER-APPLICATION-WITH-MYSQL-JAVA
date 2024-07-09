/*
Name: Antonio Urdaneta
Two-tier Client-Server Application
Date: July 7, 2024
*/

import com.mysql.cj.jdbc.MysqlDataSource;
import javax.swing.table.AbstractTableModel;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class ResultSetTableModel extends AbstractTableModel {

    private Connection connection;
    private Statement statement;
    private ResultSet result_set;
    private ResultSetMetaData metadata;
    private int number_of_rows;
    boolean connected_to_Database = false;

    private String current_user_pass;
    private Connection proj2_connection;


    public ResultSetTableModel(Connection incomming_connection, String query) throws SQLException {
        this.connection = incomming_connection;
        this.statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        connected_to_Database = true;

        DatabaseMetaData info = connection.getMetaData();
        current_user_pass = info.getUserName();

//        setQuery(query);
    }

    public Class getColumnClass(int column) throws IllegalStateException {
        // ensure database connection is available
        if (!connected_to_Database)
            throw new IllegalStateException("Not Connected to Database");

        // determine Java class of column
        try {
            String className = metadata.getColumnClassName(column + 1);


            // return Class object that represents className
            return Class.forName(className);
        } // end try
        catch (Exception exception) {
            exception.printStackTrace();
        } // end catch

        return Object.class; // if problems occur above, assume type Object
    } // end method getColumnClass

    // get number of columns in ResultSet
    public int getColumnCount() throws IllegalStateException {
        // ensure database connection is available
        if (!connected_to_Database)
            throw new IllegalStateException("Not Connected to Database");

        // determine number of columns
        try {
            return metadata.getColumnCount();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch

        return 0; // if problems occur above, return 0 for number of columns
    } // end method getColumnCount

    // get name of a particular column in ResultSet
    public String getColumnName(int column) throws IllegalStateException {
        // ensure database connection is available
        if (!connected_to_Database)
            throw new IllegalStateException("Not Connected to Database");

        // determine column name
        try {
            return metadata.getColumnName(column + 1);
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch

        return ""; // if problems, return empty string for column name
    } // end method getColumnName

    // return number of rows in ResultSet
    public int getRowCount() throws IllegalStateException {
        // ensure database connection is available
        if (!connected_to_Database)
            throw new IllegalStateException("Not Connected to Database");

        return number_of_rows;
    } // end method getRowCount

    // obtain value in particular row and column
    public Object getValueAt(int row, int column)
            throws IllegalStateException {
        // ensure database connection is available
        if (!connected_to_Database)
            throw new IllegalStateException("Not Connected to Database");

        // obtain a value at specified ResultSet row and column
        try {
            result_set.next();  /* fixes a bug in MySQL/Java with date format */
            result_set.absolute(row + 1);
            return result_set.getObject(column + 1);
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch

        return ""; // if problems, return empty string object
    } // end method getValueAt

    // set new database query string
    public void setQuery(String query)
            throws SQLException, IllegalStateException {
        // ensure database connection is available
        if (!connected_to_Database)
            throw new IllegalStateException("Not Connected to Database");

        // specify query and execute it
        result_set = statement.executeQuery(query);

        // obtain meta data for ResultSet
        metadata = result_set.getMetaData();

        // determine number of rows in ResultSet
        result_set.last();                   // move to last row
        number_of_rows = result_set.getRow();  // get row number

        try {
            // project2db
            Properties operationslog_properties = new Properties();
            FileInputStream operationslog_db_file = null;
            MysqlDataSource dataSource = null;

            operationslog_db_file = new FileInputStream("C:\\Users\\anton\\IdeaProjects\\Database Interface dev\\src\\project2.properties");
            operationslog_properties.load(operationslog_db_file);

            System.out.println(operationslog_properties.getProperty("MYSQL_DB_USERNAME"));
            System.out.println(operationslog_properties.getProperty("MYSQL_DB_PASSWORD"));


            dataSource = new MysqlDataSource();
            dataSource.setURL(operationslog_properties.getProperty("MYSQL_DB_URL"));
            dataSource.setUser(operationslog_properties.getProperty("MYSQL_DB_USERNAME"));
            dataSource.setPassword(operationslog_properties.getProperty("MYSQL_DB_PASSWORD"));

            proj2_connection = dataSource.getConnection();
            System.out.println("Connected to Database: " + proj2_connection);
            try {
                String find_user_in_db = "SELECT * from operationscount where login_username = ?";
                PreparedStatement find_user_in_db_prepared = proj2_connection.prepareStatement(find_user_in_db);
                find_user_in_db_prepared.setString(1, current_user_pass);
                ResultSet find_user_in_db_result = find_user_in_db_prepared.executeQuery();

                if (find_user_in_db_result.next()) {
                    System.out.println(current_user_pass + " already exists");
                    int num_queries = find_user_in_db_result.getInt(2);
                    String update_num_query = "UPDATE operationscount SET num_queries = ? WHERE login_username = ?";
                    PreparedStatement update_num_query_prepared = proj2_connection.prepareStatement(update_num_query);
                    update_num_query_prepared.setInt(1, num_queries + 1);
                    update_num_query_prepared.setString(2, current_user_pass);
                    int rows_updated = update_num_query_prepared.executeUpdate();
                    if (rows_updated > 0) {
                        System.out.println("Number of rows updated for " + current_user_pass + " = " + rows_updated);
                    }
                    update_num_query_prepared.close();
                } else {
                    String insert_user_query = "INSERT INTO operationscount (login_username, num_queries, num_updates) VALUES (?, ?, ?)";
                    PreparedStatement insert_user_query_prepared = proj2_connection.prepareStatement(insert_user_query);
                    insert_user_query_prepared.setString(1, current_user_pass);
                    insert_user_query_prepared.setInt(2, 1);
                    insert_user_query_prepared.setInt(3, 0);
                    int rows_inserted = insert_user_query_prepared.executeUpdate();

                    if (rows_inserted > 0) {
                        System.out.println("Number of rows inserted for user " + current_user_pass + ": " + rows_inserted);
                    }
                    insert_user_query_prepared.close();
                }

                proj2_connection.close();

            } catch (SQLException e) {

                throw new RuntimeException(e);
            }


        } catch (IOException e) {
            System.out.println("Error reading project 2.properties");
        }

        // notify JTable that model has changed
        fireTableStructureChanged();
    }   // end method setQuery


    // set new database update-query string
    public int setUpdate(String query)
            throws SQLException, IllegalStateException, IOException {
        int res = 0;
        // ensure database connection is available
        if (!connected_to_Database)
            throw new IllegalStateException("Not Connected to Database");

        //specify query and execute it
        res = statement.executeUpdate(query);

        Properties operationslog_properties = new Properties();
        FileInputStream operationslog_db_file = null;
        MysqlDataSource dataSource = null;

        operationslog_db_file = new FileInputStream("C:\\Users\\anton\\IdeaProjects\\Database Interface dev\\src\\project2.properties");
        operationslog_properties.load(operationslog_db_file);

        System.out.println(operationslog_properties.getProperty("MYSQL_DB_USERNAME"));
        System.out.println(operationslog_properties.getProperty("MYSQL_DB_PASSWORD"));


        dataSource = new MysqlDataSource();
        dataSource.setURL(operationslog_properties.getProperty("MYSQL_DB_URL"));
        dataSource.setUser(operationslog_properties.getProperty("MYSQL_DB_USERNAME"));
        dataSource.setPassword(operationslog_properties.getProperty("MYSQL_DB_PASSWORD"));

        proj2_connection = dataSource.getConnection();
        System.out.println("Connected to Database: " + proj2_connection);


        try {
            String find_user_in_db = "SELECT * from operationscount where login_username = ?";
            PreparedStatement find_user_in_db_prepared = proj2_connection.prepareStatement(find_user_in_db);
            find_user_in_db_prepared.setString(1, current_user_pass);
            ResultSet find_user_in_db_result = find_user_in_db_prepared.executeQuery();

            //Update already existing user
            if (find_user_in_db_result.next()) {
                System.out.println("User already exists: " + current_user_pass);
                int num_updates = find_user_in_db_result.getInt(3);
                String update_num_updates = "UPDATE operationscount SET num_updates = ? WHERE login_username = ?";
                PreparedStatement update_num_updates_prepared = proj2_connection.prepareStatement(update_num_updates);

                update_num_updates_prepared.setInt(1, num_updates + 1);
                update_num_updates_prepared.setString(2, current_user_pass);
                res = update_num_updates_prepared.executeUpdate();
                return res;


            } else {
                String insert_user_query = "INSERT INTO operationscount (login_username, num_queries, num_updates) VALUES (?, ?, ?)";
                PreparedStatement insert_user_query_prepared = proj2_connection.prepareStatement(insert_user_query);
                insert_user_query_prepared.setString(1, current_user_pass);
                insert_user_query_prepared.setInt(2, 0);
                insert_user_query_prepared.setInt(3, 1);
                res = insert_user_query_prepared.executeUpdate();
                return res;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        fireTableStructureChanged();
        return res;
    } // end method setUpdate

    // close Statement and Connection
    public void disconnectFromDatabase() {
        if (!connected_to_Database)
            return;
            // close Statement and Connection
        else try {
            statement.close();
            connection.close();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
        finally  // update database connection status
        {
            connected_to_Database = false;
        } // end finally
    } // end method disconnectFromDatabase
}  // end class ResultSetTableModel
