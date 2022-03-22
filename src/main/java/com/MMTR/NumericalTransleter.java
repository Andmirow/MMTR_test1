package com.MMTR;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumericalTransleter extends Transleter{
    public NumericalTransleter(String plase, String nameDataBase) throws SQLException, MyChekedException {
        super(plase, nameDataBase);
    }

    public NumericalTransleter(){}

    @Override
    public  boolean personalchekWord(String word){
        Pattern pattern = Pattern.compile("[0-9][0-9][0-9][0-9][0-9]");
        Matcher matcher = pattern.matcher(word);
        return matcher.matches();
    }

}
