package com.maituanluc.activemodel2_1.Controller;

import android.content.Context;
import android.widget.ListView;

import com.maituanluc.activemodel2_1.Adapter.AdapterLecturers;
import com.maituanluc.activemodel2_1.Model.Object.Lecturers;
import com.maituanluc.activemodel2_1.Reponsitory.Observer;
import com.maituanluc.activemodel2_1.Reponsitory.Subject;

import java.util.ArrayList;

public class LecturersController implements Observer {
    private Subject subject;
    Context context;
    ListView lv;

    public LecturersController(Context context, ListView lv) {
        this.context = context;
        this.lv = lv;
    }

    @Override
    public void update() {
        ArrayList<Lecturers> ds=(ArrayList<Lecturers>)subject.getUpdate(this);
        AdapterLecturers adapterLecturers=new AdapterLecturers(context,ds);
        lv.setAdapter(adapterLecturers);
    }

    @Override
    public void setSubject(Subject subject) {
        this.subject=subject;
    }
}
