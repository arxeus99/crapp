package com.valentelmadafaka.crapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.valentelmadafaka.crapp.PreguntasActivity;
import com.valentelmadafaka.crapp.R;
import com.valentelmadafaka.crapp.models.RegistroTemporal;

public class ColorActivity extends AppCompatActivity {

    String color;
    Button siguiente;
    RegistroTemporal registroTemporal;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        textView = (TextView)findViewById(R.id.textView2);
        siguiente = (Button)findViewById(R.id.button12);
        siguiente.setEnabled(false);
        Bundle extras =getIntent().getExtras();
        registroTemporal = (RegistroTemporal)extras.get("Registro");
    }

    public void marron(View view){
        textView.setText("MARRON");
        color = "marron";
        siguiente.setEnabled(true);
    }
    public void verde(View view){
        textView.setText("VERDE");
        color = "verde";
        siguiente.setEnabled(true);
    }
    public void amarillo(View view){
        textView.setText("AMARILLO");
        color = "amarillo";
        siguiente.setEnabled(true);
    }
    public void rojo(View view){
        textView.setText("ROJO");
        color = "rojo";
        siguiente.setEnabled(true);
    }
    public void negro(View view){
        textView.setText("NEGRO");
        color = "negro";
        siguiente.setEnabled(true);
    }
    public void blanco(View view){
        textView.setText("BLANCO");
        color = "blanco";
        siguiente.setEnabled(true);
    }
    public void siguiente(View view){
        registroTemporal.setColor(color);
        Intent i = new Intent(this, PreguntasActivity.class);
        i.putExtra("Registro", registroTemporal);
        startActivity(i);
    }

    public void volver(View view) {
        finish();
    }
}
