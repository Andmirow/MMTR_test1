package com.MMTR.readers.db_reader;

import com.MMTR.servis.CRUD_Interface;
import com.MMTR.servis.TypeLanguage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbReader implements CRUD_Interface {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    SettingDb settingDb;
    ConnectDB connectDB;

    public DbReader(SettingDb settingDb, ConnectDB connectDB){
        this.settingDb = settingDb;
        this.connectDB = connectDB;
        connect();
        try {
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void connect(){
        this.connection = connectDB.getConnection();
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
        try {
            Boolean isFirst = foundWord(word, true);
            if (!isFirst){
                AddSimpleTable(word,settingDb.getNameTableFirst(),settingDb.getNameColumnFirstTable());
            }

            if (!foundWord(translete, false)){
                AddSimpleTable(translete,settingDb.getNameTableSecond(),settingDb.getNameColumnSecondTable());
            }

            preparedStatement = connection.prepareStatement("INSERT INTO "+settingDb.getNameTableThird()+" ("+settingDb.getNameFirstColumnThirdTable()+", "+settingDb.getNameSecondColumnThirdTable()+")\n" +
                "VALUES  (\n" +
                "\t(SELECT "+settingDb.getNameFirstColumnThirdTable()+" FROM "+settingDb.getNameTableFirst()+"\n" +
                "\t\tWHERE "+settingDb.getNameColumnFirstTable()+" = ?),\n" +
                "\t(SELECT "+settingDb.getNameSecondColumnThirdTable()+" FROM "+settingDb.getNameTableSecond()+"\n" +
                "\tWHERE "+settingDb.getNameColumnSecondTable()+" = ?));");
            preparedStatement.setString(1, word);
            preparedStatement.setString(2, translete);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void AddSimpleTable(String word, String table, String colunmName) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO  " + table + "("+colunmName+")" +
                      "VALUES (?);");
        preparedStatement.setString(1, word);
        preparedStatement.executeUpdate();
    }



    public boolean foundWord(String word, Boolean first){
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM " + ((first) ? settingDb.getNameTableFirst() : settingDb.getNameTableSecond()) + " WHERE " + ((first) ? settingDb.getNameColumnFirstTable() : settingDb.getNameColumnSecondTable()) + " = ?;");
            preparedStatement.setString(1, word);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    @Override
    public List find(String word) {
        List<String> list = findWord(word, true);
        if (list.size()==0){
            return findWord(word, false);
        }else
            return list;
    }




    public List findWord(String word, boolean first) {
        List<String> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT " + settingDb.getNameColumnFirstTable() + ", " + settingDb.getNameColumnSecondTable() + "\n" +
                    "FROM " + settingDb.getNameTableThird() + "\n" +
                    "NATURAL INNER JOIN " + settingDb.getNameTableFirst() + "\n" +
                    "NATURAL INNER JOIN " + settingDb.getNameTableSecond() + "\n" +
                    "WHERE " + ((first) ? settingDb.getNameColumnFirstTable() : settingDb.getNameColumnSecondTable()) + " = ?;");
            preparedStatement.setString(1, word);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(settingDb.getNameColumnFirstTable()) + " - " + rs.getString(settingDb.getNameColumnSecondTable()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List selectAll() {
        List<String> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT " + settingDb.getNameColumnFirstTable() + ", " + settingDb.getNameColumnSecondTable() + "\n" +
                    "FROM " + settingDb.getNameTableThird() + "\n" +
                    "NATURAL INNER JOIN " + settingDb.getNameTableFirst() + "\n" +
                    "NATURAL INNER JOIN " + settingDb.getNameTableSecond() + ";");
            ResultSet rs3 = preparedStatement.executeQuery();
            while(rs3.next()) {
                list.add("\n" +rs3.getString("english_word")+" - " +rs3.getString("russian_word"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean delite(String word) {
        if (findWord(word, true).size()>0){
            return deliteWord(word, true);
        }else{
            return deliteWord(word, false);
        }
    }

    public boolean deliteWord(String word, Boolean first) {
        try {

            preparedStatement = connection.prepareStatement("DELETE FROM " + ((first) ? settingDb.getNameTableFirst() : settingDb.getNameTableSecond()) + "\n" +
                    "WHERE " + ((first) ? settingDb.getNameColumnFirstTable() : settingDb.getNameColumnSecondTable()) + " = ?;");
            preparedStatement.setString(1, word);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
