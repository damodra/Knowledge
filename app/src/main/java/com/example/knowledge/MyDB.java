package com.example.knowledge;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDB extends SQLiteOpenHelper {

    Context c;
    public  static String DBNAME="DAMODAR";
    public static int VERSION=1;


    public MyDB(@Nullable Context context) {
        super(context,DBNAME, null, VERSION);
        c=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            String qry="create table SibaTab(Name TEXT,LName TEXT,MobNo TEXT,Pass TEXT,RePass TEXT,Email TEXT PRIMARY KEY)";
            db.execSQL(qry);
            Toast.makeText(c, "Table Create Successfully...", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Log.e("MYDB","table creation error",e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public boolean insertData(String name,String lname,String mobile,String password,String repassword,String email)
    {

        try{
            String qry="insert into SibaTab values('"+name+"','"+lname+"','"+mobile+"','"+password+"','"+repassword+"','"+email+"')";
            SQLiteDatabase db=getWritableDatabase();
            db.execSQL(qry);
            Toast.makeText(c, name+"Registered Successfully...", Toast.LENGTH_SHORT).show();
            return true;
        }
        catch (Exception e)
        {
            Log.e("MYDB","Record Insertion Failds",e);
            return false;
        }
    }

    public Cursor empLoginCheck(String email)
    {
        try
        {
            String qry="Select Name, Pass from SibaTab where Email='"+email+"'";
            SQLiteDatabase db=getWritableDatabase();
            Cursor c=db.rawQuery(qry,null);
            return  c;
        }
        catch (Exception e)
        {
            Log.e("MYDB","Login Error",e);
            return null;
        }
    }
}
