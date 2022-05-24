package com.MMTR.readers.db_reader;

import com.MMTR.servis.CRUD_Interface;

import java.sql.Connection;

public interface DB_Interfase{

    public Connection getConnection();
    public void disconnect();

}
