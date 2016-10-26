package com.keggphones;

import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.keggphones.Security.Encryption;
import com.keggphones.WS.BCCRWS;
import com.keggphones.WS.ClientWS;
import com.keggphones.WS.getAllPhonesWS;
import com.keggphones.WS.getInformationClientWS;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilUserName;
    private TextInputLayout tilPassword;
    private EditText txtUserName;
    private EditText txtPassword;
    public static String key = "";
    Encryption encryption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        encryption = new Encryption();
        new BCCRWS(this).execute("317");

        tilUserName = (TextInputLayout)findViewById(R.id.til_user);
        tilPassword = (TextInputLayout)findViewById(R.id.til_password);


        Button btnAccept = (Button)findViewById(R.id.btn_accept_login);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    validateData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });



        Button btnRegistry = (Button)findViewById(R.id.btnRegistry);
        btnRegistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registry = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(registry);
                finish();
            }
        });


        txtUserName = (EditText)findViewById(R.id.txtUserName);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
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


    }

    public void validateData() throws InterruptedException {
        if(isCorrectPassword() && isCorrectUserName()){
            //Se hace la validación  en la base da datos, si es correcto se
            //abre la pantalla principal
            String userName = txtUserName.getText().toString();
            String password = txtPassword.getText().toString();
            key = userName;
            String client = encryption.encrypt(userName+";"+password,key);
            String enUser = encryption.encrypt(userName,key);
            //Consumo ws para verificar el cliente
            new ClientWS(this).execute(client,key);
            //Consumo ws para obtener los datos del cliente
            new getInformationClientWS(this).execute(enUser,key);

        }

    }



    public boolean isCorrectUserName(){
        String userName = tilUserName.getEditText().getText().toString();
        if (userName.length() > 30 || userName.length() < 1) {
            tilUserName.setError("Usuario inválido");
            return false;
        } else {
            tilUserName.setError(null);
        }
        //Consumo ws para obtener todos los celulares
        new getAllPhonesWS(this).execute(userName);
        return true;
    }
    public boolean isCorrectPassword(){

        if (tilPassword.getEditText().getText().toString().length() > 30 ||
                tilPassword.getEditText().getText().toString().length() < 1) {
            tilPassword.setError("Contraseña inválida");
            return false;
        } else {
            tilPassword.setError(null);
        }
        return true;
    }

}
