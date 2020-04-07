package com.maituanluc.activemodel2_1.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.maituanluc.activemodel2_1.Controller.StudentController;
import com.maituanluc.activemodel2_1.Model.Object.Student;
import com.maituanluc.activemodel2_1.Model.SQLite.LecturersDAO;
import com.maituanluc.activemodel2_1.Model.SQLite.StudentDAO;
import com.maituanluc.activemodel2_1.R;
import com.maituanluc.activemodel2_1.Reponsitory.Observer;

public class MainActivity extends AppCompatActivity {
    static EditText etNameSt,etPoint;
    Button btAddSt,btNext;
    ListView lvStudent;
    StudentDAO studentDAO;
    LecturersDAO lecturersDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unilityMain();

        studentDAO=new StudentDAO(MainActivity.this);
        Observer obj=new StudentController(MainActivity.this,lvStudent);
        studentDAO.register(obj);
        obj.setSubject(studentDAO);
        obj.update();

        btAddSt.setOnClickListener(view -> insert());


        lecturersDAO=new LecturersDAO(MainActivity.this);
        lvStudent.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(MainActivity.this, ""+i, Toast.LENGTH_SHORT).show();
            lecturersDAO.deleteToName(studentDAO.getStudent().get(i).getNameStudent());
            studentDAO.delete(studentDAO.getStudent().get(i).getIdStudent());
        });
        btNext.setOnClickListener(view->{
            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
        });
    }

    private void unilityMain() {
        btNext=findViewById(R.id.btNext);
        btAddSt=findViewById(R.id.btAddStudent);
        lvStudent=findViewById(R.id.lvStudent);
    }

    private void insert(){
        final AlertDialog.Builder abStudent= new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater=getLayoutInflater();
        View view1=inflater.inflate(R.layout.dialog_student,null);
        abStudent.setView(view1);
        unilityDialog(view1);
        abStudent.setPositiveButton("Yes", (dialogInterface, i) -> {
            String name=etNameSt.getText().toString();
            String point=etPoint.getText().toString();
            Student student=new Student(name,point);
            studentDAO.insertStudent(student);
        });
        abStudent.setNegativeButton("Cancel", (dialog, which) -> abStudent.setCancelable(true));
        abStudent.show();
    }
    private void unilityDialog(View view1){
        etNameSt=view1.findViewById(R.id.etNameSt);
        etPoint=view1.findViewById(R.id.etPoint);
    }
}
