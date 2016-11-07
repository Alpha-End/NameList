package com.example.c_jecy.namelist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class List extends AppCompatActivity {

    public ListView list;
    private MyDatabaseHelper dbHelper;
    private java.util.List<person> lsp;
    private int selectCourse;
    private int REQUEST_CODE=1;

    //public static TextView number ;
    //static  View mainview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //SQLiteDatabase db=openOrCreateDatabase(MyDatabaseHelper.getMyDatabaseName("namelist.db"),Context.MODE_PRIVATE,null);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent it=List.this.getIntent();
        selectCourse=it.getIntExtra("selectclass",0);

        //number= (TextView)findViewById(R.id.stu_number);


        dbHelper=new MyDatabaseHelper(this, "namelist.db",null, 1);
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        //Bitmap bmp= BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/cc.bmp"));
        //MyDatabaseHelper.insertPic(db,bmp);
        //Cursor c=db.rawQuery("SELECT * FROM list",null);
        //Cursor c=db.rawQuery("SELECT * FROM list",null);
        Cursor c=db.query("list",null,null,null,null,null,null);


        java.util.List<String> lsn=new ArrayList<String>();
        lsp = new ArrayList<person>();
        person p;

        //lsn.add(c.getCount()+"");
        while(c.moveToNext()){
            String id = c.getString(c.getColumnIndex("id"));
            String name =c.getString(c.getColumnIndex("name"));
            String _class =c.getString(c.getColumnIndex("class"));
            p=new person(id,name,_class);
            lsp.add(p);
            //lsn.add("老王");

        }

        c.close();




        for(int position=0;position<lsp.size();position++){
            person pe=lsp.get(position);
            lsn.add(pe.name);
        }


        list= (ListView)findViewById(R.id.list);
        list.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lsn));


        OnItemClickListenerImpl cli=new OnItemClickListenerImpl(this);
        list.setOnItemClickListener(cli);
        list.setAdapter(new BaseAdapter() {
            View view;
                            public int getCount() {
                                return lsp.size();
                            }
                            public View getView(int position, View convertView, ViewGroup parent) {
                                //View view;
                                if (convertView == null) {
                                    view = View.inflate(getBaseContext(), R.layout.studentlayout, null);
                                } else {
                                    view = convertView;
                                }
                                //从studentlist中取出一行数据，position相当于数组下标,可以实现逐行取数据
                                person st = lsp.get(position);
                                TextView number = (TextView) view.findViewById(R.id.stu_number);
                                TextView name = (TextView) view.findViewById(R.id.stu_name);
                                TextView _class = (TextView) view.findViewById(R.id.stu_class);
                                number.setText(st.id);
                                name.setText(st.name);
                                _class.setText(st._class);
                                //view.setId(Integer.getInteger(st.id));
                                return view;
                            }
                            public Object getItem(int position) {
                                return view;
                            }
                            public long getItemId(int position) {return view.getId();}
                        }
        );



        //list = (ListView)findViewById(R.id.list);
    }

    public void jump(String id,String name,String _class){
        Intent it=new Intent(List.this,StudentInformation.class);
        it.putExtra("id",id);
        it.putExtra("name",name);
        it.putExtra("_class",_class);
        it.putExtra("selectclass",selectCourse);

        List.this.startActivityForResult(it,REQUEST_CODE);
    }
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);


    }

};
class OnItemClickListenerImpl  implements AdapterView.OnItemClickListener {
    private List act;
    OnItemClickListenerImpl(List a){act=a;
    }
    public void onItemClick(AdapterView<?> parent, View view, int position,long id){

        TextView t=(TextView)view.findViewById(R.id.stu_number);
        String nu=t.getText().toString();
        //t.setTextColor(Color.rgb(255,100,100));

        t=(TextView)view.findViewById(R.id.stu_name);
        String na=t.getText().toString();
        //t.setTextColor(Color.rgb(255,100,100));

        t=(TextView)view.findViewById(R.id.stu_class);
        String cl=t.getText().toString();
        //t.setTextColor(Color.rgb(255,100,100));

        act.jump(nu,na,cl);


    }

};
class person{
    String id;
    String name;
    String _class;
    int []score;
    person(String i,String n,String c){
        id=i;name=n;_class=c;
        score=new int[5];}
}