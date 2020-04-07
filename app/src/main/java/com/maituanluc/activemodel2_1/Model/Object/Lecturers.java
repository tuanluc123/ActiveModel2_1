package com.maituanluc.activemodel2_1.Model.Object;

public class Lecturers {
    public int idLecturers;
    public String nameStudent_Lt;
    public String nameLecturers;
    public String phone;

    public Lecturers(int idLecturers, String nameStudent_Lt, String nameLecturers, String phone) {
        this.idLecturers = idLecturers;
        this.nameStudent_Lt = nameStudent_Lt;
        this.nameLecturers = nameLecturers;
        this.phone = phone;
    }

    public Lecturers(String nameStudent_Lt, String nameLecturers, String phone) {
        this.nameStudent_Lt = nameStudent_Lt;
        this.nameLecturers = nameLecturers;
        this.phone = phone;
    }

    public int getIdLecturers() {
        return idLecturers;
    }

    public String getNameStudent_Lt() {
        return nameStudent_Lt;
    }

    public String getNameLecturers() {
        return nameLecturers;
    }

    public String getPhone() {
        return phone;
    }
}
