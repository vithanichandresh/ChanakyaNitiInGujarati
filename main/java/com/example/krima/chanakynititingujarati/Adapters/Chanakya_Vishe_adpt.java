package com.example.krima.chanakynititingujarati.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.krima.chanakynititingujarati.Mybean.MyBean;
import com.example.krima.chanakynititingujarati.R;

import java.util.ArrayList;

/**
 * Created by KRIMA on 12-08-2017.
 */

public class Chanakya_Vishe_adpt extends BaseAdapter {

    Context myContext;
    ArrayList<MyBean> arrayList;

    public Chanakya_Vishe_adpt(Context context, ArrayList<MyBean> arrayList) {
        this.myContext = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.chanakya_vishe, null);

        MyBean myBean = arrayList.get(position);

        TextView txtChanakyaVishe = (TextView) view.findViewById(R.id.txtChanakyaVishe);
        txtChanakyaVishe.setText(myBean.getcTitle());
        return view;
    }
}
