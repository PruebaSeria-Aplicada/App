package com.keggphones;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.keggphones.Security.Encryption;
import com.keggphones.WS.InsertClientWS;
import com.keggphones.WS.getAllPhonesWS;
import com.keggphones.WS.getInformationClientWS;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    private TextInputLayout tilName;
    private TextInputLayout tilUserName;
    private TextInputLayout tilEmail;
    private TextInputLayout tilPassword;
    private TextInputLayout tilCard;
    private TextInputLayout tilsvcCard;
    private TextInputLayout tilAddress;
    private EditText txtName;
    private EditText txtUserName;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtCard;
    private EditText txtSvcCard;
    private EditText txtAddress;


    Encryption encryption = new Encryption();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tilName = (TextInputLayout)findViewById(R.id.til_name);
        tilUserName = (TextInputLayout)findViewById(R.id.til_user);
        tilEmail = (TextInputLayout)findViewById(R.id.til_email);
        tilPassword = (TextInputLayout)findViewById(R.id.til_password);
        tilCard = (TextInputLayout)findViewById(R.id.til_card);
        tilsvcCard = (TextInputLayout)findViewById(R.id.til_svcCard);
        tilAddress = (TextInputLayout)findViewById(R.id.til_address);

        txtName = (EditText)findViewById(R.id.txtName);
        txtUserName = (EditText)findViewById(R.id.txtUserName);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        txtCard = (EditText)findViewById(R.id.txtCard);
        txtSvcCard = (EditText)findViewById(R.id.txtsvcCard);
        txtAddress = (EditText)findViewById(R.id.txtAddress);


        Button btnAccept = (Button)findViewById(R.id.btn_accept);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

        //---------------------------Métodos para validar los campos ---------------------------//

        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilUserName.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilAddress.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isCorrectPassword();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilCard.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtSvcCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilsvcCard.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void validateData(){
        if(isCorrectName() && isCorrectAddress() && isCorrectEmail() && isCorrectNumberCard()
                && isCorrectSvcCard() && isCorrectUserName() && isCorrectPassword()){

            String[] lastName = txtName.getText().toString().split(" ");
            String information = "0;"+lastName[0]+";"+lastName[1]+";"+lastName[2]+";"+
                    txtUserName.getText().toString()+";"+txtPassword.getText().toString()+";"+
                    txtEmail.getText().toString()+";"+txtCard.getText().toString()+";"+
                    txtAddress.getText().toString()+";0000;"+txtSvcCard.getText().toString();

            String enInformation = encryption.encrypt(information,txtUserName.getText().toString());
            //WS para insertar el cliente
            new InsertClientWS(this).execute(enInformation,txtUserName.getText().toString());

        }
    }
    public boolean isCorrectName(){
        String name = tilName.getEditText().getText().toString();
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        String[] names = name.split(" ");
        if (!patron.matcher(name).matches() || name.length() > 30 || names.length < 2) {
            tilName.setError("Nombre inválido");
            return false;
        } else {
            tilName.setError(null);
        }
        return true;
    }
    public boolean isCorrectUserName(){
        String userName = tilUserName.getEditText().getText().toString();
        if ((userName.length() > 30) || (userName == tilName.getEditText().getText().toString()) ||
                (userName.length() < 1)) {
            tilUserName.setError("Nombre usuario inválido");
            return false;
        } else {
            tilUserName.setError(null);
        }
        return true;
    }
    private boolean isCorrectEmail() {

        if (!Patterns.EMAIL_ADDRESS.matcher(tilEmail.getEditText().getText().toString()).matches()) {
            tilEmail.setError("Correo electrónico inválido");
            return false;
        } else {
            tilEmail.setError(null);
        }

        return true;
    }
    private boolean isCorrectNumberCard() {
        Pattern patron = Pattern.compile("^((67\\d{2})|(4\\d{3})|(5[1-5]\\d{2})|(6011))(-?\\s?\\d{4}){3}|(3[4,7])\\d{2}-?\\s?\\d{6}-?\\s?\\d{5}$");

        if (!patron.matcher(tilCard.getEditText().getText().toString()).matches() ||
                tilCard.getEditText().getText().toString().length() > 16) {
            tilCard.setError("Numero de tarjeta inválido");
            return false;
        } else {
            tilCard.setError(null);
        }
        return true;
    }
    private boolean isCorrectSvcCard() {

        if (tilsvcCard.getEditText().getText().toString().length() > 3 ||
                tilsvcCard.getEditText().getText().toString().length() < 1) {
            tilsvcCard.setError("Número de SVC inválido");
            return false;
        } else {
            tilsvcCard.setError(null);
        }
        return true;
    }
    public boolean isCorrectAddress(){

        if (tilAddress.getEditText().getText().toString().length() > 200 ||
                tilAddress.getEditText().getText().toString().length() < 10) {
            tilAddress.setError("Dirección no válida 10-200 caracteres");
            return false;
        } else {
            tilAddress.setError(null);
        }
        return true;
    }
    public boolean isCorrectPassword(){

        if (tilPassword.getEditText().getText().toString().length() > 30 ||
                tilPassword.getEditText().getText().toString().length() < 1 ) {
            tilPassword.setError("Contraseña no válida");
            return false;
        } else {
            tilPassword.setError(null);
        }
        return true;
    }

}
