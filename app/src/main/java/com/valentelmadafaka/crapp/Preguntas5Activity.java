package com.valentelmadafaka.crapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.valentelmadafaka.crapp.models.RegistroTemporal;

public class Preguntas5Activity extends AppCompatActivity {

    RegistroTemporal registroTemporal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas5);
        Bundle extras =getIntent().getExtras();
        registroTemporal = (RegistroTemporal)extras.get("Registro");
    }

    public void si(View view) {
        Intent i = new Intent(this, ResultadoActivity.class);
        i.putExtra("Registro", registroTemporal);
        i.putExtra("Respuesta", "BED");
        startActivity(i);
    }

    public void no(View view){
        Intent i = new Intent(this, ResultadoActivity.class);
        i.putExtra("Registro", registroTemporal);
        startActivity(i);
    }
}
