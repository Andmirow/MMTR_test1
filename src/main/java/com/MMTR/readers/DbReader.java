package com.MMTR.readers;

import com.MMTR.servis.CRUD_Interface;
import com.MMTR.servis.TypeLanguage;

import java.sql.*;
import java.util.List;

public class DbReader implements CRUD_Interface {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private String nameTableFirst;
    private String nameTableSecond;
    private String nameTableThird;


    public DbReader(String user, String password,TypeLanguage typeLanguage){
        connect(user,password);
    }
    public DbReader(String plase){
        connect(plase);
    }

    private void inicialization(TypeLanguage typeLanguage){
        if (typeLanguage == TypeLanguage.english){
            nameTableFirst = "english_words";
            nameTableSecond = "russian_words";
            nameTableThird = "english_transtator";
        }else if (typeLanguage == TypeLanguage.numeric){
            nameTableFirst = "english_words";
            nameTableSecond = "numeric_words";
            nameTableThird = "numeric_transtator";
        }
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
        try (ResultSet rs3 = statement.executeQuery("SELECT russian_word, english_word\n" +
                "FROM flywaydb.english_transtator\n" +
                "NATURAL INNER JOIN flywaydb.russian_words\n" +
                "NATURAL INNER JOIN flywaydb.english_words;")) {
            while(rs3.next()) {
                System.out.println(rs3.getString("russian_word")+" - " +rs3.getString("english_word"));
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
