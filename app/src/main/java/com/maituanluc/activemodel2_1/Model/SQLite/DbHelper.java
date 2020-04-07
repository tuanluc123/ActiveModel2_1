package com.maituanluc.activemodel2_1.Model.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {
    public static final String TBSTUDENT="TBStudent";
    public static final String IDSTUDENT="idStudent";
    public static final String NAMESTUDENT="nameStudent";
    public static final String POINTSTUDENT="pointStudent";
    public String sqlStudent="create table "+TBSTUDENT+" ("+
            IDSTUDENT+" integer primary key autoincrement, "+
            NAMESTUDENT+" text, "+
            POINTSTUDENT+" text)";

    public static final String TBLECTURERS="TBLect";
    public static final String IDLECTRERS="idLecturers";
    public static final String NAMESTUDENT_LT="nameStudent_Lt";
    public static final String NAMELECTURERS="nameLecturers";
    public static final String PHONE="phone";

    public String sqlLecturers="create table "+TBLECTURERS+" ("+
            IDLECTRERS+" integer primary key autoincrement, "+
            NAMESTUDENT_LT+" text, "+
            NAMELECTURERS+" text, "+
            PHONE+" text)";



    public DbHelper(Context context) {
        super(context, "Lecturees", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlStudent);
        sqLiteDatabase.execSQL(sqlLecturers);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TBSTUDENT);
        sqLiteDatabase.execSQL("drop table if exists "+TBLECTURERS);
        onCreate(sqLiteDatabase);
    }
}
