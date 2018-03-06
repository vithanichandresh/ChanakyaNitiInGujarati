package com.example.krima.chanakynititingujarati.favoriteactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krima.chanakynititingujarati.Activities.MainContent;
import com.example.krima.chanakynititingujarati.Mybean.MyBean;
import com.example.krima.chanakynititingujarati.R;

import java.util.ArrayList;

public class FavoriteList extends AppCompatActivity {

    ListView listView;
    FavoriteAdpt myAdpt;
    ArrayList<MyBean> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        
        setContentView(R.layout.activity_favorite_list);
        listView=(ListView) findViewById(R.id.FavoriteList);


       final SQliteFavoriteHelper helper=new SQliteFavoriteHelper(FavoriteList.this);

        arrayList=helper.displayData();

        Log.d("array", "onCreate: "+arrayList.size()+" "+arrayList.toString());

        myAdpt=new FavoriteAdpt(FavoriteList.this,arrayList);

        listView.setAdapter(myAdpt);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(FavoriteList.this,MainContent.class);
                i.putExtra("favoriteItem",arrayList);
                String key= String.valueOf(position);
                i.putExtra("FavNiti",key);
                startActivity(i);
            }
        });
    }

}
