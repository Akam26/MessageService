package com.example.akam.s198596_s198574;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    DBAdapter db;
    final Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DBAdapter(this);
        db.open();

        Button settinn=(Button)findViewById(R.id.settinn);

        final EditText navn=(EditText)findViewById(R.id.name);
        final EditText telefon=(EditText)findViewById(R.id.phone);
        final DatePicker fodsel=(DatePicker)findViewById(R.id.birth);
        final String regExFornavn = "^[a-zæøåA-ZÆØÅ]{1,}$";
        final Pattern p = Pattern.compile(regExFornavn);
        final String regExFortlf = "^[0-9]{8}$";
        final Pattern t = Pattern.compile(regExFortlf);




            settinn.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    ////*****

                    String fnavn = navn.getText().toString();
                    String tlf = telefon.getText().toString();


                    if(p.matcher(fnavn).matches() && t.matcher(tlf).matches()) {
                        ContentValues cv = new ContentValues(5);
                        cv.put(db.FORNAVN, fnavn);
                        cv.put(db.TELEFON, tlf);
                        cv.put(db.BURSDAG, fodsel.getDayOfMonth());
                        cv.put(db.BURSMANED, fodsel.getMonth());
                        cv.put(db.BURSAR, fodsel.getYear());
                        Log.d("Database", "verdien satt i databasen");

                        db.insert(cv);
                        finish();
                    }
                    else
                    {
                        new AlertDialog.Builder(Register.this).setTitle("Error").setMessage("Feil i enten Navn eller Telefonnummer\ningen mellomrom").show();
                    }
                /*Intent intent1 = new Intent(context, MainActivity.class);
                startActivity(intent1);*/


                }
                //}
            });
        }

    @Override
    public void onResume(){
        super.onResume();
        db.open();
    }
    @Override
    public void onPause(){
        super.onPause();
        db.close();
    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_register, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            //int id = item.getItemId();
            final Context context=this;
            switch(item.getItemId()){

                    // Intent intent1 = new Intent(context, visalle.class);
                    //startActivity(intent1);
            }

            //noinspection SimplifiableIfStatement
            /*if (id == R.id.action_settings) {
                return true;
            }*/

            return super.onOptionsItemSelected(item);
        }

    }

