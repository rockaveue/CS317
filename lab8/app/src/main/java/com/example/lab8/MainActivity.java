package com.example.lab8;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> results = new ArrayList<String>();
    private String tableName = MySQLiteOpenHelper.tableName;
    private MySQLiteOpenHelper myDB;
    private Baraa baraa;
    private Baraa_turul baraa_turul;
    EditText editId, editName, editUne, editTurul, editTurulId, editTurulNer;
    Button btnAdd, btnDel, btnCre, btnUpt, btnShw, btnAdd2, btnDel2, btnCre2, btnUpt2, btnShw2;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        openAndQueryDatabase();
//        displayResultList();
        myDB = new MySQLiteOpenHelper(this);
        editId = (EditText)findViewById(R.id.editText_id);
        editName = (EditText)findViewById(R.id.editText_name);
        editUne = (EditText)findViewById(R.id.editText_une);
        editTurul = (EditText)findViewById(R.id.editText_turul);

        editTurulId = (EditText)findViewById(R.id.editText_turul_id);
        editTurulNer = (EditText)findViewById(R.id.editText_turul_ner);

        btnAdd = (Button)findViewById(R.id.button_add);
        btnDel = (Button)findViewById(R.id.button_delete);
        btnUpt = (Button)findViewById(R.id.button_update);
        btnShw = (Button)findViewById(R.id.button_viewAll);

        btnAdd2 = (Button)findViewById(R.id.button_add2);
        btnDel2 = (Button)findViewById(R.id.button_delete2);
        btnUpt2 = (Button)findViewById(R.id.button_update2);
        btnShw2 = (Button)findViewById(R.id.button_viewAll2);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDB.insertBaraa(editName.getText().toString(),
                        Double.parseDouble(editUne.getText().toString()), Integer.parseInt(editTurul.getText().toString()));
//                baraa.insertData(Integer.parseInt(editId.getText().toString()), editName.getText().toString(),
//                        Double.parseDouble(editUne.getText().toString()), Integer.parseInt(editTurul.getText().toString()));
                if(isInserted){
                    Toast.makeText(MainActivity.this,"Бичлэг орлоо", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,"Бичлэг ороогүй", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDel.setOnClickListener(v -> {
            baraa.deleteBaraa(Integer.parseInt(editId.getText().toString()));
            Toast.makeText(MainActivity.this,"Бичлэг устгагдлаа", Toast.LENGTH_SHORT).show();
        });
        btnShw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.getAllBaraa();
                if(res.getCount() == 0){
                    showMessage("Алдаа", "Бичлэг олдсонгүй.");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("id:\t " + res.getString(0) + "\n");
                    buffer.append("ner: \t" + res.getString(1) + "\n");
                    buffer.append("une: \t" + res.getString(2) + "\n");
                    buffer.append("turul_id: \t" + res.getString(3) + "\n\n");
                }
                showMessage("Барааны бичлэг", buffer.toString());
            }
        });
        btnUpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDB.updateBaraa(Integer.parseInt(editId.getText().toString()), editName.getText().toString(),
                        Double.parseDouble(editUne.getText().toString()), Integer.parseInt(editTurul.getText().toString()));
                if (isUpdate){
                    Toast.makeText(MainActivity.this,"Бичлэг шинэчлэгдлээ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,"Бичлэг шинэчлэгдсэнгүй", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDB.deleteBaraa(Integer.parseInt(editId.getText().toString()));
                if ( deletedRows > 0){
                    Toast.makeText(MainActivity.this,"Бичлэг устгагдлаа", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,"Бичлэг устгагдсангүй", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDB.insertBaraaTurul(editTurulNer.getText().toString());
                if(isInserted){
                    Toast.makeText(MainActivity.this,"Бичлэг орлоо", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,"Бичлэг ороогүй", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnShw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.getAllBaraaTurul();
                if(res.getCount() == 0){
                    showMessage("Алдаа", "Бичлэг олдсонгүй.");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("id:\t " + res.getString(0) + "\n");
                    buffer.append("turul_ner: \t" + res.getString(1) + "\n\n");
                }
                showMessage("Барааны төрлийн бичлэг", buffer.toString());
            }
        });
        btnUpt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDB.updateBaraaTurul(Integer.parseInt(editTurulId.getText().toString()), editTurulNer.getText().toString());
                if (isUpdate){
                    Toast.makeText(MainActivity.this,"Бичлэг шинэчлэгдлээ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,"Бичлэг шинэчлэгдсэнгүй", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDB.deleteBaraaTurul(Integer.parseInt(editTurulId.getText().toString()));
                if ( deletedRows > 0){
                    Toast.makeText(MainActivity.this,"Бичлэг устгагдлаа", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,"Бичлэг устгагдсангүй", Toast.LENGTH_SHORT).show();
                }
            }
        });
}
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}