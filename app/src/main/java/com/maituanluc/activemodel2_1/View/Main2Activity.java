package com.maituanluc.activemodel2_1.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.maituanluc.activemodel2_1.Adapter.AdapterSpinner;
import com.maituanluc.activemodel2_1.Controller.LecturersController;
import com.maituanluc.activemodel2_1.Model.Object.Lecturers;
import com.maituanluc.activemodel2_1.Model.SQLite.LecturersDAO;
import com.maituanluc.activemodel2_1.Model.SQLite.StudentDAO;
import com.maituanluc.activemodel2_1.R;
import com.maituanluc.activemodel2_1.Reponsitory.Observer;

public class Main2Activity extends AppCompatActivity {
    static Spinner sp;
    static EditText etNameLt,etPhone;
    Button btAddLt;
    static ListView lvLt;

    LecturersDAO lecturersDAO;
    StudentDAO studentDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        unility();
        lecturersDAO=new LecturersDAO(Main2Activity.this);
        Observer obj=new LecturersController(Main2Activity.this,lvLt);
        lecturersDAO.register(obj);
        obj.setSubject(lecturersDAO);
        obj.update();


        studentDAO=new StudentDAO(Main2Activity.this);
        AdapterSpinner adapterSpinner=new AdapterSpinner(studentDAO.getStudent(),Main2Activity.this);
        sp.setAdapter(adapterSpinner);

        btAddLt.setOnClickListener(v->{
            insert();
        });

        lvLt.setOnItemClickListener((adapterView, view, i, l) -> {
            lecturersDAO.delete(lecturersDAO.getList().get(i).getIdLecturers());
        });
    }

    private void insert() {
        String name=etNameLt.getText().toString();
        String phone=etPhone.getText().toString();
        String nameSt=studentDAO.getStudent().get(sp.getSelectedItemPosition()).getNameStudent();
        Lecturers lecturers=new Lecturers(nameSt,name,phone);
        lecturersDAO.insertLecturers(lecturers);
    }

    private void unility() {
        sp      =findViewById(R.id.sp);
        etNameLt=findViewById(R.id.etNameLt);
        etPhone =findViewById(R.id.etPhone);
        btAddLt =findViewById(R.id.btAddLt);
        lvLt    =findViewById(R.id.lvLt);
    }
}
