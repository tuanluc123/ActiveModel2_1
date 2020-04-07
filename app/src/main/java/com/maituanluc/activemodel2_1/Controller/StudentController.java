package com.maituanluc.activemodel2_1.Controller;

import android.content.Context;
import android.widget.ListView;

import com.maituanluc.activemodel2_1.Adapter.StudentAdapter;
import com.maituanluc.activemodel2_1.Model.Object.Student;
import com.maituanluc.activemodel2_1.Reponsitory.Observer;
import com.maituanluc.activemodel2_1.Reponsitory.Subject;

import java.util.ArrayList;

public class StudentController implements Observer {
    private Subject subject;
    Context context;
    ListView lv;

    public StudentController(Context context, ListView lv) {
        this.context = context;
        this.lv = lv;
    }

    @Override
    public void update() {
        ArrayList<Student> list= (ArrayList<Student>) subject.getUpdate(this);
        StudentAdapter studentAdapter=new StudentAdapter(context,list);
        lv.setAdapter(studentAdapter);
    }

    @Override
    public void setSubject(Subject subject) {
        this.subject=subject;
    }


}
