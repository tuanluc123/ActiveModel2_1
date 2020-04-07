package com.maituanluc.activemodel2_1.Reponsitory;

public interface Subject {
    void register(Observer observer);
    void remove(Observer observer);
    void notifyObserver();
    Object getUpdate(Observer observer);
}
