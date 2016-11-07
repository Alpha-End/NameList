package com.example.c_jecy.namelist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class AddStudent extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);


        dbHelper=new MyDatabaseHelper(this, "namelist.db",null, 1);
        Button a=(Button)findViewById(R.id.addsurebutton);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id,_class,name;
                EditText e=(EditText)findViewById(R.id.ideditText);
                id=e.getText().toString().trim();
                e=(EditText)findViewById(R.id.nameeditText);
                name=e.getText().toString().trim();
                e=(EditText)findViewById(R.id.classeditText);
                _class=e.getText().toString().trim();
                dbHelper.insertStudent(id,name,_class);
                finish();
            }
        });

    }
}
