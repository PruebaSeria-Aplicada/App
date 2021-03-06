package com.keggphones.WS;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.keggphones.Domain.Phone;
import com.keggphones.LoginActivity;
import com.keggphones.MainActivity;
import com.keggphones.MenuActivity;
import com.keggphones.PruebaWSActivity;
import com.keggphones.Security.Encryption;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import static com.keggphones.WS.getAllPhonesWS.phonesList;


public class getAllPhonesWS  extends AsyncTask<String, Integer, String> {

    private Context context;

    public static ArrayList<Phone> phonesList;

    private static final String SOAP_ACTION = "http://tempuri.org/IPhoneService/getPhones"; //dominio más nombre método
    private static final String OPERATION_NAME = "getPhones"; //Nombre del método
    private static final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";
    public static final String SOAP_ADDRESS = "http://25.45.62.52/Services/PhoneService.svc";

    private String key;
    Encryption encryption  = new Encryption();

    public getAllPhonesWS (Context context) {
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
        request.addProperty("key", params[0]);
        key = params[0];

        try {

            // Se hace el llamado al web service
            httpTransport.call(SOAP_ACTION, envelope);

            // Respuesta del web service
            SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope
                    .getResponse();

            result = resultsRequestSOAP.toString();


            httpTransport.getServiceConnection().disconnect();
        } catch (IOException | XmlPullParserException e) {
            Log.v("Error", e.getMessage());
            result = e.getMessage();

        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        phonesList = new ArrayList<>();
        try{
            String information = encryption.decrypting(result,key);
            String[] phones = information.split("#");

            for(int i = 0; i < phones.length; i++){
                String[] paraPhones = phones[i].split(";");
                String[] price = paraPhones[10].split(",");
                Phone phone =  new Phone(paraPhones[0],paraPhones[1],paraPhones[2],paraPhones[3],
                paraPhones[4],paraPhones[5],paraPhones[6],paraPhones[7],paraPhones[8],paraPhones[9],
                        paraPhones[10],paraPhones[11],paraPhones[12]);

                int quanDolar = ((Integer.parseInt(price[0]))/(Integer.parseInt(BCCRWS.valueDolar)));
                phone.setPriceDolar(""+quanDolar);
                phonesList.add(phone);

            }
        }catch (Exception e){}

    }


}
