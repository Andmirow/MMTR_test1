package com.MMTR.old;

import java.sql.SQLException;

public class FactoryTranslater {

    public static Transleter getTranslater(int nomber, String plase, String nameDataBase) throws SQLException, MyChekedException {
        Transleter res = null;
        if (nomber ==1){
            res = new EnglishTransleter(plase, nameDataBase);
        }else if (nomber ==2){
            res = new NumericalTransleter(plase, nameDataBase);
        }else
            throw new MyChekedException("не верный формат словаря");

        return res;
    }

    public static Transleter getTranslater(String url, String login, String password){
        return null;
    }



}
