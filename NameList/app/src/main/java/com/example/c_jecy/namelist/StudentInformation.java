package com.example.c_jecy.namelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.*;
import android.content.*;
import android.widget.*;
import android.view.*;

public class StudentInformation extends AppCompatActivity {

    private MyDatabaseHelper dbHelper=new MyDatabaseHelper(this, "namelist.db",null, 1);
    //private Switch[] sc;
    private RadioGroup classSelect;
    private RadioGroup condition;
    private Button back;
    private String id;
    private int REQUEST_CODE=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);

        Intent it=StudentInformation.this.getIntent();
        String na=it.getStringExtra("name");
        TextView name = (TextView) findViewById(R.id.stu_name);
        name.setText(na);

        String nu=it.getStringExtra("id");
        TextView num = (TextView) findViewById(R.id.stu_id);
        num.setText(nu);

        String cl=it.getStringExtra("_class");
        TextView _class = (TextView) findViewById(R.id.stu_class);
        _class.setText(cl);



        int se=it.getIntExtra("selectclass",0);

        id=nu;

        /*
        sc=new Switch[5];
        sc[0]=(Switch)findViewById(R.id.switch1);
        sc[1]=(Switch)findViewById(R.id.switch2);
        sc[2]=(Switch)findViewById(R.id.switch3);
        sc[3]=(Switch)findViewById(R.id.switch4);
        sc[4]=(Switch)findViewById(R.id.switch5);
*/

        ImageView im=(ImageView)findViewById(R.id.stu_image);
        if(im!=null) {
            im.setImageBitmap(dbHelper.getImgById(nu));
        }


        final int[] select=dbHelper.searchScoreById(nu);
        classSelect=(RadioGroup)findViewById(R.id.classselect);
        classSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int sel=group.getCheckedRadioButtonId()-R.id.classButton1;
                selectCondition(select[sel]);
            }
        });

        RadioButton bs[]=new RadioButton[2];
        bs[0]=(RadioButton) findViewById(R.id.classButton1);
        bs[1]=(RadioButton) findViewById(R.id.classButton2);

        bs[se].setChecked(true);

        condition=(RadioGroup)findViewById(R.id.condition);
        condition.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int sel=classSelect.getCheckedRadioButtonId()-R.id.classButton1;
                select[sel]=condition.getCheckedRadioButtonId()-R.id.radioButton0;
            }
        });


        /*
        for(int i=0;i<5;i++){
            if(select[i]==1){
                sc[i].setChecked(true);
            }
        }*/

        //name.setText(select[0]+"");
        /*if(select[0]==0){
            sc[0].setChecked(true);
        }*/
        back=(Button)findViewById(R.id.button);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                /*
                for(int i=0;i<5;i++){
                    if(sc[i].isChecked()){
                        select[i]=1;
                    }
                    else{
                        select[i]=0;
                    }
                }
                */
                /*int sel=classSelect.getCheckedRadioButtonId()-R.id.classButton1;
                select[sel]=condition.getCheckedRadioButtonId()-R.id.radioButton0;*/
                dbHelper.updateScoreById(dbHelper.getWritableDatabase(),id,select);
                Intent inte=new Intent();
                inte.putExtra("selectedstudent",Integer.getInteger(id));
                StudentInformation.this.finish();
            }});
    }
    void selectCondition(int i){
        RadioButton[] b=new RadioButton[5];
        b[0]=(RadioButton) findViewById(R.id.radioButton0);
        b[1]=(RadioButton) findViewById(R.id.radioButton1);
        b[2]=(RadioButton) findViewById(R.id.radioButton2);
        b[3]=(RadioButton) findViewById(R.id.radioButton3);
        b[4]=(RadioButton) findViewById(R.id.radioButton4);

        b[i].setChecked(true);
        for(int j=0;j<5;j++) {
            if(j!=i)
            {b[j].setChecked(false);}
        }
    }
}
