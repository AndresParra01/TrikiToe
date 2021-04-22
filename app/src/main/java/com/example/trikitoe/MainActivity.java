package com.example.trikitoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] botones = new Button[3][3];
    private boolean turno1 = true;
    private int contadorRonda;
    private int puntosJugador1;
    private int puntosJugador2;
    private TextView textViewJugador1;
    private TextView textViewJugador2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewJugador1 = findViewById(R.id.text_view_p1);
        textViewJugador2 = findViewById(R.id.text_view_p2);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String IDboton = "button_" + i + j;
                int IDres = getResources().getIdentifier(IDboton, "id", getPackageName());
                botones[i][j] = findViewById(IDres);
                botones[i][j].setOnClickListener(this);
            }
        }
        Button reiniciarBoton = findViewById(R.id.button_reset);
        reiniciarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciarJuego();
            }
        });
    }
    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (turno1) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }
        contadorRonda++;
        if (revisarVictoria()) {
            if (turno1) {
                victoriaJugador1();
            } else {
                victoriaJugador2();
            }
        } else if (contadorRonda == 9) {
            dibujar();
        } else {
            turno1 = !turno1;
        }
    }
    private boolean revisarVictoria() {
        String[][] campo = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                campo[i][j] = botones[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (campo[i][0].equals(campo[i][1])
                    && campo[i][0].equals(campo[i][2])
                    && !campo[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (campo[0][i].equals(campo[1][i])
                    && campo[0][i].equals(campo[2][i])
                    && !campo[0][i].equals("")) {
                return true;
            }
        }
        if (campo[0][0].equals(campo[1][1])
                && campo[0][0].equals(campo[2][2])
                && !campo[0][0].equals("")) {
            return true;
        }
        if (campo[0][2].equals(campo[1][1])
                && campo[0][2].equals(campo[2][0])
                && !campo[0][2].equals("")) {
            return true;
        }
        return false;
    }
    private void victoriaJugador1() {
        puntosJugador1++;
        Toast.makeText(this, "Gana el jugador 1!", Toast.LENGTH_SHORT).show();
        actualizarPuntos();
        reiniciarTablero();
    }
    private void victoriaJugador2() {
        puntosJugador2++;
        Toast.makeText(this, "Gana el jugador 2!", Toast.LENGTH_SHORT).show();
        actualizarPuntos();
        reiniciarTablero();
    }
    private void dibujar() {
        Toast.makeText(this, "Empate!", Toast.LENGTH_SHORT).show();
        reiniciarTablero();
    }
    private void actualizarPuntos() {
        textViewJugador1.setText("Jugador 1: " + puntosJugador1);
        textViewJugador2.setText("Jugador 2: " + puntosJugador2);
    }
    private void reiniciarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setText("");
            }
        }
        contadorRonda = 0;
        turno1 = true;
    }
    private void reiniciarJuego() {
        puntosJugador1 = 0;
        puntosJugador2 = 0;
        actualizarPuntos();
        reiniciarTablero();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("contadorRonda", contadorRonda);
        outState.putInt("puntosJugador1", puntosJugador1);
        outState.putInt("puntosJugador2", puntosJugador2);
        outState.putBoolean("turnoJugador1", turno1);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        contadorRonda = savedInstanceState.getInt("contadorRonda");
        puntosJugador1 = savedInstanceState.getInt("puntosJugador1");
        puntosJugador2 = savedInstanceState.getInt("puntosJugador2");
        turno1 = savedInstanceState.getBoolean("turnoJugador1");
    }
}