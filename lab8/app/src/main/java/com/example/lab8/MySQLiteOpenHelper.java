package com.example.lab8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper{

    public SQLiteDatabase DB;
    public String DBPath;
    public static String DBName = "testDatabase";
    public static final int version = '1';
    public static Context currentContext;
    public static String tableName = "Baraa";
    public static String tableName1 = "Baraa_turul";
    public Baraa baraa;
    public Baraa_turul baraa_turul;

    public MySQLiteOpenHelper(Context context) {
        super(context, DBName, null, version);
        /*currentContext = context;
        DBPath = "/data/data/" + context.getPackageName() + "/databases";
        createDatabase();*/
        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(baraa.CREATE_TABLE_BARAA);
        db.execSQL(baraa_turul.CREATE_TABLE_TURUL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + baraa.TABLE_BARAA);
        db.execSQL("DROP TABLE IF EXISTS " + baraa_turul.TABLE_BARAA_TURUL);
        onCreate(db);
    }

    public boolean insertBaraa(String ner, Double une, int turul_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(baraa.NER, ner);
        contentValues.put(baraa.UNE, une);
        contentValues.put(baraa.TURUL, turul_id);
        long result = db.insert(Baraa.TABLE_BARAA, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllBaraa(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+Baraa.TABLE_BARAA, null);
        return res;
    }

    public boolean updateBaraa(int id, String ner, Double une, int turul_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(baraa.NER, ner);
        contentValues.put(baraa.UNE, une);
        contentValues.put(baraa.TURUL, turul_id);
        db.update(Baraa.TABLE_BARAA, contentValues, "id = ?", new String[]{ String.valueOf(id) });
        return true;
    }

    public Integer deleteBaraa (int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(Baraa.TABLE_BARAA, "id = ?", new String[]{String.valueOf(id)});
    }

    public boolean insertBaraaTurul(String baraa_turul_ner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Baraa_turul.NER, baraa_turul_ner);
        long result = db.insert(Baraa_turul.TABLE_BARAA_TURUL, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllBaraaTurul(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+Baraa_turul.TABLE_BARAA_TURUL, null);
        return res;
    }
    public boolean updateBaraaTurul(int id, String baraa_turul_ner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Baraa_turul.NER, baraa_turul_ner);
        db.update(Baraa_turul.TABLE_BARAA_TURUL, contentValues, "id = ?", new String[]{ String.valueOf(id) });
        return true;
    }
    public Integer deleteBaraaTurul(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(Baraa_turul.TABLE_BARAA_TURUL, "id = ?", new String[]{String.valueOf(id)});
    }
    public void myexec(String query){
        DB.execSQL(query);
    }

}
