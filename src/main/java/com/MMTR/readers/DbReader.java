package com.MMTR.readers;

import com.MMTR.CRUD_Interface;

import java.sql.*;
import java.util.List;

public class DbReader implements CRUD_Interface {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public DbReader(String user, String password){
        connect(user,password);
    }
    public DbReader(String plase){
        connect(plase);
    }

    public void connect(String plase){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+plase+"/");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void connect(String user, String password){
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/translator","postgres","1120697");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public boolean Add(String word, String translete) {
        return false;
    }

    @Override
    public List find(String word) {
        return null;
    }

    @Override
    public List selectAll() {
        try (ResultSet rs3 = statement.executeQuery("SELECT * FROM flywaydb.russian_words;")) {
            if (rs3.next()) {
                System.out.println(rs3.getString("russian_word"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delite(String word) {
        return false;
    }
}
