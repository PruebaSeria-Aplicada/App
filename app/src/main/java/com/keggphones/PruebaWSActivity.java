package com.keggphones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.keggphones.WS.ClientWS;
import com.keggphones.WS.getAllBrandWS;
import com.keggphones.WS.getAllPhonesWS;
import com.keggphones.WS.getPhonesWS;

import java.util.concurrent.ExecutionException;

public class PruebaWSActivity extends AppCompatActivity {

    public static TextView txtWS;
    public static String information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_ws);

        txtWS = (TextView)findViewById(R.id.txtMessageWS);


        new getAllPhonesWS(this).execute("");






    }
}
