package com.MMTR.readers;

import com.MMTR.CRUD_Interface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TxtReader implements CRUD_Interface {

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

    private boolean write(){
        boolean result;
        try {
            PrintWriter pw = new PrintWriter(way);
            for (String line : list){
                pw.write(line);
                pw.write("\n");
                }
            pw.flush();
            pw.close();
            result = true;
        } catch (FileNotFoundException e) {
            result = false;
        }
        return result;

    }

    @Override
    public boolean Add(String word, String translete) {
        boolean result;
        boolean up = false;
        StringBuilder sb = new StringBuilder();
        List<String> temp = find(word);
        if (temp.size()==0){
            sb.append(word).append(" - ").append(translete);
            list.add(sb.toString());
        }else{
            list.remove(temp.get(0));
            list.add(sb.append(temp.get(0).replace(word + " -","")).append(", ").append(translete).toString());
        }
        return write();
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
        StringBuilder sb = new StringBuilder();
        List<String> temp = find(word);
        if (temp.size()>0){
            list.remove(temp.get(0));
            return  write();
        }else{
            return false;
        }
    }
}
