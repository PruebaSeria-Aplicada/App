package com.keggphones.WS;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.widget.Toast;

import com.keggphones.Domain.Phone;
import com.keggphones.MenuActivity;
import com.keggphones.PruebaWSActivity;
import com.keggphones.Security.Encryption;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.SystemDefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by mm on 24/10/2016.
 */
public class SearchPhonesWS extends AsyncTask<String, Integer, String> {


    private Context context;
    private String key;
    public static String flag = "-1";
    public static ArrayList<Phone> phoneList;
    Encryption encryption = new Encryption();


    public SearchPhonesWS (Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        String result = null;

        try {

            HttpClient httpClient = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet(
                    "http://25.45.62.52/Services/PhonesLikeService.svc/getPhonesLike?word=" + params[0] + "&key=" + params[1]);
            getRequest.addHeader("accept", "application/json");

            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            key = params[1];

            result = br.readLine();
            httpClient.getConnectionManager().shutdown();

        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }


        return result;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progreso = values[0].intValue();

    }

    @Override
    protected void onPostExecute(String result) {

        String values = null;
        JSONObject jObject  = null; // json
        try {
            jObject = new JSONObject(result);
            values = jObject.getString("getPhonesLikeResult");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        phoneList = new ArrayList<>();
        try {
            String information = encryption.decrypting(values, key);

                String[] phones = information.split("#");
                for (int i = 0; i < phones.length; i++) {
                    String[] paraPhones = phones[i].split(";");
                    String[] price = paraPhones[10].split(",");
                    Phone phone = new Phone(paraPhones[0], paraPhones[1], paraPhones[2], paraPhones[3],
                            paraPhones[4], paraPhones[5], paraPhones[6], paraPhones[7], paraPhones[8], paraPhones[9],
                            paraPhones[10], paraPhones[11], paraPhones[12]);

                    int quanDolar = ((Integer.parseInt(price[0])) / (Integer.parseInt(BCCRWS.valueDolar)));
                    phone.setPriceDolar("" + quanDolar);

                    phoneList.add(phone);

                }
                flag = "1";
                Intent main = new Intent(context, MenuActivity.class);
                context.startActivity(main);

        } catch (Exception e) {
            Toast.makeText(context, "No existen resultados", Toast.LENGTH_LONG).show();
        }

    }

}
