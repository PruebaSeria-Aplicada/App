package com.keggphones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.keggphones.WS.ClientWS;

public class PruebaWSActivity extends AppCompatActivity {

    public static  TextView txtWS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_ws);

        txtWS = (TextView)findViewById(R.id.txtMessageWS);

        //new ClientWS(this).execute("317","16/10/16","18/10/16","Gerardo","no");
        new ClientWS(this).execute("yarr","456");

    }
}
