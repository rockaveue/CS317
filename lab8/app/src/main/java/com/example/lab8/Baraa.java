package com.example.lab8;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class Baraa extends MySQLiteOpenHelper{

    public SQLiteDatabase DB;
    public static final String TABLE_BARAA = "baraa";
    public static final String TABLE_BARAA_TURUL = "baraa_turul";
    public static final String TABLE_BARAA_TURUL_ID = "baraa_turul_id";
    public static final String ID = "id";
    public static final String NER = "ner";
    public static final String UNE = "une";
    public static final String TURUL = "turul";
    public static final String CREATE_TABLE_BARAA = "CREATE TABLE "
            + TABLE_BARAA + "(" + ID + " INTEGER PRIMARY KEY AUTO_INCREMENT," + NER
            + " TEXT," + UNE + " DOUBLE," + TURUL
            + " INTEGER, FOREIGN KEY(" + TURUL + ") REFERENCES " + TABLE_BARAA_TURUL
            + "(" + TABLE_BARAA_TURUL_ID + "));";
    public Baraa(Context context) {
        super(context);
    }

    public void exec(String query){
        super.myexec(query);
    }

    public String createTable(){
        return CREATE_TABLE_BARAA;
    }

    public void insertData(int id, String ner, Double une, int turul_id){
        String query = "INSERT INTO " +  TABLE_BARAA + "(" + ID + "," + NER + "," + UNE + "," + TURUL + ") " +
            " VALUES(" + id + "," + ner + "," + une + "," + turul_id + ")";
        exec(query);
    }
    public void updateData(int id, String ner, Double une, int turul_id){
        String query = "UPDATE " + TABLE_BARAA +
        " SET " +  NER + "=" + ner + "," +  UNE + "=" + une + "," +  TURUL + "=" + turul_id +
       " WHERE " + ID + "=" + id;
        exec(query);
    }
    public List<String> showData(){
        List<String> list = null;
        String query = "SELECT * FROM " + TABLE_BARAA;
        Cursor cursor = DB.rawQuery("select * from " + TABLE_BARAA,null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(cursor.getColumnIndex(ID));
                String name1 = cursor.getString(cursor.getColumnIndex(NER));
                String name2 = cursor.getString(cursor.getColumnIndex(UNE));
                String name3 = cursor.getString(cursor.getColumnIndex(TURUL));

                list.add(name);
                list.add(name1);
                list.add(name2);
                list.add(name3);
                cursor.moveToNext();
            }
        }
        return list;
    }



}
