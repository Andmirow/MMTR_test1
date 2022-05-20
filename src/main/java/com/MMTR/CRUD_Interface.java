package com.MMTR;

import java.util.List;

public interface CRUD_Interface {
    public boolean Add(String word, String translete);
    public List find(String word);
    public List selectAll();
    public boolean delite(String word);
}
