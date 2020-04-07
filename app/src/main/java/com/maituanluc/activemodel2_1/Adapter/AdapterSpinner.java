package com.maituanluc.activemodel2_1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.maituanluc.activemodel2_1.Model.Object.Student;
import com.maituanluc.activemodel2_1.R;

import java.util.ArrayList;

public class AdapterSpinner extends BaseAdapter {
    ArrayList<Student> ds;
    Context context;

    public AdapterSpinner(ArrayList<Student> ds, Context context) {
        this.ds = ds;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ds.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inf=((Activity)context).getLayoutInflater();
        view=inf.inflate(R.layout.item_spinner,null);
        TextView tvName=view.findViewById(R.id.tvNameSp);
        Student student=ds.get(i);
        tvName.setText(student.getNameStudent());
        return view;
    }
}
