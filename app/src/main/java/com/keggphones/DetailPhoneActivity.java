package com.keggphones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.keggphones.Domain.Client;
import com.keggphones.Security.Encryption;

import com.keggphones.WS.SalesPhoneWS;


public class DetailPhoneActivity extends AppCompatActivity {


    private String idPhone;
    public static Client client;
    private String price;
    Encryption encryption = new Encryption();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_phone);

        Bundle extras = getIntent().getExtras();
        String brand = extras.getString("Brand");
        String model = extras.getString("Model");
        String internalMemory = extras.getString("InternalMemory");
        String externalMemory = extras.getString("ExternalMemory");
        String pixels = extras.getString("Pixels");
        String flash = extras.getString("Flash");
        String resolution = extras.getString("Resolution");
        price = extras.getString("Price");
        String image = extras.getString("Image");
        idPhone = extras.getString("id");


        TextView txvBrand = (TextView)findViewById(R.id.txv_brand);
        TextView txvModel = (TextView)findViewById(R.id.txv_model);
        TextView txvIntMemory = (TextView)findViewById(R.id.txv_internal_memory);
        TextView txvExtMemory = (TextView)findViewById(R.id.txv_external_memory);
        TextView txvPixels = (TextView)findViewById(R.id.txv_pixels);
        TextView txvFlash = (TextView)findViewById(R.id.txv_flash);
        TextView txvResolution = (TextView)findViewById(R.id.txv_resolution);
        TextView txvPrice = (TextView)findViewById(R.id.txv_price);



        txvBrand.setText(txvBrand.getText() +" "+ brand );
        txvModel.setText(txvModel.getText() +" "+ model);
        txvIntMemory.setText(txvIntMemory.getText() +" "+ internalMemory);
        txvExtMemory.setText(txvExtMemory.getText() + " "+ externalMemory);
        txvPixels.setText(txvPixels.getText() + " " + pixels);
        txvFlash.setText(txvFlash.getText() + " "+ flash);
        txvResolution.setText(txvResolution.getText() +" "+ resolution);
        txvPrice.setText(txvPrice.getText()+" "+ price);


        try {
            Glide.with(this).load(image).into((ImageView) findViewById(R.id.icm_phone));
        } catch (Exception e) {
            e.printStackTrace();
        }



        Button btnSale = (Button)findViewById(R.id.btn_sale);
        btnSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerSale();


            }
        });





    }

    public void registerSale(){

        String idClient = encryption.encrypt(client.getIdUser(),client.getNameUser());
        String phone = idPhone+";1";
        phone = encryption.encrypt(phone,client.getNameUser());
        String total = encryption.encrypt(price,client.getNameUser());
        System.out.println(price);
        new SalesPhoneWS(this).execute(idClient,phone,total,client.getNameUser());

    }

}