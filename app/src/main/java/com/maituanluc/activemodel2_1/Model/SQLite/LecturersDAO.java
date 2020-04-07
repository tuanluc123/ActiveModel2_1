package com.maituanluc.activemodel2_1.Model.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maituanluc.activemodel2_1.Model.Object.Lecturers;
import com.maituanluc.activemodel2_1.Reponsitory.Observer;
import com.maituanluc.activemodel2_1.Reponsitory.Subject;

import java.util.ArrayList;
import java.util.List;

import static com.maituanluc.activemodel2_1.Model.SQLite.DbHelper.IDLECTRERS;
import static com.maituanluc.activemodel2_1.Model.SQLite.DbHelper.NAMELECTURERS;
import static com.maituanluc.activemodel2_1.Model.SQLite.DbHelper.NAMESTUDENT_LT;
import static com.maituanluc.activemodel2_1.Model.SQLite.DbHelper.PHONE;
import static com.maituanluc.activemodel2_1.Model.SQLite.DbHelper.TBLECTURERS;

public class LecturersDAO implements Subject {
    DbHelper helper;
    SQLiteDatabase database;

    List<Observer> observerList;
    boolean changed;
    final Object MUTEX=new Object();

    public LecturersDAO(Context context) {
        helper=new DbHelper(context);
        this.observerList=new ArrayList<>();
    }

    public void insertLecturers(Lecturers lecturers){
        database=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(NAMESTUDENT_LT,lecturers.getNameStudent_Lt());
        values.put(NAMELECTURERS,lecturers.getNameLecturers());
        values.put(PHONE,lecturers.getPhone());
        database.insert(TBLECTURERS,null,values);
        this.changed=true;
        notifyObserver();
    }

    public ArrayList<Lecturers> getList(){
        ArrayList<Lecturers>ds=new ArrayList<>();
        database=helper.getReadableDatabase();
        Cursor c=database.query(TBLECTURERS,null,null,null,null,null,null);
        if(c.moveToFirst()){
            do{
                int id=c.getInt(0);
                String namest=c.getString(1);
                String name=c.getString(2);
                String phone=c.getString(3);
                Lecturers lecturers=new Lecturers(id,namest,name,phone);
                ds.add(lecturers);
            }while (c.moveToNext());
        }
        return ds;
    }

    public void delete(int id){
        database=helper.getWritableDatabase();
        database.delete(TBLECTURERS,IDLECTRERS+"=?",new String[]{String.valueOf(id)});
        this.changed=true;
        notifyObserver();
    }

    public void deleteToName(String name){
        database=helper.getWritableDatabase();
        database.delete(TBLECTURERS,NAMESTUDENT_LT+"=?",new String[]{String.valueOf(name)});
        this.changed=true;
        notifyObserver();
    }

    @Override
    public void register(Observer observer) {
        if(observer==null) throw new NullPointerException("Observer Empty");
        if(!observerList.contains(observer)) observerList.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        List<Observer> observerLocal;
        synchronized (MUTEX){
            if(!changed)return;
            observerLocal=new ArrayList<>(this.observerList);
            this.changed=false;
        }
        for(Observer observer:observerLocal){
            observer.update();
        }
    }

    @Override
    public Object getUpdate(Observer observer) {
        return this.getList();
    }
}
