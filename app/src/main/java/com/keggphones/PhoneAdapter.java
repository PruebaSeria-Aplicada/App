package com.keggphones;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import com.keggphones.Domain.Phone;
import com.keggphones.WS.aa;
import com.squareup.picasso.Picasso;

/**
 * Created by mm on 12/10/2016.
 */
public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.MyViewHolder> {


    private Context mContext;
    private ArrayList<Phone> phoneList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txvModel, txvPrice, txvPriceDolar;
        public ImageView imcPhone;
        public Button btnSale;

        public MyViewHolder(View view) {
            super(view);
            txvModel = (TextView) view.findViewById(R.id.txvModel);
            txvPrice = (TextView) view.findViewById(R.id.txvPrice);
            txvPriceDolar = (TextView) view.findViewById(R.id.txvPriceDolar);
            imcPhone = (ImageView) view.findViewById(R.id.imc_phone);
            btnSale = (Button) view.findViewById(R.id.btn_sale);
        }
    }


    public PhoneAdapter(Context mContext, ArrayList<Phone> phoneList) {
        this.mContext = mContext;
        this.phoneList = phoneList;
    }



    @Override
    public PhoneAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.phones_card, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final PhoneAdapter.MyViewHolder holder, int position) {
        final Phone phone = phoneList.get(position);
        holder.txvModel.setText(phone.getBrand());
        holder.txvPrice.setText("₡ " + phone.getPrice());
        holder.txvPriceDolar.setText("$ " +phone.getPriceDolar());
        //System.out.println("URL imagen " + phone.getImage() );



        try {
            Glide.with(mContext).load(phone.getImage()).into(holder.imcPhone);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //Picasso.with(mContext).load("25.45.62.52/CoreVises/Images/Phones/iphone.png").into(holder.imcPhone);


        holder.imcPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String flash;
                if(phone.getFlash().equals("1"))
                    flash = "Si";
                else
                    flash = "No";

                Intent detailPhone = new Intent(mContext,DetailPhoneActivity.class);
                detailPhone.putExtra("Brand",phone.getBrand());
                detailPhone.putExtra("Model",phone.getModel().toString());
                detailPhone.putExtra("InternalMemory",phone.getInternalMemory());
                detailPhone.putExtra("ExternalMemory",phone.getExternalMemory());
                detailPhone.putExtra("Pixels",phone.getPixels());
                detailPhone.putExtra("Flash",flash);
                detailPhone.putExtra("Resolution",phone.getResolution());
                detailPhone.putExtra("Price",phone.getPrice());
                detailPhone.putExtra("Image",phone.getImage());
                detailPhone.putExtra("id",phone.getIdPhone());
                mContext.startActivity(detailPhone);

            }

        });


    }

    @Override
    public int getItemCount() {
        return phoneList.size();
    }





}
