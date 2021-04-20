package com.example.trikitoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Creditos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);
    }
    public void onClick(View view){
        Intent nav = new Intent(Creditos.this,Inicio.class);
        startActivity(nav);
    }
}