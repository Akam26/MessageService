package com.example.akam.s198596_s198574;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button button;
    DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBAdapter(this);
        db.open();

        ListView tl = (ListView) findViewById(R.id.personlistviwe);
        Cursor cur = db.finnAlle();

        final ArrayList<String> list = new ArrayList<String>();
        final ArrayList<Integer> listId = new ArrayList<>();

        if (cur.moveToFirst()) {
            do {
                list.add(cur.getString(0));
                listId.add(cur.getInt(cur.getColumnIndex(db.ID)));
            } while (cur.moveToNext());
        }
        cur.close();

        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list, listId);
        tl.setAdapter(adapter);
        final Context c = this;

        tl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                      @Override
                                      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                          Intent intent = new Intent(c, Endre.class);
                                          intent.putExtra("TryThis", adapter.getActualId(position));
                                          startActivity(intent);
                                      }
                                  }
        );


        //tl.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {});

    }

    @Override
    protected void onResume() {
        super.onResume();

        db = new DBAdapter(this);
        db.open();

        ListView tl = (ListView) findViewById(R.id.personlistviwe);
        Cursor cur = db.finnAlle();

        final ArrayList<String> list = new ArrayList<String>();
        final ArrayList<Integer> listId = new ArrayList<>();

        if (cur.moveToFirst()) {
            do {
                list.add(cur.getString(0));
                listId.add(cur.getInt(cur.getColumnIndex(db.ID)));
            } while (cur.moveToNext());
        }
        cur.close();

        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list, listId);
        tl.setAdapter(adapter);
        final Context c = this;

        tl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                      @Override
                                      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                          Intent intent = new Intent(c, Endre.class);
                                          intent.putExtra("TryThis", adapter.getActualId(position));
                                          startActivity(intent);
                                      }
                                  }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        //return true;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final Context context = this;
        switch (item.getItemId()) {
            case R.id.reg:
                Intent intent = new Intent(context, Register.class);
                startActivity(intent);
                break;

            case R.id.sms:
                Intent i = new Intent(context, MsgPeriodiskService.class);
                context.startService(i);
                Intent intent1 = new Intent(context, Melding.class);
                startActivity(intent1);
                break;

                // Intent intent1 = new Intent(context, visalle.class);
                //startActivity(intent1);
        }
        return super.onOptionsItemSelected(item);
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
        List<Integer> trialId;

        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects, List<Integer> objectId) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); i++)
                mIdMap.put(objects.get(i), i);
            trialId = objectId;
        }

        public long getItemId(int position){
            String item = getItem(position);
            return mIdMap.get(item);
        }

        public int getActualId(int position)
        {
            return trialId.get(position);
        }

        public boolean hasStableId(){
            return true;
        }
    }

}







