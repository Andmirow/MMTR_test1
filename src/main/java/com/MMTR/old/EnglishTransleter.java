package com.MMTR.old;

import com.MMTR.Transleter;
import com.MMTR.old.MyChekedException;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnglishTransleter extends Transleter {

    public EnglishTransleter(String plase, String nameDataBase) throws SQLException, MyChekedException {
        super(plase, nameDataBase);
    }

    public EnglishTransleter(){}

    @Override
    public  boolean personalchekWord(String word){
        Pattern pattern = Pattern.compile("[a-zA-Z][a-zA-Z][a-zA-Z][a-zA-Z]");
        Matcher matcher = pattern.matcher(word);
        return matcher.matches();
    }

}
