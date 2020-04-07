package com.maituanluc.activemodel2_1.Controller;

import java.util.ArrayList;

public interface Controller<T> {
    void Add(T t);
    ArrayList<T> getList();
    void delete(T t);
    void updateView();
}
