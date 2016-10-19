package com.keggphones;

import android.content.Intent;
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

import com.keggphones.WS.ClientWS;
import com.keggphones.WS.getAllPhonesWS;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilUserName;
    private TextInputLayout tilPassword;
    private EditText txtUserName;
    private EditText txtPassword;
    public static String flagWS = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        tilUserName = (TextInputLayout)findViewById(R.id.til_user);
        tilPassword = (TextInputLayout)findViewById(R.id.til_password);


        Button btnAccept = (Button)findViewById(R.id.btn_accept_login);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

        new getAllPhonesWS(this).execute("");

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

    public void validateData(){
        if(isCorrectPassword() && isCorrectUserName()){
            //Se hace la validación  en la base da datos, si es correcto se
            //abre la pantalla principal
            new ClientWS(this).execute(txtUserName.getText().toString(),txtPassword.getText().toString());

            if(flagWS.equals("1") == true){

                Intent intentMenu = new Intent(LoginActivity.this, MenuActivity.class);
                //String userName = txtUserName.getText().toString();
                //intentMenu.putExtra("userName", userName);
                startActivity(intentMenu);
                finish();

            }else{
                Toast.makeText(this, "El usuario no existe, debe registrase", Toast.LENGTH_LONG).show();
                /*Descomentar cuando se haga la validación en la base de datos
                Intent registry = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(registry);
                finish();
                */
            }


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
