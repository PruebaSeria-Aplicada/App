package com.keggphones;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.keggphones.Domain.Client;
import com.keggphones.Security.Encryption;
import com.keggphones.WS.ClientWS;
import com.keggphones.WS.SearchPhonesWS;


public class SearchPhoneActivity extends AppCompatActivity {


    private String wordSearch;
    Encryption encryption = new Encryption();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_phone);

        Button btnSearch = (Button)findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWord();
            }
        });





    }

    public void getWord(){
        TextView word = (TextView)findViewById(R.id.txt_word_search);
        wordSearch = word.getText().toString();
        wordSearch = encryption.encrypt(wordSearch,LoginActivity.key);
        new SearchPhonesWS(this).execute(wordSearch,LoginActivity.key);
    }



}







