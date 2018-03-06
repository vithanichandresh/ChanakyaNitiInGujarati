package com.example.krima.chanakynititingujarati.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.krima.chanakynititingujarati.Adapters.Chanakya_Vishe_adpt;
import com.example.krima.chanakynititingujarati.Mybean.MyBean;
import com.example.krima.chanakynititingujarati.R;
import com.example.krima.chanakynititingujarati.database.SQLiteHelper;
import com.example.krima.chanakynititingujarati.favoriteactivities.FavoriteList;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

public class Chankya_niti extends AppCompatActivity {

    ListView listView;
    SQLiteHelper sqLiteHelper;
    Chanakya_Vishe_adpt myAdpt;
    MyBean myBean;
    ArrayList<MyBean> arrayList;
    InterstitialAd mInterstitialAd;
    CardView card_view;
    ImageView favoriteActivity;
    int pos;
    int length;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chankya_niti);

        card_view = (CardView) findViewById(R.id.card_view);

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


        listView = (ListView) findViewById(R.id.listView1);


        favoriteActivity = (ImageView) findViewById(R.id.FavoriteAct);
        favoriteActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Chankya_niti.this, FavoriteList.class);
                startActivity(i);
            }
        });

        sqLiteHelper = new SQLiteHelper(getApplicationContext());
        arrayList = sqLiteHelper.displayData1();

        myAdpt = new Chanakya_Vishe_adpt(getApplicationContext(), arrayList);
        listView.setAdapter(myAdpt);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), MainContent.class);

                pos = position;
                length = arrayList.size();
                i.putExtra("item1", arrayList);
                String niti= String.valueOf(position);
                i.putExtra("Niti", niti);
                i.putExtra("arraylistPos", position);
                Log.i("TAG", "onItemClick: get pos-->" + i.getIntExtra("arraylistPos",0) + "-->" + arrayList.get(position));
                startActivity(i);
            }
        });

        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // card_view.setCardBackgroundColor(R.color.Android_Center);
                card_view.setCardBackgroundColor(R.color.color3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*startActivity(new Intent(getApplicationContext(),MainActivity.class));

        finish();*/
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}
