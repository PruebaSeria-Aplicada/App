package com.keggphones.WS;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.keggphones.LoginActivity;
import com.keggphones.PruebaWSActivity;
import com.keggphones.Security.Encryption;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by mm on 23/10/2016.
 */

public class BCCRWS  extends AsyncTask<String, Integer, String> {

    private Context context;

    private static final String SOAP_ACTION = "http://ws.sdde.bccr.fi.cr/ObtenerIndicadoresEconomicosXML"; //dominio más nombre método
    private static final String OPERATION_NAME = "ObtenerIndicadoresEconomicosXML"; //Nombre del método
    private static final String WSDL_TARGET_NAMESPACE = "http://ws.sdde.bccr.fi.cr";
    public static final String SOAP_ADDRESS = "http://indicadoreseconomicos.bccr.fi.cr/indicadoreseconomicos/WebServices/wsIndicadoresEconomicos.asmx";
    public static String valueDolar;
    Calendar date = new GregorianCalendar();

    public BCCRWS (Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        String result = null;
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH)+1;
        int day = date.get(Calendar.DAY_OF_MONTH);
        String date = day+"/"+month+"/"+year;



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
        request.addProperty("tcFechaInicio",date);
        request.addProperty("tcFechaFinal",date);
        request.addProperty("tcNombre","Gerardo");
        request.addProperty("tnSubNiveles","no");

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
        try {

            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(result)));

            NodeList economicIndicators = doc.getElementsByTagName("INGC011_CAT_INDICADORECONOMIC");
            Node indicator = economicIndicators.item(0);

            if (indicator.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) indicator;
                NodeList changeDollar = elemento.getElementsByTagName("NUM_VALOR").item(0).getChildNodes();
                Node valor = (Node) changeDollar.item(0);
                valueDolar = valor.getNodeValue().substring(0,3);
                new getAllPhonesWS(context).execute("yarr");
                //PruebaWSActivity.txtWS.setText("valor dolar "+ valueDolar);
            }
        } catch (ParserConfigurationException | SAXException | IOException | DOMException ex) {
        }

    }





}
