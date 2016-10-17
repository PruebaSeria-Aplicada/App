package com.keggphones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.keggphones.WS.ClientWS;

public class PruebaWSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_ws);



        new ClientWS(this).execute("michael","msdhsg1212");

    }
}
