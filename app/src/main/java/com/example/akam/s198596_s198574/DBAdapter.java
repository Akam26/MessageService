package com.example.akam.s198596_s198574;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.Date;

public class DBAdapter {
    Context context;
    static final String TAG = "DbHelper";
    static final String DB_NAVN = "BRdatabase.db";
    static final String TABELL = "kontakter";
    static final String ID = BaseColumns._ID;
    static final String FORNAVN = "name";
    static final String TELEFON = "phone";
    static final String BURSDAG = "birthday";
    static final String BURSMANED = "birthmonth";
    static final String BURSAR = "birthyear";
    static final int DB_VERSJON = 1;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }


    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {

            super(context,DB_NAVN, null, DB_VERSJON);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            String sql = "create table " + TABELL + " ("
                    + ID + " integer primary key autoincrement, "
                    + FORNAVN + " text, "
                    + TELEFON + " text, "
                    + BURSDAG + " int, "
                    + BURSMANED + " int, "
                    + BURSAR + " int);";
            Log.d(TAG, "oncreated sql" + sql);
            db.execSQL(sql);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
            db.execSQL("drop table if exists " + TABELL);
            Log.d(TAG,"updated");
            onCreate(db);
        }
    } //slutt DatabaseHelper
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
    }
    public void insert(ContentValues cv)
    {
        db.insert(TABELL,null,cv);
    }
    public Cursor finnAlle()
    {
        Cursor cur;
        String[]cols={FORNAVN,TELEFON, ID};
        cur=db.query(TABELL,cols,null,null,null,null,ID);
        return cur;

    }

    public boolean oppdater(String fornavn, String telefon, int bursdag, int bursmaned, int bursAr, int id) {
        ContentValues cv = new ContentValues(5);

        cv.put(FORNAVN, fornavn);
        cv.put(TELEFON, telefon);
        cv.put(BURSDAG, bursdag);
        cv.put(BURSMANED, bursmaned);
        cv.put(BURSAR, bursAr);
        //return db.update(TABELL, cv, FORNAVN + " equals '" + fornavn + "'", null) >0;
        return db.update(TABELL,cv,ID + "='" + id + "'",null) > 0;
    }

    public boolean slett(int id)
    {
        return db.delete(TABELL, ID + "='" + id + "'", null) > 0;
    }

    public Cursor finnPersonMId(int id)
    {

        String[] cols = {ID,FORNAVN,TELEFON,BURSAR,BURSMANED,BURSDAG};
        //String sql = "SELECT * FROM " + TABELL + " WHERE " + ID + " = " + id;
        Log.d("ID", "!!!!!!!!!-------" +id);
        //Cursor cur = db.rawQuery(sql, null);
        Cursor cur = db.query(TABELL,cols,ID + " == " + id,null,null,null,ID);
        cur.moveToFirst();
        Log.d("DATA", "!!!!!!!!!-------" + id);
        return cur;
    }

    public Cursor bursdagIDag(int dag , int man){

        Cursor cur;
        String[]cols={ID,FORNAVN,TELEFON,BURSAR,BURSMANED,BURSDAG};
        cur=db.query(TABELL,cols,BURSDAG + " = '" + dag + "' and " + BURSMANED + " = '" + man + "'",null,null,null,ID);
        return cur;


    }

}


