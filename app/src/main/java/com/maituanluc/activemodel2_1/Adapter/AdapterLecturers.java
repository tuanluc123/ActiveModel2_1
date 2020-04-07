package com.maituanluc.activemodel2_1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.maituanluc.activemodel2_1.Model.Object.Lecturers;
import com.maituanluc.activemodel2_1.R;

import java.util.ArrayList;

public class AdapterLecturers extends BaseAdapter {
    Context context;
    ArrayList<Lecturers> ds;

    public AdapterLecturers(Context context, ArrayList<Lecturers> ds) {
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
        view=((Activity)context).getLayoutInflater().inflate(R.layout.item_lecturers,null);
        TextView tvNameSt=view.findViewById(R.id.tvNameSt);
        TextView tvNameLt=view.findViewById(R.id.tvNameLt);
        TextView tvPhone=view.findViewById(R.id.tvPhone);
        Lecturers lecturers=ds.get(i);
        tvNameSt.setText(lecturers.getNameStudent_Lt());
        tvNameLt.setText(lecturers.getNameLecturers());
        tvPhone.setText(lecturers.getPhone());
        return view;
    }
}
