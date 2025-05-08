package com.example.elapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btnImprimir = null;
    TextView txtMensaje = null;
    CheckBox check = null;
    CalendarView fecha = null;
    String fechadehoy = null;
    String hoy = "";
    Long tiempo = null;
    ImageButton BotonImg = null;
    String msgMensaje = ""; //Existe alguna diferencia enter null y "" ??

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Date hora = new Date();
        String pattern = "HH:mm";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatofecha = new SimpleDateFormat(pattern); //Que es el SuppressLint??
        String Hora = formatofecha.format(new Date());

        btnImprimir = findViewById(R.id.btnHola);
        txtMensaje = findViewById(R.id.textView);
        check = findViewById(R.id.checkBox);
        fecha = findViewById(R.id.calendarView);
        BotonImg = findViewById(R.id.imageButton);

        SimpleDateFormat FormatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        fecha.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                tiempo = fecha.getDate();

                //fechadehoy = FormatoFecha.format(tiempo);
                //fechadehoy = fechadehoy.concat(String.valueOf(Year)).concat(String.valueOf(month)).concat(Strings.valueOf(dayOfMonth));
                //fechadehoy = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(dayOfMonth);
                fechadehoy = dayOfMonth + "/" + (month+1) + "/" + year;
                //Sumarle un 1 al mes porque empieza en 0
            }
        });

        btnImprimir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoy = fechadehoy;

                msgMensaje += ", "+txtMensaje.getText();
                msgMensaje += ", "+check.isChecked();
                msgMensaje += ", "+Hora;
                msgMensaje += ", "+hoy;
                //Diferencia entre "=" e "+="

                Toast.makeText(getApplicationContext(), msgMensaje, Toast.LENGTH_LONG).show();

                Snackbar snackbar = Snackbar.make(v,"Hola Mundo, estoy en clase de Android", Snackbar.LENGTH_LONG);
                snackbar.show();

                msgMensaje = "";

                //Toast.makeText(getApplicationContext(),txtMensaje.getText()+", "+check.isChecked()+", "+Hora+", y hoy es "+hoy,Toast.LENGTH_LONG).show();
                //Despues de imprimir el toast tenemos que limpiar la variable
            }
        });

        BotonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent puente = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(puente);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}