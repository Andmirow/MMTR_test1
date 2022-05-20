package com.MMTR;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDAO implements CRUD_Interface{

    CRUD_Interface crud_interface;
    String mask;

    public UserDAO(CRUD_Interface crud_interface){
        this.crud_interface = crud_interface;
    }

    public UserDAO(CRUD_Interface crud_interface, String mask){
        this.crud_interface = crud_interface;
        this.mask = mask;
   }

    @Override
    public boolean Add(String word, String translete) {
        if (mask != null){
            Pattern pattern = Pattern.compile(mask);
            Matcher matcher = pattern.matcher(word);
            if (matcher.matches()){
                crud_interface.Add(word,translete);
            }else{
                return false;
            }
        }else {
            crud_interface.Add(word,translete);
        }
        return false;
    }

    @Override
    public List find(String word) {
        return crud_interface.find(word);
    }

    @Override
    public List selectAll() {
        return crud_interface.selectAll();
    }

    @Override
    public boolean delite(String word) {
        return crud_interface.delite(word);
    }
}
