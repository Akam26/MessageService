package com.example.akam.s198596_s198574;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;

import java.io.IOException;

public class Melding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_melding);

        Button lag=(Button)findViewById(R.id.save);
        final Switch avPa = (Switch)findViewById(R.id.onOff);

        final EditText et = (EditText)findViewById(R.id.smsMelding);
        String thisTxt = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("melding","Gratulerer");

        final TimePicker tm = (TimePicker)findViewById(R.id.timePicker);
        tm.setIs24HourView(true);
        int thisTime = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("time", 1);
        tm.setCurrentHour(thisTime);
        int thisMinutt = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("minutt", 1);
        tm.setCurrentMinute(thisMinutt);
        //tm.setMinute(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("minutt", 1));
        //tm.setHour(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("time", 1));

        et.setText(thisTxt);
        avPa.setChecked(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean("avpa",false));

        lag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txt = et.getText().toString();
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("melding", txt).commit();

                tm.clearFocus();
                int timen = tm.getCurrentHour();
                if(timen == 0)
                    timen = 24;
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putInt("time", timen).commit();
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putInt("minutt", tm.getCurrentMinute()).commit();

                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putBoolean("avpa", avPa.isChecked()).commit();

                finish();
            }
        });


        avPa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Intent intent = new Intent(Melding.this, MsgPeriodiskService.class);
                    startService(intent);
                }
                else{
                    Intent intent = new Intent(Melding.this, MsgPeriodiskService.class);
                    stopService(intent);
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_melding, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
