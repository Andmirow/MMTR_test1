package com.MMTR.readers.db_reader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB implements DB_Interfase{

    private Connection connection;

    private void connect(String plase){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+plase+"/");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    private void connect(String user, String password){
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/translator",user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ConnectDB (String user, String password){
        connect(user, password);
    }

    public ConnectDB (String plase){
        connect(plase);
    }

    @Override
    public void disconnect() {

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }





}
