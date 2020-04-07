package com.maituanluc.activemodel2_1.Model.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.maituanluc.activemodel2_1.Model.Object.Student;
import com.maituanluc.activemodel2_1.Reponsitory.Observer;
import com.maituanluc.activemodel2_1.Reponsitory.Subject;

import java.util.ArrayList;
import java.util.List;

import static com.maituanluc.activemodel2_1.Model.SQLite.DbHelper.IDSTUDENT;
import static com.maituanluc.activemodel2_1.Model.SQLite.DbHelper.NAMESTUDENT;
import static com.maituanluc.activemodel2_1.Model.SQLite.DbHelper.POINTSTUDENT;
import static com.maituanluc.activemodel2_1.Model.SQLite.DbHelper.TBSTUDENT;

public class StudentDAO implements Subject {
    DbHelper helper;
    SQLiteDatabase database;

    List<Observer> observers;
    boolean changed;
    final Object MUTEX=new Object();

    public StudentDAO(Context context) {
        helper=new DbHelper(context);
        this.observers=new ArrayList<>();
    }

    public void insertStudent(Student student){
        database=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(NAMESTUDENT,student.getNameStudent());
        values.put(POINTSTUDENT,student.getPointStudent());
        database.insert(TBSTUDENT,null,values);
        this.changed=true;
        notifyObserver();
    }
    public ArrayList<Student> getStudent(){
        database=helper.getReadableDatabase();
        ArrayList<Student> ds=new ArrayList<>();
        Cursor c=database.query(TBSTUDENT,null,null,null,null,null,null);
        if(c.moveToFirst()){
            do{
                int id=c.getInt(0);
                String name=c.getString(1);
                String point=c.getString(2);
                Student student=new Student(id,name,point);
                ds.add(student);
            }while (c.moveToNext());
        }
        return ds;
    }

    public void delete(int id){
        database=helper.getWritableDatabase();
        database.delete(TBSTUDENT,IDSTUDENT+"=?",new String[]{String.valueOf(id)});
        this.changed=true;
        notifyObserver();
    }

    @Override
    public void register(Observer observer) {
        if(observer == null) throw new NullPointerException("Null Observer");
        if(!observers.contains(observer)) observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        List<Observer> observersLocal;
        //synchronization is used to make sure any observer registered after message is received is not notified
        synchronized (MUTEX) {
            if (!changed)
                return;
            observersLocal = new ArrayList<>(this.observers);
            this.changed=false;
        }
        for (Observer obj : observersLocal) {
            obj.update();
        }
    }

    @Override
    public Object getUpdate(Observer observer) {
        return this.getStudent();
    }

}
