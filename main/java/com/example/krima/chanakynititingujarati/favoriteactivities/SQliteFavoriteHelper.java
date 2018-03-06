package com.example.krima.chanakynititingujarati.favoriteactivities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.krima.chanakynititingujarati.Mybean.MyBean;

import java.util.ArrayList;

/*

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

*/

/**
 * Created by KRIMA on 31-07-2017.
 */

public class SQliteFavoriteHelper extends SQLiteOpenHelper {

    private static final String DB_PATH_SUFFIX = "/databases/";
    Context context;
    MyBean myBean;
    public static String DataBaseName1 = "chanakyanitiguj.sqlite";
    public static final String TABLENAME1 = "Favorite_Niti";
    public static String Name = "chanakya_title";
    public static final String Method = "chanakya_desc";
    public static final String Id = "chanakya_id";

    private static final String PAssEncrypt = "Vithani123";

    private String TAG = getClass().getSimpleName();

    SQLiteDatabase database;

    public SQliteFavoriteHelper(Context context) {
        super(context, DataBaseName1, null, 1);
        this.context = context;
    }


    public void onCreate(SQLiteDatabase db) {

          }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void InsertData(MyBean myBean) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Name, myBean.getcTitle());// benifit
        cv.put(Method, myBean.getCdesc()); // image
        sqLiteDatabase.insert(TABLENAME1, null, cv);
        sqLiteDatabase.close();
    }

    public ArrayList<MyBean> displayData() {
        ArrayList<MyBean> arrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from " + TABLENAME1;
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {

            MyBean myBean = new MyBean();
            myBean.setcTitle(cursor.getString(1));
            myBean.setCdesc(cursor.getString(2));
            arrayList.add(myBean);
        }
        db.close();
        return arrayList;
    }


    public void Delet(MyBean myBean,String title) {
        //Open the database
        SQLiteDatabase database = getWritableDatabase();
        //Execute sql query to remove from database
        //NOTE: When removing by String in SQL, value must be enclosed with ''
        String sql="DELETE FROM "+TABLENAME1+" WHERE "+Name+" = '"+ title+"'";
        database.execSQL(sql);
        //Close the database
        database.close();
    }

    public boolean isExist(MyBean myBean) {
        Boolean IsFavorite = false;

        SQLiteDatabase database = getWritableDatabase();
        String sql = "SELECT * FROM "+TABLENAME1+" WHERE chanakya_title = '" + myBean.getcTitle() + "' ";
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String databaseVariable=cursor.getString(1);
            String myBeanVariable=myBean.getcTitle();
            if (databaseVariable.equals(myBeanVariable)) {
                IsFavorite = true;
            } else {
                IsFavorite = false;
            }
        }
        cursor.close();
        database.close();
        return IsFavorite;

    }

}
