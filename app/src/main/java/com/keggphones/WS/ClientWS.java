package com.keggphones.WS;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.keggphones.PruebaWSActivity;

public class ClientWS extends AsyncTask<String, Integer, String> {

    private Context context;

    //private static final String SOAP_ACTION = "http://tempuri.org/IClientService/verifyExistsClient"; //dominio más nombre método
    //private static final String OPERATION_NAME = "verifyExistsClient"; //Nombre del método
    //private static final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/IClientService/";
    //public static final String SOAP_ADDRESS = "http://25.45.62.52/Services/ClientService.svc?wsdl";

    private static final String SOAP_ACTION = "http://ws.sdde.bccr.fi.cr/ObtenerIndicadoresEconomicosXML"; //dominio más nombre método
    private static final String OPERATION_NAME = "ObtenerIndicadoresEconomicosXML"; //Nombre del método
    private static final String WSDL_TARGET_NAMESPACE = "http://ws.sdde.bccr.fi.cr";
    public static final String SOAP_ADDRESS = "http://indicadoreseconomicos.bccr.fi.cr/indicadoreseconomicos/WebServices/wsIndicadoresEconomicos.asmx";


    public ClientWS(Context context) {
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
        request.addProperty("tcIndicador", params[0]);
        request.addProperty("tcFechaInicio", params[1]);
        request.addProperty("tcFechaFinal", params[2]);
        request.addProperty("tcNombre", params[3]);
        request.addProperty("tnSubNiveles", params[4]);
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
        // Se muestra la respuesta del web service
        PruebaWSActivity.txtWS.setText(result);
        //Toast.makeText(context,result, Toast.LENGTH_LONG).show();
    }

}