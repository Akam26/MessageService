package com.example.akam.s198596_s198574;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

/**
 * Created by Akam on 19.10.15.
 */
public class personAdapter extends BaseAdapter {

    private String[][] personListe;
    private LayoutInflater li;

    public personAdapter(Context c) {

        DBAdapter db = new DBAdapter(c);
        db.open();
        Cursor cur = db.finnAlle();

        personListe = new String[2][cur.getCount()];
        int i = 0;

        if (cur.moveToFirst()) {
            do {

                personListe[0][i] = cur.getString(0);
                personListe[1][i++] = cur.getString(1);

            } while (cur.moveToNext());

        }
        cur.close();
    }

    @Override
    public int getCount() {
        return personListe[0].length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

}