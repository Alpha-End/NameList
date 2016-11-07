package com.example.c_jecy.namelist;

/**
 * Created by C_jecy on 2016/10/30.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.Environment;
import java.io.*;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static String CREATE_list = "create table list(id text primary key,name text,class text,score1 integer default(0),score2 integer default(0),score3 integer default(0),score4 integer default(0),score5 integer default(0),ima blob);";
    private Context context;


    public MyDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
        //调用父类构造函数
        super(context, getMyDatabaseName(name), factory, version);
    }

    public static String getMyDatabaseName(String name) {
        String databasename = name;
        boolean isSdcardEnable = false;
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {//SDCard是否插入
            isSdcardEnable = true;
        }
        String dbPath = null;
        if (isSdcardEnable) {
            dbPath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/namelist/";
        } else {//未插入SDCard，建在内存中

        }
        File dbp = new File(dbPath);
        if (!dbp.exists()) {
            dbp.mkdirs();
        }
        databasename = dbPath + databasename;
        return databasename;
    }

    /**
     * 当数据库首次创建时执行该方法，一般将创建表等初始化操作放在该方法中执行.
     * 重写onCreate方法，调用execSQL方法创建表
     */
    @Override

    public void onCreate(SQLiteDatabase db) {
        //创建数据库sql语句

        db.execSQL(CREATE_list);
       /* insertlist();
        for(int i=0;i<98;i++) {
            db.execSQL(str[i]);
        }*/
        //Bitmap bmp= BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/cc.bmp"));
        //insertPic(db,bmp);
        insertPic(db);
    }
    public  void updateImgByid(SQLiteDatabase db,String id){
        Bitmap bmp;

            bmp= BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/cc.bmp"));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, os);
        //SQLiteDatabase db=this.getWritableDatabase();
        byte[] ss = os.toByteArray();
        ContentValues values = new ContentValues();
        values.put("ima", ss);
        db.update("list", values, "id=?", new String[]{id});
    }

    public int[] searchScoreById(String id){
        int []s=new int[5];
        Cursor c=this.getWritableDatabase().rawQuery("select * from list where id=?",new String[]{id});

        c.moveToNext();

        s[0]=c.getInt(c.getColumnIndex("score1"));
        s[1]=c.getInt(c.getColumnIndex("score2"));
        s[2]=c.getInt(c.getColumnIndex("score3"));
        s[3]=c.getInt(c.getColumnIndex("score4"));
        s[4]=c.getInt(c.getColumnIndex("score5"));
        return s;
    }
    public Bitmap getImgById(String id){
        Cursor c=this.getWritableDatabase().rawQuery("select * from list where id=?",new String[]{id});

        c.moveToNext();

        byte[] blob = c.getBlob(c.getColumnIndex("ima"));
        if(blob==null){return null;}
        Bitmap bmp = BitmapFactory.decodeByteArray(blob, 0, blob.length);
        return bmp;
    }

    public  void updateScoreById(SQLiteDatabase db,String id,int []s){
        String sql="update list set score1="+s[0]+",score2="+s[1]+",score3="+s[2]+",score4="+s[3]+",score5="+s[4]+" where id='"+id+"';";
        db.execSQL(sql);
    }

    @Override
    //当打开数据库时传入的版本号与当前的版本号不同时会调用该方法
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertPic(SQLiteDatabase db){
        //SQLiteDatabase db=this.getWritableDatabase();

        insertlist();
        for(int i=0;i<98;i++) {
            ContentValues values = new ContentValues();
            values.put("id", str1[i]);
            values.put("name", str2[i]);
            values.put("class", str3[i]);
            db.insert("list", null, values);
            updateImgByid(db,str1[i]);
        }
    }
    static private void insertlist(){
        //str[0]="insert into list(id,name,class) values ('631406010102','莫天金','计科1401班');";str[1]="insert into list(id,name,class) values ('631406010103','吴国平','计科1401班');";str[2]="insert into list(id,name,class) values ('631406010104','孙文斌','计科1401班');";str[3]="insert into list(id,name,class) values ('631406010105','潘俊旭','计科1401班');";str[4]="insert into list(id,name,class) values ('631406010106','石佳磊','计科1401班');";str[5]="insert into list(id,name,class) values ('631406010107','赵权','计科1401班');";str[6]="insert into list(id,name,class) values ('631406010108','马鹏','计科1401班');";str[7]="insert into list(id,name,class) values ('631406010109','郭文浩','计科1401班');";str[8]="insert into list(id,name,class) values ('631406010110','李季','计科1401班');";str[9]="insert into list(id,name,class) values ('631406010111','陈仕豪','计科1401班');";str[10]="insert into list(id,name,class) values ('631406010112','杜菲','计科1401班');";str[11]="insert into list(id,name,class) values ('631406010113','李红兵','计科1401班');";str[12]="insert into list(id,name,class) values ('631406010114','蔡佳辰','计科1401班');";str[13]="insert into list(id,name,class) values ('631406010115','肖洒益','计科1401班');";str[14]="insert into list(id,name,class) values ('631406010117','伍凯荣','计科1401班');";str[15]="insert into list(id,name,class) values ('631406010118','张林','计科1401班');";str[16]="insert into list(id,name,class) values ('631406010119','王斌','计科1401班');";str[17]="insert into list(id,name,class) values ('631406010120','廖宇峰','计科1401班');";str[18]="insert into list(id,name,class) values ('631406010122','谭建','计科1401班');";str[19]="insert into list(id,name,class) values ('631406010123','左永和','计科1401班');";str[20]="insert into list(id,name,class) values ('631406010124','王增辉','计科1401班');";str[21]="insert into list(id,name,class) values ('631406010128','任中豪','计科1401班');";str[22]="insert into list(id,name,class) values ('631406010129','何泳桦','计科1401班');";str[23]="insert into list(id,name,class) values ('631406010130','张力','计科1401班');";str[24]="insert into list(id,name,class) values ('631406010131','任达','计科1401班');";str[25]="insert into list(id,name,class) values ('631404090425','李自力','计科1402班');";str[26]="insert into list(id,name,class) values ('631406010201','肖霞','计科1402班');";str[27]="insert into list(id,name,class) values ('631406010203','郑建峰','计科1402班');";str[28]="insert into list(id,name,class) values ('631406010206','程飘','计科1402班');";str[29]="insert into list(id,name,class) values ('631406010207','王浩','计科1402班');";str[30]="insert into list(id,name,class) values ('631406010208','李建鹏','计科1402班');";str[31]="insert into list(id,name,class) values ('631406010209','张向守','计科1402班');";str[32]="insert into list(id,name,class) values ('631406010210','邱凯','计科1402班');";str[33]="insert into list(id,name,class) values ('631406010211','罗七奇','计科1402班');";str[34]="insert into list(id,name,class) values ('631406010214','李佩科','计科1402班');";str[35]="insert into list(id,name,class) values ('631406010216','黄许飞','计科1402班');";str[36]="insert into list(id,name,class) values ('631406010217','钟富胜','计科1402班');";str[37]="insert into list(id,name,class) values ('631406010218','陈鸿','计科1402班');";str[38]="insert into list(id,name,class) values ('631406010219','易家洛','计科1402班');";str[39]="insert into list(id,name,class) values ('631406010220','邓强','计科1402班');";str[40]="insert into list(id,name,class) values ('631406010222','原晨','计科1402班');";str[41]="insert into list(id,name,class) values ('631406010223','袁益','计科1402班');";str[42]="insert into list(id,name,class) values ('631406010224','石峻臣','计科1402班');";str[43]="insert into list(id,name,class) values ('631406010226','张洋','计科1402班');";str[44]="insert into list(id,name,class) values ('631406010227','唐玉','计科1402班');";str[45]="insert into list(id,name,class) values ('631406010228','秦皓','计科1402班');";str[46]="insert into list(id,name,class) values ('631406010229','刘妍','计科1402班');";str[47]="insert into list(id,name,class) values ('631406010230','严伟安','计科1402班');";str[48]="insert into list(id,name,class) values ('631406010231','杨煌','计科1402班');";str[49]="insert into list(id,name,class) values ('631426140213','李中耀','计科1402班');";str[50]="insert into list(id,name,class) values ('?631306050204','高杰','计科1403班');";str[51]="insert into list(id,name,class) values ('631406010301','李佳佳','计科1403班');";str[52]="insert into list(id,name,class) values ('631406010303','何友鹏','计科1403班');";str[53]="insert into list(id,name,class) values ('631406010306','郭耕佐','计科1403班');";str[54]="insert into list(id,name,class) values ('631406010307','杨飘','计科1403班');";str[55]="insert into list(id,name,class) values ('631406010308','李中清','计科1403班');";str[56]="insert into list(id,name,class) values ('631406010309','王亢','计科1403班');";str[57]="insert into list(id,name,class) values ('631406010311','郭睿','计科1403班');";str[58]="insert into list(id,name,class) values ('631406010312','江航','计科1403班');";str[59]="insert into list(id,name,class) values ('631406010313','张丰伟','计科1403班');";str[60]="insert into list(id,name,class) values ('631406010314','左琴','计科1403班');";str[61]="insert into list(id,name,class) values ('631406010315','徐红涛','计科1403班');";str[62]="insert into list(id,name,class) values ('631406010316','王梦迪','计科1403班');";str[63]="insert into list(id,name,class) values ('631406010317','陶军华','计科1403班');";str[64]="insert into list(id,name,class) values ('631406010318','黄震国','计科1403班');";str[65]="insert into list(id,name,class) values ('631406010319','张舰心','计科1403班');";str[66]="insert into list(id,name,class) values ('631406010321','杨升','计科1403班');";str[67]="insert into list(id,name,class) values ('631406010322','成黉','计科1403班');";str[68]="insert into list(id,name,class) values ('631406010323','丁莹','计科1403班');";str[69]="insert into list(id,name,class) values ('631406010324','冯明建','计科1403班');";str[70]="insert into list(id,name,class) values ('631406010326','陈雷','计科1403班');";str[71]="insert into list(id,name,class) values ('631406010327','孙作明','计科1403班');";str[72]="insert into list(id,name,class) values ('631406010328','李帆','计科1403班');";str[73]="insert into list(id,name,class) values ('631406010329','樊庆珂','计科1403班');";str[74]="insert into list(id,name,class) values ('631406010330','张建辉','计科1403班');";str[75]="insert into list(id,name,class) values ('631406010331','欧诗卿','计科1403班');";str[76]="insert into list(id,name,class) values ('631424210205','李嘉华','计科1403班');";str[77]="insert into list(id,name,class) values ('631406010401','刘翠芳','计科1404班');";str[78]="insert into list(id,name,class) values ('631406010402','李杰','计科1404班');";str[79]="insert into list(id,name,class) values ('631406010404','杨林','计科1404班');";str[80]="insert into list(id,name,class) values ('631406010405','刘佳','计科1404班');";str[81]="insert into list(id,name,class) values ('631406010408','刘钊宏','计科1404班');";str[82]="insert into list(id,name,class) values ('631406010409','董刚','计科1404班');";str[83]="insert into list(id,name,class) values ('631406010410','伍守增','计科1404班');";str[84]="insert into list(id,name,class) values ('631406010411','裴丹','计科1404班');";str[85]="insert into list(id,name,class) values ('631406010412','梁健','计科1404班');";str[86]="insert into list(id,name,class) values ('631406010413','李奕达','计科1404班');";str[87]="insert into list(id,name,class) values ('631406010416','陈劲','计科1404班');";str[88]="insert into list(id,name,class) values ('631406010417','朱彤','计科1404班');";str[89]="insert into list(id,name,class) values ('631406010418','张亮','计科1404班');";str[90]="insert into list(id,name,class) values ('631406010419','陈兴','计科1404班');";str[91]="insert into list(id,name,class) values ('631406010422','龚毅','计科1404班');";str[92]="insert into list(id,name,class) values ('631406010423','罗艺','计科1404班');";str[93]="insert into list(id,name,class) values ('631406010424','陈朝阳','计科1404班');";str[94]="insert into list(id,name,class) values ('631406010425','张宇','计科1404班');";str[95]="insert into list(id,name,class) values ('631406010426','向健','计科1404班');";str[96]="insert into list(id,name,class) values ('631406010428','廖旺','计科1404班');";str[97]="insert into list(id,name,class) values ('631406010430','陈建川','计科1404班');";


        str1[0]="631406010102";
        str2[0]="莫天金";
        str3[0]="计科1401班";
        str1[1]="631406010103";
        str2[1]="吴国平";
        str3[1]="计科1401班";
        str1[2]="631406010104";
        str2[2]="孙文斌";
        str3[2]="计科1401班";
        str1[3]="631406010105";
        str2[3]="潘俊旭";
        str3[3]="计科1401班";
        str1[4]="631406010106";
        str2[4]="石佳磊";
        str3[4]="计科1401班";
        str1[5]="631406010107";
        str2[5]="赵权";
        str3[5]="计科1401班";
        str1[6]="631406010108";
        str2[6]="马鹏";
        str3[6]="计科1401班";
        str1[7]="631406010109";
        str2[7]="郭文浩";
        str3[7]="计科1401班";
        str1[8]="631406010110";
        str2[8]="李季";
        str3[8]="计科1401班";
        str1[9]="631406010111";
        str2[9]="陈仕豪";
        str3[9]="计科1401班";
        str1[10]="631406010112";
        str2[10]="杜菲";
        str3[10]="计科1401班";
        str1[11]="631406010113";
        str2[11]="李红兵";
        str3[11]="计科1401班";
        str1[12]="631406010114";
        str2[12]="蔡佳辰";
        str3[12]="计科1401班";
        str1[13]="631406010115";
        str2[13]="肖洒益";
        str3[13]="计科1401班";
        str1[14]="631406010117";
        str2[14]="伍凯荣";
        str3[14]="计科1401班";
        str1[15]="631406010118";
        str2[15]="张林";
        str3[15]="计科1401班";
        str1[16]="631406010119";
        str2[16]="王斌";
        str3[16]="计科1401班";
        str1[17]="631406010120";
        str2[17]="廖宇峰";
        str3[17]="计科1401班";
        str1[18]="631406010122";
        str2[18]="谭建";
        str3[18]="计科1401班";
        str1[19]="631406010123";
        str2[19]="左永和";
        str3[19]="计科1401班";
        str1[20]="631406010124";
        str2[20]="王增辉";
        str3[20]="计科1401班";
        str1[21]="631406010128";
        str2[21]="任中豪";
        str3[21]="计科1401班";
        str1[22]="631406010129";
        str2[22]="何泳桦";
        str3[22]="计科1401班";
        str1[23]="631406010130";
        str2[23]="张力";
        str3[23]="计科1401班";
        str1[24]="631406010131";
        str2[24]="任达";
        str3[24]="计科1401班";
        str1[25]="631404090425";
        str2[25]="李自力";
        str3[25]="计科1402班";
        str1[26]="631406010201";
        str2[26]="肖霞";
        str3[26]="计科1402班";
        str1[27]="631406010203";
        str2[27]="郑建峰";
        str3[27]="计科1402班";
        str1[28]="631406010206";
        str2[28]="程飘";
        str3[28]="计科1402班";
        str1[29]="631406010207";
        str2[29]="王浩";
        str3[29]="计科1402班";
        str1[30]="631406010208";
        str2[30]="李建鹏";
        str3[30]="计科1402班";
        str1[31]="631406010209";
        str2[31]="张向守";
        str3[31]="计科1402班";
        str1[32]="631406010210";
        str2[32]="邱凯";
        str3[32]="计科1402班";
        str1[33]="631406010211";
        str2[33]="罗七奇";
        str3[33]="计科1402班";
        str1[34]="631406010214";
        str2[34]="李佩科";
        str3[34]="计科1402班";
        str1[35]="631406010216";
        str2[35]="黄许飞";
        str3[35]="计科1402班";
        str1[36]="631406010217";
        str2[36]="钟富胜";
        str3[36]="计科1402班";
        str1[37]="631406010218";
        str2[37]="陈鸿";
        str3[37]="计科1402班";
        str1[38]="631406010219";
        str2[38]="易家洛";
        str3[38]="计科1402班";
        str1[39]="631406010220";
        str2[39]="邓强";
        str3[39]="计科1402班";
        str1[40]="631406010222";
        str2[40]="原晨";
        str3[40]="计科1402班";
        str1[41]="631406010223";
        str2[41]="袁益";
        str3[41]="计科1402班";
        str1[42]="631406010224";
        str2[42]="石峻臣";
        str3[42]="计科1402班";
        str1[43]="631406010226";
        str2[43]="张洋";
        str3[43]="计科1402班";
        str1[44]="631406010227";
        str2[44]="唐玉";
        str3[44]="计科1402班";
        str1[45]="631406010228";
        str2[45]="秦皓";
        str3[45]="计科1402班";
        str1[46]="631406010229";
        str2[46]="刘妍";
        str3[46]="计科1402班";
        str1[47]="631406010230";
        str2[47]="严伟安";
        str3[47]="计科1402班";
        str1[48]="631406010231";
        str2[48]="杨煌";
        str3[48]="计科1402班";
        str1[49]="631426140213";
        str2[49]="李中耀";
        str3[49]="计科1402班";
        str1[50]="631306050204";
        str2[50]="高杰";
        str3[50]="计科1403班";
        str1[51]="631406010301";
        str2[51]="李佳佳";
        str3[51]="计科1403班";
        str1[52]="631406010303";
        str2[52]="何友鹏";
        str3[52]="计科1403班";
        str1[53]="631406010306";
        str2[53]="郭耕佐";
        str3[53]="计科1403班";
        str1[54]="631406010307";
        str2[54]="杨飘";
        str3[54]="计科1403班";
        str1[55]="631406010308";
        str2[55]="李中清";
        str3[55]="计科1403班";
        str1[56]="631406010309";
        str2[56]="王亢";
        str3[56]="计科1403班";
        str1[57]="631406010311";
        str2[57]="郭睿";
        str3[57]="计科1403班";
        str1[58]="631406010312";
        str2[58]="江航";
        str3[58]="计科1403班";
        str1[59]="631406010313";
        str2[59]="张丰伟";
        str3[59]="计科1403班";
        str1[60]="631406010314";
        str2[60]="左琴";
        str3[60]="计科1403班";
        str1[61]="631406010315";
        str2[61]="徐红涛";
        str3[61]="计科1403班";
        str1[62]="631406010316";
        str2[62]="王梦迪";
        str3[62]="计科1403班";
        str1[63]="631406010317";
        str2[63]="陶军华";
        str3[63]="计科1403班";
        str1[64]="631406010318";
        str2[64]="黄震国";
        str3[64]="计科1403班";
        str1[65]="631406010319";
        str2[65]="张舰心";
        str3[65]="计科1403班";
        str1[66]="631406010321";
        str2[66]="杨升";
        str3[66]="计科1403班";
        str1[67]="631406010322";
        str2[67]="成黉";
        str3[67]="计科1403班";
        str1[68]="631406010323";
        str2[68]="丁莹";
        str3[68]="计科1403班";
        str1[69]="631406010324";
        str2[69]="冯明建";
        str3[69]="计科1403班";
        str1[70]="631406010326";
        str2[70]="陈雷";
        str3[70]="计科1403班";
        str1[71]="631406010327";
        str2[71]="孙作明";
        str3[71]="计科1403班";
        str1[72]="631406010328";
        str2[72]="李帆";
        str3[72]="计科1403班";
        str1[73]="631406010329";
        str2[73]="樊庆珂";
        str3[73]="计科1403班";
        str1[74]="631406010330";
        str2[74]="张建辉";
        str3[74]="计科1403班";
        str1[75]="631406010331";
        str2[75]="欧诗卿";
        str3[75]="计科1403班";
        str1[76]="631424210205";
        str2[76]="李嘉华";
        str3[76]="计科1403班";
        str1[77]="631406010401";
        str2[77]="刘翠芳";
        str3[77]="计科1404班";
        str1[78]="631406010402";
        str2[78]="李杰";
        str3[78]="计科1404班";
        str1[79]="631406010404";
        str2[79]="杨林";
        str3[79]="计科1404班";
        str1[80]="631406010405";
        str2[80]="刘佳";
        str3[80]="计科1404班";
        str1[81]="631406010408";
        str2[81]="刘钊宏";
        str3[81]="计科1404班";
        str1[82]="631406010409";
        str2[82]="董刚";
        str3[82]="计科1404班";
        str1[83]="631406010410";
        str2[83]="伍守增";
        str3[83]="计科1404班";
        str1[84]="631406010411";
        str2[84]="裴丹";
        str3[84]="计科1404班";
        str1[85]="631406010412";
        str2[85]="梁健";
        str3[85]="计科1404班";
        str1[86]="631406010413";
        str2[86]="李奕达";
        str3[86]="计科1404班";
        str1[87]="631406010416";
        str2[87]="陈劲";
        str3[87]="计科1404班";
        str1[88]="631406010417";
        str2[88]="朱彤";
        str3[88]="计科1404班";
        str1[89]="631406010418";
        str2[89]="张亮";
        str3[89]="计科1404班";
        str1[90]="631406010419";
        str2[90]="陈兴";
        str3[90]="计科1404班";
        str1[91]="631406010422";
        str2[91]="龚毅";
        str3[91]="计科1404班";
        str1[92]="631406010423";
        str2[92]="罗艺";
        str3[92]="计科1404班";
        str1[93]="631406010424";
        str2[93]="陈朝阳";
        str3[93]="计科1404班";
        str1[94]="631406010425";
        str2[94]="张宇";
        str3[94]="计科1404班";
        str1[95]="631406010426";
        str2[95]="向健";
        str3[95]="计科1404班";
        str1[96]="631406010428";
        str2[96]="廖旺";
        str3[96]="计科1404班";
        str1[97]="631406010430";
        str2[97]="陈建川";
        str3[97]="计科1404班";


    }
    public void insertStudent(String id,String name,String _class){
        String s="insert into list(id,name,class) values ('"+id+"','"+name+"','"+_class+"');";
        this.getReadableDatabase().execSQL(s);
        updateImgByid(this.getReadableDatabase(),id);
    }



    static private String[] str1=new String[98];
    static private String[] str2=new String[98];
    static private String[] str3=new String[98];
}
