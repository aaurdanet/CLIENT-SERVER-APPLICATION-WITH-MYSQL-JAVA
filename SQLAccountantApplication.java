/*
Name: Antonio Urdaneta
Course: CNT 4714 Summer 2024
Assignment title: Project 2 – A Specialized Accountant Applicatio
Date: July 7, 2024
Class: CNT 4714
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.cj.jdbc.MysqlDataSource;


public class SQLAccountantApplication extends JPanel {

    //Defining buttons
    private JButton connect_button, disconnect_button, executeQuery_button, clear_sql_command_button, clear_result_window_button;


    //Defining labels
    private JLabel db_url_properties_label, db_user_properties_label, db_username_label, db_password_label, result_window_label, sql_command_label, connection_details_label, ulr_properties_FIXED, user_properties_FIXED;
    private static JLabel connection_status_label;



    //Defining text fields
    private JTextField username_input_text_field;
    private JPasswordField password_input_text_field;

    //Defining text area
    private static JTextArea query_text_area;

    // Connection object
    private Connection connection;
    private Statement statement;
    private ResultSet result_set;
    private int number_of_rows;
    private ResultSetMetaData metadata;
    private ResultSetTableModel tableModel;
    private TableModel Empty;
    private JTable result_table;
    private boolean connected_to_database = false;

    private void clearResultWindow(JTable result_table) {
        result_table.setModel(new DefaultTableModel());
        JOptionPane.showMessageDialog(null,
                "Result Table Cleared", "Info", JOptionPane.INFORMATION_MESSAGE);

    }

    private static void clearCommandWindow(JTextArea query_text_area) {
        SQLAccountantApplication.query_text_area.setText("");
    }

    //functions
    private static void connectedLabel(JLabel connectionStatusLabel, Properties urlProperties) {
        connectionStatusLabel.setText(" CONNECTED TO: " + urlProperties.getProperty("MYSQL_DB_URL"));
        connectionStatusLabel.setForeground(Color.GREEN);
    }

    private static void disconnectedLabel(JLabel connectionStatusLabel) {
        connectionStatusLabel.setText(" NO CONNECTION ESTABLISHED");
        connectionStatusLabel.setForeground(Color.RED);
    }

    public SQLAccountantApplication() {


        connection_details_label = new JLabel(" Connection Details");
        connection_details_label.setFont(new Font("Arial", Font.BOLD, 12));

        db_url_properties_label = new JLabel(" DB URL Properties");
        db_url_properties_label.setFont(new Font("Arial", Font.BOLD, 12));
        db_url_properties_label.setOpaque(true);
        db_url_properties_label.setBackground(Color.BLACK);
        db_url_properties_label.setForeground(Color.LIGHT_GRAY);

        db_user_properties_label = new JLabel(" DB User Properties");
        db_user_properties_label.setFont(new Font("Arial", Font.BOLD, 12));
        db_user_properties_label.setOpaque(true);
        db_user_properties_label.setBackground(Color.BLACK);
        db_user_properties_label.setForeground(Color.LIGHT_GRAY);

        db_username_label = new JLabel(" Username");
        db_username_label.setFont(new Font("Arial", Font.BOLD, 12));
        db_username_label.setOpaque(true);
        db_username_label.setBackground(Color.BLACK);
        db_username_label.setForeground(Color.LIGHT_GRAY);

        db_password_label = new JLabel(" Password");
        db_password_label.setFont(new Font("Arial", Font.BOLD, 12));
        db_password_label.setOpaque(true);
        db_password_label.setBackground(Color.BLACK);
        db_password_label.setForeground(Color.LIGHT_GRAY);

        ulr_properties_FIXED = new JLabel(" operationslog.properties");
        ulr_properties_FIXED.setFont(new Font("Arial", Font.BOLD, 12));
        ulr_properties_FIXED.setOpaque(true);
        ulr_properties_FIXED.setBackground(Color.WHITE);
        ulr_properties_FIXED.setForeground(Color.BLUE);

        user_properties_FIXED = new JLabel(" theaccountant.properties");
        user_properties_FIXED.setFont(new Font("Arial", Font.BOLD, 12));
        user_properties_FIXED.setOpaque(true);
        user_properties_FIXED.setBackground(Color.WHITE);
        user_properties_FIXED.setForeground(Color.BLUE);

        connection_status_label = new JLabel(" NO CONNECTION ESTABLISHED");
        connection_status_label.setFont(new Font("Arial", Font.BOLD, 16));
        connection_status_label.setOpaque(true);
        connection_status_label.setBackground(Color.BLACK);
        connection_status_label.setForeground(Color.RED);

        result_window_label = new JLabel(" SQL Execution Result Window");
        result_window_label.setFont(new Font("Arial", Font.BOLD, 12));


        sql_command_label = new JLabel(" Enter an SQL Command");
        sql_command_label.setFont(new Font("Arial", Font.BOLD, 12));


        username_input_text_field = new JTextField("", 15);
        username_input_text_field.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 12));

        password_input_text_field = new JPasswordField("", 10);
        password_input_text_field.setFont(new Font("Arial", Font.PLAIN, 15));

        query_text_area = new JTextArea(5, 5);
        query_text_area.setFont(new Font("Arial", Font.PLAIN, 12));
        query_text_area.setWrapStyleWord(true);
        query_text_area.setLineWrap(true);
        query_text_area.setOpaque(true);


        connect_button = new JButton("Connect to database");
        connect_button.setFont(new Font("Arial", Font.BOLD, 12));
        connect_button.setBackground(Color.GREEN);
        connect_button.setBorderPainted(false);
        connect_button.setOpaque(true);

        disconnect_button = new JButton("Disconnect from database");
        disconnect_button.setFont(new Font("Arial", Font.BOLD, 12));
        disconnect_button.setBackground(Color.RED);
        disconnect_button.setBorderPainted(false);
        disconnect_button.setOpaque(true);

        executeQuery_button = new JButton("Execute SQL Query");
        executeQuery_button.setFont(new Font("Arial", Font.BOLD, 12));
        executeQuery_button.setBackground(Color.GREEN);
        executeQuery_button.setBorderPainted(false);
        executeQuery_button.setOpaque(true);

        clear_sql_command_button = new JButton("Clear SQL Command");
        clear_sql_command_button.setFont(new Font("Arial", Font.BOLD, 12));
        clear_sql_command_button.setBackground(Color.YELLOW);
        clear_sql_command_button.setBorderPainted(false);
        clear_sql_command_button.setOpaque(true);

        clear_result_window_button = new JButton("Clear Result Window");
        clear_result_window_button.setFont(new Font("Arial", Font.BOLD, 12));
        clear_result_window_button.setBackground(Color.YELLOW);
        clear_result_window_button.setBorderPainted(false);
        clear_result_window_button.setOpaque(true);




        result_table = new JTable();
        Empty = new DefaultTableModel();

        setPreferredSize(new Dimension(905, 550));
        setLayout(null);
        final Box square = Box.createHorizontalBox();
        square.add(new JScrollPane(result_table));

        Box sqlsquare = Box.createHorizontalBox();
        sqlsquare.add(new JScrollPane(query_text_area));
        result_table.setEnabled(false);
        result_table.setGridColor(Color.BLACK);

        connect_button.setBounds(10, 180, 185, 25);

        disconnect_button.setBounds(242, 180, 185, 25);

        clear_sql_command_button.setBounds(450, 160, 185, 25);

        executeQuery_button.setBounds(705, 160, 185, 25);

        clear_result_window_button.setBounds(10, 500, 168, 25);


        connection_details_label.setBounds(10, 0, 300, 25);

        db_url_properties_label.setBounds(10, 30, 125, 25);

        db_user_properties_label.setBounds(10, 68, 125, 25);

        db_username_label.setBounds(10, 105, 125, 25);

        db_password_label.setBounds(10, 140, 125, 25);

        connection_status_label.setBounds(10, 220, 880, 25);

        result_window_label.setBounds(10, 250, 220, 25);

        square.setBounds(10, 275, 880, 220);

        sql_command_label.setBounds(450, 0, 215, 25);

        sqlsquare.setBounds(450, 30, 440, 120);

        ulr_properties_FIXED.setBounds(138, 30, 290, 25);

        user_properties_FIXED.setBounds(138, 68, 290, 25);


        username_input_text_field.setBounds(138, 104, 290, 30);

        password_input_text_field.setBounds(138, 138, 290, 30);

        add(connect_button);
        add(disconnect_button);
        add(clear_sql_command_button);
        add(executeQuery_button);
        add(clear_result_window_button);
        add(sql_command_label);
        add(sqlsquare);
        add(connection_details_label);
        add(db_url_properties_label);
        add(db_user_properties_label);
        add(db_username_label);
        add(db_password_label);
        add(ulr_properties_FIXED);
        add(user_properties_FIXED);
        add(username_input_text_field);
        add(password_input_text_field);
        add(connection_status_label);
        add(result_window_label);
        add(square);


        //connect button
        connect_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {

                    String input_url_properties = ulr_properties_FIXED.getText().strip();
                    String input_user_properties = user_properties_FIXED.getText().strip();
                    String username = username_input_text_field.getText();
                    String password = password_input_text_field.getText();

                    if (username.equals("") || password.equals("")) {
                        JOptionPane.showMessageDialog(null,
                                "Fill All Input Fields", "Input Error", JOptionPane.WARNING_MESSAGE);
                    } else {

                        Properties url_properties = new Properties();
                        Properties user_properties = new Properties();
                        FileInputStream user_properties_file = null;
                        FileInputStream ulr_properties_file = null;
                        MysqlDataSource dataSource = null;
                        try {

                            user_properties_file = new FileInputStream("C:\\Users\\anton\\IdeaProjects\\Database Interface dev\\src\\" + input_user_properties);
                            user_properties.load(user_properties_file);

                            ulr_properties_file = new FileInputStream("C:\\Users\\anton\\IdeaProjects\\Database Interface dev\\src\\" + input_url_properties);
                            url_properties.load(ulr_properties_file);

                            if (username.equals(user_properties.getProperty("MYSQL_DB_USERNAME")) && password.equals(user_properties.getProperty("MYSQL_DB_PASSWORD"))) {
                                dataSource = new MysqlDataSource();
                                dataSource.setURL(url_properties.getProperty("MYSQL_DB_URL"));
                                dataSource.setUser(user_properties.getProperty("MYSQL_DB_USERNAME"));
                                dataSource.setPassword(user_properties.getProperty("MYSQL_DB_PASSWORD"));


                                // establish connection to database
                                connection = dataSource.getConnection();

                                // update database connection status
                                connected_to_database = true;
                                connectedLabel(connection_status_label, url_properties);
                                password_input_text_field.setText("");
                                username_input_text_field.setText("");
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Check username and password", "Input Error", JOptionPane.ERROR_MESSAGE);
                            }

                            // set query and execute it
                            //     setQuery( query );

                            //set update and execute it
                            //setUpdate (query);
                        } //end try
                        catch (SQLException sqlException) {
                            sqlException.printStackTrace();
                            System.exit(1);
                        } // end catch
                        catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,
                            e.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Disconnect button
        disconnect_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    disconnectedLabel(connection_status_label);
                    clearCommandWindow(query_text_area);
                    clearResultWindow(result_table);
                    connection.close();
                    //delete jtable output window
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null,
                            "Error closing connection", "Connection Error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(e);
                } catch (NullPointerException e) {
                    System.out.println("Null Pointer Exception");
                }

            }
        });

        //Clear SQL command button
        clear_sql_command_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                clearCommandWindow(query_text_area);
            }
        });

        clear_result_window_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                clearResultWindow(result_table);
            }
        });

        executeQuery_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (!connected_to_database) {
                    JOptionPane.showMessageDialog(null,
                            "Connect to database before executing SQL query", "Connection Warning", JOptionPane.WARNING_MESSAGE);
                }
                String query = query_text_area.getText();
                int res = 0;
                String outputString = "";

                try {

                    result_table.setEnabled(true);
                    result_table.setAutoscrolls(true);

                    if (query.toUpperCase().contains("SELECT")) {
                        tableModel = new ResultSetTableModel(connection, query);
                        tableModel.setQuery(query);
                        result_table.setModel(tableModel);
                    } else {
                        tableModel = new ResultSetTableModel(connection, query);
                        res = tableModel.setUpdate(query);
                        if (res != 0) {
                            outputString = "Successfully updated SQL DB... " + res + " row(s) updated";
                            JOptionPane.showMessageDialog(null, outputString, "Update Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(e);

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(e);
                }

            }
        });


    }


    public static class Main {
        public static void main(String[] args) {
            JFrame frame = new JFrame("SQL Accountant Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(915, 570);

            SQLAccountantApplication app = new SQLAccountantApplication();
            frame.setContentPane(app);
            frame.setVisible(true);


        }
    }
}