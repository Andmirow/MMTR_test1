package com.MMTR;

import com.MMTR.old.MyChekedException;
import com.MMTR.old.TransleterInterface;

import java.sql.*;

public abstract class Transleter implements TransleterInterface {

    private Connection connection;
    private String nameDataBase;
    private String nameTable;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private String nameFirstColumn;
    private String nameSecondColumn;
    private boolean isSetTransleter = false;
    private String rule;

    public Transleter(){}


    public void setTransleter(String plase,String nameDataBase )throws SQLException, MyChekedException {
        this.nameDataBase = nameDataBase;
        connect(plase);
        inicialization();
    }


    public Transleter(String plase,String nameDataBase) throws SQLException,MyChekedException {
        this.nameDataBase = nameDataBase;
        connect(plase);
        inicialization();
    }

    private void inicialization() throws SQLException,MyChekedException {
        try (ResultSet rst = statement.executeQuery("SELECT NAME FROM sqlite_master WHERE (type = 'table') AND (NAME NOT IN('sqlite_sequence'));")){
            if (rst.next()){
                nameTable = rst.getString("name");
                try (ResultSet rsc = statement.executeQuery("PRAGMA table_info ("+nameTable+");")){
                    while (rsc.next()) {
                        if (!rsc.getString("name").equals("id")){
                            if (nameFirstColumn == null){
                                nameFirstColumn = rsc.getString("name");
                                isSetTransleter = true;
                            }else{
                                nameSecondColumn = rsc.getString("name");
                            }
                        }
                    }
                }
            }else
                throw new MyChekedException("this DataBase have't table");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean Add (String firstLanguageWord, String secondLanguageWord) throws SQLException, MyChekedException{
        if (isSetTransleter){
            if (personalchekWord(firstLanguageWord)) {
                preparedStatement = connection.prepareStatement("INSERT INTO " + nameTable + " (" + nameFirstColumn + ", " + nameSecondColumn + ") VALUES (?, ?);");
                preparedStatement.setString(1, firstLanguageWord);
                preparedStatement.setString(2, secondLanguageWord);
                preparedStatement.executeUpdate();
                return true;
            }else
                throw new MyChekedException("wrong format exception, chek the word again");
        }else
            throw new MyChekedException("not set transleter");
    }

    public  boolean chekWord(String word){
        if (word != null){
            int index1 = word.indexOf(";");
            int index2 = word.indexOf("(");
            int index3 = word.indexOf(")");
            int index4 = word.indexOf("DROP TABLE");
            int index5 = word.indexOf("*");
            return (index1 == -1) && (index2 == -1) && (index3 == -1) && (index4 == -1) && (index5 == -1);
        }
        return false;
    }

    public  boolean personalchekWord(String word){
        return true;
    }

    public String find (String word) throws SQLException,MyChekedException {
        if (isSetTransleter){
            String res = null;
            if (chekWord(word)){
                if (personalchekWord(word)) {
                    try (ResultSet rs = statement.executeQuery("SELECT * FROM " + nameTable + " WHERE " + nameFirstColumn + " = '" + word + "' ;")) {
                        if (rs.next()) {
                            res = (rs.getString(nameFirstColumn) + " " + rs.getString(nameSecondColumn));
                        } else {
                            res = selectInSecondColumn(word);
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else{
                    res = selectInSecondColumn(word);
                }

            }else {
                throw new MyChekedException("wrong format exception, chek the word again");
            }
            return res;
        }else
            throw new MyChekedException("not set transleter");
    }

    public String selectInSecondColumn(String word) throws SQLException, MyChekedException{
        if (isSetTransleter){
            String res = null;
            if (chekWord(word)){
                try (ResultSet rs3 = statement.executeQuery("SELECT * FROM " + nameTable + " WHERE " + nameSecondColumn + " = '" + word + "' ;")) {
                    if (rs3.next()) {
                        res = (rs3.getString(nameFirstColumn) + " " + rs3.getString(nameSecondColumn));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else {
                throw new MyChekedException("wrong format exception, chek the word again");
            }
            return res;
        }else
            throw new MyChekedException("not set transleter");
    }

    public boolean deliteWordInSecondColumn(String word) throws SQLException, MyChekedException {
        if (isSetTransleter){
            if (chekWord(word)){
                try (ResultSet rs2 = statement.executeQuery("SELECT * FROM " + nameTable + " WHERE " + nameSecondColumn + " = '" + word + "' ;")) {
                    if (rs2.next()) {
                        statement.executeUpdate("DELETE FROM " + nameTable + " WHERE " + nameSecondColumn + " = '" + word + "' ;");
                        return true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }else
            throw new MyChekedException("not set transleter");
    }

    public boolean delite(String word) throws SQLException, MyChekedException{
        if (isSetTransleter){
            if (chekWord(word)){
                if (personalchekWord(word)) {
                    try (ResultSet rs = statement.executeQuery("SELECT * FROM " + nameTable + " WHERE " + nameFirstColumn + " = '" + word + "' ;")) {
                        if (rs.next()) {
                            statement.executeUpdate("DELETE FROM " + nameTable + " WHERE " + nameFirstColumn + " = '" + word + "' ;");
                            return true;
                        } else {
                            return deliteWordInSecondColumn(word);
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else{
                    return deliteWordInSecondColumn(word);
                }
            }else{
                throw new MyChekedException("wrong format exception, chek the word again");
            }
            return false;
        }else
            throw new MyChekedException("not set transleter");
    }

    public void selectAll() throws SQLException, MyChekedException {
        if (isSetTransleter){
            try (ResultSet rs = statement.executeQuery("SELECT * FROM "+nameTable+";")){
                while(rs.next()) {
                    System.out.println(rs.getInt(1) + " " + rs.getString(nameFirstColumn) + " - " + rs.getString(nameSecondColumn));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else
            throw new MyChekedException("not set transleter");

    }

    public void connect(String plase) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+plase+"/"+nameDataBase);
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException var1) {
            throw new SQLException("Unable to connect");
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

}
