package com.example.krima.chanakynititingujarati.Activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krima.chanakynititingujarati.AscyncTasks.SnackBar;
import com.example.krima.chanakynititingujarati.Mybean.MyBean;
import com.example.krima.chanakynititingujarati.R;
import com.example.krima.chanakynititingujarati.database.SQLiteHelper;
import com.example.krima.chanakynititingujarati.favoriteactivities.FavoriteList;
import com.example.krima.chanakynititingujarati.favoriteactivities.SQliteFavoriteHelper;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.File;
import java.util.ArrayList;

import petrov.kristiyan.colorpicker.ColorPicker;

public class MainContent extends AppCompatActivity {

    ImageButton btnBack, btnShare;
    ImageView previusButton, nextButton, sizeButton, playButton, favoriteButton;
    TextView toolText;
    MyBean myBean;
    FloatingActionButton colorpicker;
    int size;
    int counter = 15;
    Boolean back = null;
    private VerticalMarqueeTextView VMTV;
    int autoscrollflag = 1;
    AdView adView;
    SeekBar seekBarSize, seekBarSpeed;
    LinearLayout layout;
    TextView txtSize, txtSpeed;
    int p = 0;

    boolean click;

    int pos;

    String back1 = null;
    String back2 = null;
    String back3 = null;

    String key1, key2, key3;

    ArrayList<MyBean> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_content);
        getId();
/*
        adView = (AdView) findViewById(R.id.adView);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-7559303174610793~8089319537");
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
*/
        try {
            if (getIntent().getStringExtra("key") != null) {
                key1 = getIntent().getStringExtra("key");
                pos = Integer.parseInt(key1);
                arrayList = (ArrayList<MyBean>) getIntent().getSerializableExtra("item");
                back1 = "back1";

            } else if (getIntent().getStringExtra("Niti") != null) {
                key2 = getIntent().getStringExtra("Niti");
                pos = Integer.parseInt(key2);
                arrayList = (ArrayList<MyBean>) getIntent().getSerializableExtra("item1");
                back2 = "back2";

            } else if (getIntent().getStringExtra("FavNiti") != null) {
                key3 = getIntent().getStringExtra("FavNiti");
                pos = Integer.parseInt(key3);
                myBean = (MyBean) getIntent().getSerializableExtra("favoriteItem");
                back3 = "back3";

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        previusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                previusButton.setImageResource(R.mipmap.selected_previus);
                nextButton.setImageResource(R.mipmap.unselected_next);
                sizeButton.setImageResource(R.mipmap.unselected_textsize);
                //playButton.setImageResource(R.mipmap.unselected_play);
                layout.setVisibility(View.GONE);
                if (!VMTV.isPaused()) {
                    VMTV.pauseMarquee();
                    playButton.setImageResource(R.mipmap.unselected_play);
                }


                pos = pos - 1;

                if (pos >= 0) {
                    Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/shrut.ttf");
                    VMTV.setTypeface(tf);
                    VMTV.setText(arrayList.get(pos).getCdesc());
                    toolText.setText(arrayList.get(pos).getcTitle());
                } else if (pos == 0) {
                    SnackBar.makeLong(MainContent.this, "First Page");
                } else {
                    pos = pos + 1;
                }
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                previusButton.setImageResource(R.mipmap.unselected_previus);
                nextButton.setImageResource(R.mipmap.selected__next);
                sizeButton.setImageResource(R.mipmap.unselected_textsize);
                playButton.setImageResource(R.mipmap.unselected_play);
                layout.setVisibility(View.GONE);

                if (!VMTV.isPaused()) {
                    VMTV.pauseMarquee();
                    playButton.setImageResource(R.mipmap.unselected_play);
                }

                pos = pos + 1;

                if (pos < arrayList.size()) {
                    Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/shrut.ttf");
                    VMTV.setTypeface(tf);
                    VMTV.setText(arrayList.get(pos).getCdesc());
                    toolText.setText(arrayList.get(pos).getcTitle());
                } else if (pos == arrayList.size()) {
                    SnackBar.makeLong(MainContent.this, "Last Page");
                } else {
                    pos = pos - 1;
                }

            }
        });
        sizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previusButton.setImageResource(R.mipmap.unselected_previus);
                nextButton.setImageResource(R.mipmap.unselected_next);
                sizeButton.setImageResource(R.mipmap.selected_textsize);

                if (!click){
                    click=true;
                    layout.setVisibility(View.VISIBLE);
                }
                else if (click){
                    layout.setVisibility(View.GONE);
                    click=false;
                }


            }
        });

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previusButton.setImageResource(R.mipmap.unselected_previus);
                nextButton.setImageResource(R.mipmap.unselected_next);
                sizeButton.setImageResource(R.mipmap.unselected_textsize);
                playButton.setImageResource(R.mipmap.unselected_play);
                VMTV.pauseMarquee();
                layout.setVisibility(View.GONE);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (back1 != null) {
                    startActivity(new Intent(getApplicationContext(), Chanakya_Vishe.class));
                } else if (back2 != null) {
                    startActivity(new Intent(getApplicationContext(), Chankya_niti.class));
                } else if (back3 != null) {
                    startActivity(new Intent(getApplicationContext(), FavoriteList.class));
                }

            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
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
        colorpicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ColorPicker colorPicker = new ColorPicker(MainContent.this);
                colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        VMTV.setTextColor(color);
                    }

                    @Override
                    public void onCancel() {
                        // VMTV.setTextColor(R.color.black);
                    }
                }).setDefaultColorButton(Color.parseColor("#f84c44")).setColumns(4).show();
            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setVisibility(View.GONE);
                previusButton.setImageResource(R.mipmap.unselected_previus);
                nextButton.setImageResource(R.mipmap.unselected_next);
                sizeButton.setImageResource(R.mipmap.unselected_textsize);
                playButton.setImageResource(R.mipmap.selected_play);
                VMTV.setMovementMethod(new ScrollingMovementMethod());
                VMTV.startMarquee();
                if (autoscrollflag == 0) {
                    playButton.setImageResource(R.mipmap.selected_pause);
                    autoscrollflag = 1;
                    if (VMTV.isPaused()) {
                        VMTV.resumeMarquee();
                    }
                } else {
                    autoscrollflag = 0;
                    playButton.setImageResource(R.mipmap.unselected_play);
                    VMTV.pauseMarquee();
                }
            }
        });

        seekBarSize.setProgress(20);
        txtSize = (TextView) findViewById(R.id.txtSize);
        txtSpeed = (TextView) findViewById(R.id.txtSpeed);
        seekBarSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                p = progress;
                txtSize.setText("  Size : " + p);
                VMTV.setTextSize(p);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBarSpeed.setProgress(50);
        seekBarSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int t = 90 - progress;
                txtSpeed.setText("Auto Scroll Speed ");
                VMTV.setDuration(t);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/shrut.ttf");
        VMTV.setTypeface(tf);
        VMTV.setText(arrayList.get(pos).getCdesc());
        toolText.setText(arrayList.get(pos).getcTitle());

    }


    @Override
    protected void onResume() {

        // Start or restart the Marquee if paused.
        if (VMTV.isPaused()) {
            VMTV.resumeMarquee();
        }
        if (adView != null) {
            adView.resume();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {

        // Pause the Marquee when the Activity pauses.
        VMTV.pauseMarquee();
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {

        VMTV.stopMarquee();
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    public void getId() {
        btnBack = (ImageButton) findViewById(R.id.btn_back);
        btnShare = (ImageButton) findViewById(R.id.btn_share);
        toolText = (TextView) findViewById(R.id.toolBarTexView);

        colorpicker = (FloatingActionButton) findViewById(R.id.colorPickerbtn);
        VMTV = (VerticalMarqueeTextView) findViewById(R.id.ContentTextView);

        nextButton = (ImageView) findViewById(R.id.btnNext);
        previusButton = (ImageView) findViewById(R.id.btnPrevius);
        sizeButton = (ImageView) findViewById(R.id.btnTextSize);
        playButton = (ImageView) findViewById(R.id.autoscroll);
        favoriteButton = (ImageView) findViewById(R.id.btnFavorite);

        layout = (LinearLayout) findViewById(R.id.l1);
        seekBarSize = (SeekBar) findViewById(R.id.seekbar1);
        seekBarSpeed = (SeekBar) findViewById(R.id.seekbar2);

    }
}

