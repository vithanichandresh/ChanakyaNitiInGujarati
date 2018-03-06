package com.example.krima.chanakynititingujarati.Activities;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krima.chanakynititingujarati.AscyncTasks.JSONHelper;
import com.example.krima.chanakynititingujarati.AscyncTasks.OnAsyncLoader;
import com.example.krima.chanakynititingujarati.AscyncTasks.SnackBar;
import com.example.krima.chanakynititingujarati.Mybean.MyBean;
import com.example.krima.chanakynititingujarati.Mybean.MyBean2;
import com.example.krima.chanakynititingujarati.NotificationUtilities.NotificationUtils;
import com.example.krima.chanakynititingujarati.R;
import com.example.krima.chanakynititingujarati.app.Config;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.startapp.android.publish.adsCommon.AutoInterstitialPreferences;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    TextView appName;
    LinearLayout MainLayout;
    LinearLayout chankyaNiti, chankyaVishe, ShareApp, OverApp, contactUs, RateUs;
    BroadcastReceiver  mRegistrationBroadcastReceiver;
    String TAG=getClass().getSimpleName();
    MyBean2 mybean;
    MyBean myBean;
    JSONHelper  helper;
    ImageView ad,closed;
    public static String regId;

    int randomNumber;
    Random random;

    int OnTouchi=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/*
        StartAppSDK.init(this, "207943865", true);
        StartAppAd.enableAutoInterstitial();
        StartAppAd.setAutoInterstitialPreferences(
                new AutoInterstitialPreferences()
                        .setSecondsBetweenAds(60)
        );*/

        setContentView(R.layout.activity_main);
        random= new Random();
        randomNumber=random.nextInt(10);
        Log.e("otp  ", "onCreate: "+randomNumber );

        TelephonyManager tManager = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);

        String uid = tManager.getDeviceId();

        Log.e(TAG, "onCreate: uid = "+uid);

        myBean=new MyBean();
        myBean.setIMEI(uid);
        Log.e("IMEI", "IMEI = "+myBean.getIMEI());

        getIds();



        MainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(OnTouchi<10){
                    OnTouchi=OnTouchi+1;
                }
                else {
                   OnTouchi=0;
               }

               if (randomNumber == OnTouchi){
                   helper=new JSONHelper(MainActivity.this, "http://varniapp.com/apps/index.php/api/get_image", null, new OnAsyncLoader() {
                       @Override
                       public void onResult(String result) throws JSONException {

                       }
                   });
                   helper.execute();
                   final Dialog dialog = new Dialog(MainActivity.this, android.R.style.DeviceDefault_Light_ButtonBar);
                   dialog.setContentView(R.layout.dialog);
                   ImageView backPreConfirmation = (ImageView) dialog.findViewById(R.id.close);
                   dialog.show();
                   backPreConfirmation.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           dialog.dismiss();
                       }
                   });
               }

            }
        });

        mRegistrationBroadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {

                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
                        String regId = pref.getString("regId", null);

                        Log.e(TAG, "Firebase reg id: " + regId);

                        if (!TextUtils.isEmpty(regId)) {
                            displayFirebaseRegId();
                            Toast.makeText(context, "regId" + regId, Toast.LENGTH_SHORT).show();
                        }
                                /*txtRegId.setText("Firebase Reg Id: " + regId);*/
                        else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {

                            String message = intent.getStringExtra("message");


                            Toast.makeText(context, "Push Notification " + message, Toast.LENGTH_SHORT).show();
                        }
                                /*txtRegId.setText("Firebase Reg Id is not received yet!");*/
                    }
                }

        };
        displayFirebaseRegId();



        chankyaVishe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Chanakya_Vishe.class));
                overridePendingTransition(R.animator.slide_left_to_right,R.animator.slide_right_to_left);
            }
        });
        chankyaNiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Chankya_niti.class));
            }
        });
        ShareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationInfo app = getApplicationContext().getApplicationInfo();
                String filePath = app.sourceDir;
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("application/*");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
                startActivity(Intent.createChooser(intent, "Share app via"));
            }
        });
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ContactUs.class));
            }
        });

      /*  AcyncTask task=new AcyncTask();
        task.execute();*/

    }
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
         regId = pref.getString("regId", null);


        Log.e(TAG, "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId))

            Log.i(TAG, "displayFirebaseRegId: "+regId);
        else
            Log.i(TAG, "displayFirebaseRegId: not recived yet");
    }



    public void getIds() {

        chankyaNiti = (LinearLayout) findViewById(R.id.Img_Chanakya_Niti);
        chankyaVishe = (LinearLayout) findViewById(R.id.Img_Chanakya_Vishe);
        ShareApp = (LinearLayout) findViewById(R.id.Img_SharApp);
        OverApp = (LinearLayout) findViewById(R.id.Img_over_App);
        contactUs = (LinearLayout) findViewById(R.id.Img_Contact_us);
        RateUs=(LinearLayout) findViewById(R.id.Img_Give_Rate);
        appName = (TextView) findViewById(R.id.appName);
        MainLayout=(LinearLayout) findViewById(R.id.layout_main);
        ad=(ImageView) findViewById(R.id.VarniAd);

    }
    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                StartAppAd.showAd(getApplicationContext());
            }
        }).show();

    }
    class AcyncTask extends AsyncTask {
        ArrayList<MyBean2> arrayList = new ArrayList<>();
        public AcyncTask(){

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            allContact();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
          /*  Gson gson = new Gson();
            String json = gson.toJson(arrayList);
            Log.e("GetDataFromClass", "ArrayList :- " + arrayList.toString());
            HashMap<String, String> hashMap = new HashMap<String, String>();

            hashMap.put("json_data", json);

            Log.e("GetDataFromClass", "HashMap :- " + json);

            helper = new JSONHelper(MainActivity.this, "http://varniapp.com/apps/index.php/api/add_contacts", hashMap, new OnAsyncLoader() {
                @Override
                public void onResult(String result) throws JSONException {

                    Log.e(TAG, " Responce == " + result.toString());
                    SnackBar.makeLong(MainActivity.this,"respone :- "+result);
                }
            });
            helper.execute();
*/

          /*  HashMap<String, String> hashMap1 = new HashMap<String, String>();
            hashMap1.put("device_type","1");
            hashMap1.put("device_token",regId);
            hashMap1.put("device_id",myBean.getIMEI());
            Log.e(TAG, "onPostExecute: "+hashMap1 );
            helper=new JSONHelper(MainActivity.this, "http://varniapp.com/apps/index.php/api/save_device_token", hashMap1, new OnAsyncLoader() {
                @Override
                public void onResult(String result) throws JSONException {
                    Log.e(TAG, " Responce 2 == " + result.toString());
                    SnackBar.makeLong(MainActivity.this,"respone 2 :- "+result);

                }
            });
            helper.execute();
            super.onPostExecute(o);*/
        }
        public void allContact() {

            ContentResolver cr = getContentResolver();
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null, null);
            if (cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    String id = cur.getString(
                            cur.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cur.getString(cur.getColumnIndex(
                            ContactsContract.Contacts.DISPLAY_NAME));

                    if (cur.getInt(cur.getColumnIndex(
                            ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                        Cursor pCur = cr.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{id}, null);
                        while (pCur.moveToNext()) {
                            String phoneNo = pCur.getString(pCur.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER));


                            mybean = new MyBean2();

                            mybean.setName(name);
                            mybean.setNumber(phoneNo);
                            mybean.setCity("");
                            mybean.setCountry("");
                            mybean.setEmail("");
                            mybean.setState("");
                            arrayList.add(mybean);

                            Log.d(TAG, "array list size : "+arrayList.size());
                            Log.d(TAG, "array list tostring: "+arrayList.toString());
                            for (int i = 0; i < arrayList.size(); i++) {
                                Log.d(TAG, "array list get Value->" + arrayList.get(i)+" i = "+i);
                            }


                        }
                        pCur.close();

                    }
                }
            }
        }

    }

}
