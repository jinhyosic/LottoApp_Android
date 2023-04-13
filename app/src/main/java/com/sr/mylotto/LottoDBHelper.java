package com.sr.mylotto;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LottoDBHelper extends SQLiteOpenHelper {

    static int lottoRound = 0;
    static final String DB_NAME = "testlotto5.db";


    public LottoDBHelper(Context context, int version){
        super(context, DB_NAME, null, version);
    }

    //테이블생성
    @Override
    public void onCreate(SQLiteDatabase db) {
//        db = getReadableDatabase();
//        Cursor cursor = db.rawQuery(("SELECT COUNT(*) FROM testlotto5"),null);
//        cursor.moveToFirst();
//        lottoRound = cursor.getInt(0);
//        db.execSQL("CREATE TABLE testlotto5(" +
//                "`idx` INTEGER PRIMARY KEY AUTOINCREMENT," +
//                " `n1` text ," +
//                " `n2` text ," +
//                " `n3` text ," +
//                " `n4` text ," +
//                " `n5` text ," +
//                " `n6` text ," +
//                " `nb` text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS testlotto5");
        onCreate(db);
    }
    //INSERT문 메서드
    public void insert( int n1,int n2,int n3,int n4,int n5,int n6,int nb){
        SQLiteDatabase db = getWritableDatabase();
        showRound();
        ++lottoRound;
        db.execSQL("INSERT INTO testlotto5(idx, n1,n2,n3,n4,n5,n6,nb) VALUES('" + lottoRound + "','" + n1 + "','" + n2 + "','" + n3 + "','" + n4 + "', '" + n5 + "'," + n6 + ",'" + nb + "')");
    }

    public void delete(int idx){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM testlotto5 WHERE (idx = '" + idx + "');");
        --lottoRound;
    }

    public void update(int idx, int n1,int n2,int n3,int n4,int n5,int n6,int nb){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE testlotto5 SET n1 ='"+ n1 +"', n2 ='"+ n2 +"', n3 ='"+ n3 +"',n4 ='"+ n4 +"',n5 ='"+ n5 +"',n6 ='"+ n6 +"', nb ='"+nb+"' WHERE (idx = '"+idx+"')");
    }
    //로또 회차를 보여주는 메서드./lottoRound를 갱신 시키기도함.
    public void showRound(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(("SELECT COUNT(*) FROM testlotto5"),null);
        cursor.moveToFirst();
        lottoRound = cursor.getInt(0);
    }

}
