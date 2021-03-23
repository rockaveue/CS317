package com.example.lab8;

import android.content.Context;

public class Baraa_turul extends MySQLiteOpenHelper{

    public static final String TABLE_BARAA_TURUL= "baraa_turul";
    public static final String ID = "id";
    public static final String NER = "ner";
    public static final String CREATE_TABLE_TURUL = "CREATE TABLE "
            + TABLE_BARAA_TURUL + "(" + ID + " INTEGER PRIMARY KEY AUTO_INCREMENT," + NER
            + " TEXT);";
    public Baraa_turul(Context context) {
        super(context);
    }

    public void exec(String query){
        super.myexec(query);
    }

    public String createTable(){
        return CREATE_TABLE_TURUL;
    }

    public void insertData(int turul_id, String ner){
        String query = "INSERT INTO " +  TABLE_BARAA_TURUL + "(" + ID + "," + NER + ") " +
                " VALUES(" + turul_id + "," + ner + ")";
        exec(query);
    }
    public void updateData(int id, String ner){
        String query = "UPDATE " + TABLE_BARAA_TURUL +
                " SET " +  NER + "=" + ner  +
                " WHERE " + ID + "=" + id;
        exec(query);
    }
    public void showData(){
        String query = "SELECT * FROM " + TABLE_BARAA_TURUL;
        exec(query);
    }

}
