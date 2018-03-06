package com.example.krima.chanakynititingujarati.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.krima.chanakynititingujarati.Mybean.MyBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static android.R.attr.version;

/**
 * Created by KRIMA on 12-08-2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String TABLENAME1 = "chanakya";
    private static final String TABLENAME2 = "Favorite_Niti";
    public static String Name = "chanakya_title";
    private static String DB_PATH = "/databases/";
    public static String DataBaseName = "chanakyanitiguj.sqlite";
    public static final String TABLENAME = "detail";
    Context mContext;

    public SQLiteHelper(Context context) {
        super(context, DataBaseName, null, 1);
        try {
            this.mContext = context;
            CopyDataBaseFromAsset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void CopyDataBaseFromAsset() throws IOException {
        InputStream myInput = mContext.getAssets().open(DataBaseName);
// Path to the just created empty db
        String outFileName = getDatabasePath();
        // if the path doesn't exist first, create it

        File f = new File(mContext.getApplicationInfo().dataDir + DB_PATH);
        if (!f.exists())
            f.mkdir();
// Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
// transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

// Close the streams

        myOutput.flush();

        myOutput.close();

        myInput.close();
    }

    public String getDatabasePath() {
        return mContext.getApplicationInfo().dataDir + DB_PATH
                + DataBaseName;
    }

    public ArrayList<MyBean> displayData() {
        ArrayList<MyBean> arrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from " + TABLENAME;
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            MyBean myBean = new MyBean();
            myBean.setcId(cursor.getString(0));
            myBean.setcTitle(cursor.getString(1));
            myBean.setCdesc(cursor.getString(2));
            arrayList.add(myBean);
        }
        db.close();
        return arrayList;
    }


    public ArrayList<MyBean> displayData1() {
        ArrayList<MyBean> arrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from " + TABLENAME1;
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            MyBean myBean = new MyBean();
            myBean.setcId(cursor.getString(0));
            myBean.setcTitle(cursor.getString(1));
            myBean.setCdesc(cursor.getString(2));
            arrayList.add(myBean);
        }
        db.close();
        return arrayList;
    }

}
