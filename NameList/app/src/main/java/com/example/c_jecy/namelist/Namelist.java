package com.example.c_jecy.namelist;

import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.view.Window;
import android.view.*;
import android.widget.*;
import java.util.*;
import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;



public class Namelist extends AppCompatActivity {
/*
    public ListView list;
    private MyDatabaseHelper dbHelper;
    private List<person> lsp;
    //public static TextView number ;
    //static  View mainview;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //SQLiteDatabase db=openOrCreateDatabase(MyDatabaseHelper.getMyDatabaseName("namelist.db"),Context.MODE_PRIVATE,null);

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_namelist);
        //number= (TextView)findViewById(R.id.stu_number);


        Button b=(Button)findViewById(R.id.dmbutton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(Namelist.this,List.class);
                RadioGroup group=(RadioGroup)findViewById(R.id.radioGroup);
                int i=group.getCheckedRadioButtonId()-R.id.sel1;
                it.putExtra("selectclass",i);
                Namelist.this.startActivity(it);
            }
        });
        Button a=(Button)findViewById(R.id.addButton);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(Namelist.this,AddStudent.class);
                Namelist.this.startActivity(it);
            }
        });


    }

};
