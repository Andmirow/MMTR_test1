package com.MMTR.readers.db_reader;

public class SettingDb {

    private String nameTableFirst;
    private String nameColumnFirstTable;

    private String nameTableSecond;
    private String nameColumnSecondTable;

    private String nameTableThird;
    private String nameFirstColumnThirdTable;
    private String nameSecondColumnThirdTable;


    public String getNameFirstColumnThirdTable() {
        return nameFirstColumnThirdTable;
    }

    public void setNameFirstColumnThirdTable(String nameFirstColumnThirdTable) {
        this.nameFirstColumnThirdTable = nameFirstColumnThirdTable;
    }

    public String getNameSecondColumnThirdTable() {
        return nameSecondColumnThirdTable;
    }

    public void setNameSecondColumnThirdTable(String nameSecondColumnThirdTable) {
        this.nameSecondColumnThirdTable = nameSecondColumnThirdTable;
    }

    public SettingDb(String nameTableFirst, String nameColumnFirstTable, String nameTableSecond, String nameColumnSecondTable, String nameTableThird,String nameFirstColumnThirdTable, String nameSecondColumnThirdTable) {
        this.nameTableFirst = nameTableFirst;
        this.nameColumnFirstTable = nameColumnFirstTable;
        this.nameTableSecond = nameTableSecond;
        this.nameColumnSecondTable = nameColumnSecondTable;
        this.nameTableThird = nameTableThird;
        this.nameFirstColumnThirdTable = nameFirstColumnThirdTable;
        this.nameSecondColumnThirdTable = nameSecondColumnThirdTable;
    }

    public String getNameTableFirst() {
        return nameTableFirst;
    }

    public void setNameTableFirst(String nameTableFirst) {
        this.nameTableFirst = nameTableFirst;
    }

    public String getNameColumnFirstTable() {
        return nameColumnFirstTable;
    }

    public void setNameColumnFirstTable(String nameColumnFirstTable) {
        this.nameColumnFirstTable = nameColumnFirstTable;
    }

    public String getNameTableSecond() {
        return nameTableSecond;
    }

    public void setNameTableSecond(String nameTableSecond) {
        this.nameTableSecond = nameTableSecond;
    }

    public String getNameColumnSecondTable() {
        return nameColumnSecondTable;
    }

    public void setNameColumnSecondTable(String nameColumnSecondTable) {
        this.nameColumnSecondTable = nameColumnSecondTable;
    }

    public String getNameTableThird() {
        return nameTableThird;
    }

    public void setNameTableThird(String nameTableThird) {
        this.nameTableThird = nameTableThird;
    }
}
