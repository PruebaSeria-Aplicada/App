package com.keggphones.WS;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.keggphones.Domain.Phone;
import com.keggphones.PruebaWSActivity;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mm on 18/10/2016.
 */
public class getPhonesWS extends AsyncTask<String, Integer, String> {

    private Context context;

    private static final String SOAP_ACTION = "http://tempuri.org/IPhoneService/getPhones"; //dominio más nombre método
    private static final String OPERATION_NAME = "getPhones"; //Nombre del método
    private static final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
    public static final String SOAP_ADDRESS = "http://25.45.62.52/Services/ClientService.svc";



    public getPhonesWS(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        ArrayList<Phone> result = null;
        String error;

        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
                OPERATION_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER10);

        // Se indica que el web service es .net
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);

        // Se envian los parametros al web service
        //request.addProperty("nameUser", params[0]);

        try {

            // Se hace el llamado al web service
            httpTransport.call(SOAP_ACTION, envelope);

            // Respuesta del web service
            //SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();

            SoapObject resultsRequestSOAP = (SoapObject)envelope.bodyIn;
            int count = resultsRequestSOAP.getPropertyCount();

            for(int i = 0; i < count; i++) {
                if(resultsRequestSOAP.getProperty(i) != null)
                    result.add((Phone) resultsRequestSOAP.getProperty(i));
            }

            httpTransport.getServiceConnection().disconnect();
        } catch (IOException | XmlPullParserException e) {
            Log.v("Error", e.getMessage());
            error = e.getMessage();

        }

        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        // Se muestra la respuesta del web service
        PruebaWSActivity.information = result;
        //Toast.makeText(context,result, Toast.LENGTH_LONG).show();
    }

}
