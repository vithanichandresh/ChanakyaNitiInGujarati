package com.example.krima.chanakynititingujarati.favoriteactivities;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krima.chanakynititingujarati.Mybean.MyBean;
import com.example.krima.chanakynititingujarati.R;

import java.util.ArrayList;


/**
 * Created by KRIMA on 04-08-2017.
 */

public class FavoriteAdpt extends BaseAdapter {

    Context context;
    ArrayList<MyBean> arrayList;


    String TAG = getClass().getSimpleName();

    public FavoriteAdpt(Context context, ArrayList<MyBean> arrayList) {
        this.context = context;
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
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.chanakya_vishe, null);

        TextView txtTital = (TextView) convertView.findViewById(R.id.txtChanakyaVishe);


        MyBean myBean = arrayList.get(position);

        Log.i(TAG, "mybean " + myBean.toString());

        txtTital.setText(myBean.getcTitle());
        // Load image lazily


        return convertView;
}

}
