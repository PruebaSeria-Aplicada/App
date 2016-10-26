package com.keggphones.WS;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mm on 24/10/2016.
 */
public class aa extends AsyncTask<ImageView, Void, Bitmap> {

    private Context context;
    private String URL;
    ImageView image;
    Bitmap bitmap;

    public aa (Context context,String URL) {
        this.context = context;
        this.URL = URL;
    }

    @Override
    protected Bitmap doInBackground(ImageView... params) {

        image = params[0];
        try {
            URL imageURL = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection)imageURL.openConnection();
            conn.connect();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return bitmap;
    }
    protected void onPostExecute(Bitmap result) {
        image.setImageBitmap(result);
        super.onPostExecute(result);

    }
}
