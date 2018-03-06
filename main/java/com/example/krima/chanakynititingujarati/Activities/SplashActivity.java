package com.example.krima.chanakynititingujarati.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Window;

import com.example.krima.chanakynititingujarati.Mybean.MyBean;
import com.example.krima.chanakynititingujarati.R;

public class SplashActivity extends AppCompatActivity {

    MyBean myBean;
    final static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);




        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
               /* Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish();*/

        },SPLASH_TIME_OUT);
    }
}
