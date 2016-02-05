package com.example.akam.s198596_s198574;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.regex.Pattern;

public class Endre extends AppCompatActivity {

    DBAdapter db;
    final Context context=this;

    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endre);

        final String regExFornavn = "^[a-zæøåA-ZÆØÅ]{1,}$";
        final Pattern p = Pattern.compile(regExFornavn);
        final String regExFortlf = "^[0-9]{8}$";
        final Pattern t = Pattern.compile(regExFortlf);

        db = new DBAdapter(this);
        db.open();

        Intent intent = getIntent();

        Bundle pakke = intent.getExtras();
        id = pakke.getInt("TryThis");
        //id++;

        EditText nvn = (EditText)findViewById(R.id.eNavn);
        EditText tlf = (EditText)findViewById(R.id.ePhone);
        DatePicker dte = (DatePicker) findViewById(R.id.eBirth);
        Cursor cr = db.finnPersonMId(id);
        Log.d("!!!!!!!", "---------------" + id);
        //Log.d("!!!!!", "" + cr.getCount());
        cr.moveToFirst();

        nvn.setText(cr.getString(cr.getColumnIndex(db.FORNAVN)));
        tlf.setText(cr.getString(cr.getColumnIndex(db.TELEFON)));
        dte.updateDate(cr.getInt(cr.getColumnIndex(db.BURSAR)), cr.getInt(cr.getColumnIndex(db.BURSMANED)), cr.getInt(cr.getColumnIndex(db.BURSDAG)));



        Button opd=(Button)findViewById(R.id.oppdater);
        final EditText fn =(EditText)findViewById(R.id.eNavn);
        final EditText tel =(EditText)findViewById(R.id.ePhone);
        final DatePicker bdag =(DatePicker)findViewById(R.id.eBirth);
        final DatePicker bman =(DatePicker)findViewById(R.id.eBirth);
        final DatePicker bar =(DatePicker)findViewById(R.id.eBirth);

        opd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String fnv      =fn.getText().toString();
                String telefone =tel.getText().toString();
                int bd          =bdag.getDayOfMonth();
                int bm          =bman.getMonth();
                int ba          =bar.getYear();

                if(p.matcher(fnv).matches() && t.matcher(telefone).matches()) {
                    db.oppdater(fnv, telefone, bd, bm, ba, id);

                    finish();
                }
                else{
                    new AlertDialog.Builder(Endre.this).setTitle("Error").setMessage("Feil i enten Navn eller Telefonnummer\ningen mellomrom").show();

                }

            }
        }) ;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_endre, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final Context context = this;
        switch (item.getItemId()) {

            case R.id.slette:
                db.slett(id);

                finish();
                break;
            /////////7**********&&&&&&&
            // Intent intent1 = new Intent(context, visalle.class);
            //startActivity(intent1);
        }
        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}



