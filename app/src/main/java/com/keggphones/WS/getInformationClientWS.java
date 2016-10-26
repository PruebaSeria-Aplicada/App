package com.keggphones.WS;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.keggphones.DetailPhoneActivity;
import com.keggphones.Domain.Client;
import com.keggphones.EditProfileActivity;
import com.keggphones.MainActivity;
import com.keggphones.PruebaWSActivity;
import com.keggphones.SearchPhoneActivity;
import com.keggphones.Security.Encryption;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by mm on 18/10/2016.
 */
public class getInformationClientWS extends AsyncTask<String, Integer, String> {

    private Context context;

    private static final String SOAP_ACTION = "http://tempuri.org/IClientService/getClient"; //dominio más nombre método
    private static final String OPERATION_NAME = "getClient"; //Nombre del método
    private static final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
    public static final String SOAP_ADDRESS = "http://25.45.62.52/Services/ClientService.svc";

    private String key;
    Encryption encryption = new Encryption();

    public getInformationClientWS (Context context) {
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
        request.addProperty("nameUser", params[0]);
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
        try{
            String[] resultClient = result.split(";");
            Client client = new Client(resultClient[0],resultClient[1],resultClient[2],resultClient[3],
                    resultClient[4],resultClient[5],resultClient[6],resultClient[7],resultClient[8],
                    resultClient[9]);
            client.setNameUser(key);
            EditProfileActivity.client = client;
            DetailPhoneActivity.client = client;
        }catch (Exception e){}


    }


}
