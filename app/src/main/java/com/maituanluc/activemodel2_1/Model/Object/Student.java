package com.maituanluc.activemodel2_1.Model.Object;

public class Student {
    public int idStudent;
    public String nameStudent;
    public String pointStudent;

    public Student(int idStudent, String nameStudent, String pointStudent) {
        this.idStudent = idStudent;
        this.nameStudent = nameStudent;
        this.pointStudent = pointStudent;
    }

    public Student(String nameStudent, String pointStudent) {
        this.nameStudent = nameStudent;
        this.pointStudent = pointStudent;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public String getPointStudent() {
        return pointStudent;
    }
}
