package com.MMTR;

import java.awt.font.FontRenderContext;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TxtReader implements ReaderInterface{

    private List<String> list;
    private String way;

    public TxtReader(String way){
        this.way = way;
        inicialization();
    }

    private void inicialization(){
        try {
            list = Files.readAllLines(Path.of(way));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean Add(String word, String translete) {
        return false;
    }

    @Override
    public List find(String word) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String line : list){
            if (line.contains(word)){
                arrayList.add(line);
            }
        }
        return arrayList;
    }

    @Override
    public List selectAll() {
        return list;
    }

    @Override
    public boolean delite(String word) {
        return false;
    }
}
