package com.MMTR.servis;

import com.MMTR.readers.db_reader.DB_Interfase;
import com.MMTR.servis.CRUD_Interface;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDAO implements CRUD_Interface {

    private TypeLanguage typeLanguage;
    private CRUD_Interface crud_interface;

    public DB_Interfase getDb_interfase() {
        return db_interfase;
    }

    private DB_Interfase db_interfase;
    private String mask;

    public UserDAO(CRUD_Interface crud_interface){
        this.crud_interface = crud_interface;
    }

    public UserDAO(CRUD_Interface crud_interface, String mask){
        this.crud_interface = crud_interface;
        if (!mask.equals("")){
            this.mask = mask;
        }
   }

    public UserDAO(CRUD_Interface crud_interface, DB_Interfase db_interfase){
        this.crud_interface = crud_interface;
        this.db_interfase = db_interfase;
    }
    public UserDAO(CRUD_Interface crud_interface, String mask, DB_Interfase db_interfase){
        this.crud_interface = crud_interface;
        this.db_interfase = db_interfase;
        if (!mask.equals("")){
            this.mask = mask;
        }
    }

    @Override
    public boolean Add(String word, String translete) {
        if (mask != null){
            Pattern pattern = Pattern.compile(mask);
            Matcher matcher = pattern.matcher(word);
            if (matcher.matches()){
                return crud_interface.Add(word,translete);
            }else{
                return false;
            }
        }else {
            return crud_interface.Add(word,translete);
        }
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

