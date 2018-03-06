package com.example.krima.chanakynititingujarati.Activities;

import android.content.Intent;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.krima.chanakynititingujarati.Adapters.Chanakya_Vishe_adpt;
import com.example.krima.chanakynititingujarati.Mybean.MyBean;
import com.example.krima.chanakynititingujarati.R;
import com.example.krima.chanakynititingujarati.database.SQLiteHelper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

public class Chanakya_Vishe extends AppCompatActivity {

    ListView listView;
    SQLiteHelper sqLiteHelper;
    Chanakya_Vishe_adpt myAdpt;
    CardView card_view;
    ArrayList<MyBean> arrayList;
    InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chanakya__vishe);
        getIds();
        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        AdRequest adRequest = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });

        sqLiteHelper=new SQLiteHelper(getApplicationContext());
        arrayList=sqLiteHelper.displayData();
        myAdpt=new Chanakya_Vishe_adpt(getApplicationContext(),arrayList);
        listView.setAdapter(myAdpt);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(Chanakya_Vishe.this,MainContent.class);
                i.putExtra("item",arrayList);
                String key= String.valueOf(position);
                i.putExtra("key",key);
                startActivity(i);
            }
        });
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundColor(R.color.Android_Center);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void getIds(){
        listView =(ListView) findViewById(R.id.listViewInVishe);
        card_view=(CardView) findViewById(R.id.card_view);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        overridePendingTransition(R.animator.slide_right_to_left,R.animator.slide_left_to_right);
        startActivity(i);


    }
    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}
