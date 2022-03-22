package com.MMTR;

import java.sql.SQLException;

public interface TransleterInterface {
    public boolean Add(String firstLanguageWord, String secondLanguageWord) throws SQLException, MyChekedException;
    public String find(String word)  throws SQLException,MyChekedException ;
    public void selectAll() throws SQLException,MyChekedException;
    public boolean delite(String word) throws SQLException,MyChekedException;
}
