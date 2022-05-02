package com.MMTR;

import java.sql.SQLException;
import java.util.List;

public interface ReaderInterface {
    public boolean Add(String word, String translete);
    public List find(String word);
    public List selectAll();
    public boolean delite(String word);
}
