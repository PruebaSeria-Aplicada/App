package com.keggphones.WS;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.keggphones.LoginActivity;
import com.keggphones.MainActivity;
import com.keggphones.MenuActivity;
import com.keggphones.PruebaWSActivity;
import com.keggphones.Security.Encryption;


public class ClientWS  extends AsyncTask<String, Integer, String> {

    private Context context;

    private static final String SOAP_ACTION = "http://tempuri.org/IClientService/verifyExistsClient"; //dominio más nombre método
    private static final String OPERATION_NAME = "verifyExistsClient"; //Nombre del método
    private static final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
    public static final String SOAP_ADDRESS = "http://25.45.62.52/Services/ClientService.svc";

    Encryption encryption = new Encryption();
    private String key;

    public ClientWS (Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        String result = null;

        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
                OPERATION_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER10);

        // Se indica que el web service es .net
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);

        // Se envian los parametros al web service
        request.addProperty("client", params[0]);
        request.addProperty("key", params[1]);
        key = params[1];

        try {

            // Se hace el llamado al web service
            httpTransport.call(SOAP_ACTION, envelope);

            // Respuesta del web service
            SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope
                    .getResponse();

            result = resultsRequestSOAP.toString();
            result = encryption.decrypting(result,key);


            httpTransport.getServiceConnection().disconnect();
        } catch (IOException | XmlPullParserException e) {
            Log.v("Error", e.getMessage());
            result = e.getMessage();

        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        // Se muestra la respuesta del web service
        if(result.equalsIgnoreCase("1")==true){
            Intent intentMenu = new Intent(context, MenuActivity.class);
            context.startActivity(intentMenu);
        }else{
            Intent registry = new Intent(context,MainActivity.class);
            context.startActivity(registry);
        }

    }


}
