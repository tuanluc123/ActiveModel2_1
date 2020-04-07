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

public class StudentAdapter extends BaseAdapter {
    Context context;
    ArrayList<Student>ds;

    public StudentAdapter(Context context, ArrayList<Student> ds) {
        this.context = context;
        this.ds = ds;
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
        view=inf.inflate(R.layout.item_view,null);
        TextView tvName=view.findViewById(R.id.tvNameSt);
        TextView tvPoint=view.findViewById(R.id.tvPoint);
        tvName.setText(ds.get(i).getNameStudent());
        tvPoint.setText(ds.get(i).getPointStudent());
        return view;
    }
}
