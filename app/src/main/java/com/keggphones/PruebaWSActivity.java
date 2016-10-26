package com.keggphones;

import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.keggphones.Domain.Client;
import com.keggphones.Security.Encryption;
import com.keggphones.WS.BCCRWS;
import com.keggphones.WS.ClientWS;
import com.keggphones.WS.SearchPhonesWS;
import com.keggphones.WS.aa;
import com.keggphones.WS.getAllPhonesWS;
import com.keggphones.WS.getInformationClientWS;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.crypto.EncryptedPrivateKeyInfo;


public class PruebaWSActivity extends AppCompatActivity {

    public static TextView txtWS;
    Calendar date = new GregorianCalendar();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_ws);

        txtWS = (TextView)findViewById(R.id.txtMessageWS);
        ImageView imc = (ImageView)findViewById(R.id.imcPrueba);

        /*try {

            Glide.with(this).load("http://25.45.62.52/CoreVises/Images/Phones/iphone.png").into(imc);
        } catch (Exception e) { e.printStackTrace(); }*/

        String colones = "30000,000";
        String[] aa = colones.split(",");

        int b = (int)((Integer.parseInt(aa[0]))/(Integer.parseInt("547")));
        System.out.println("Conversion " + b);
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);
        String date = day+"/"+month+"/"+year;
        txtWS.setText("Valor "+b+"Fecha " + date);

        String URL = "http://25.45.62.52/CoreVises/Images/Phones/iphone.png";

        new aa(this,URL).execute(imc);





    }



}
