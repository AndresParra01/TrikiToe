package com.example.trikitoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }
    public void onClick(View view){
        Intent nav = null;
        switch (view.getId()){
            case R.id.button_play:
                nav= new Intent(Inicio.this,Jugar.class);
                break;

            case R.id.button_credits:
                nav= new Intent(Inicio.this,Creditos.class);
                break;
        }
        startActivity(nav);
    }
}