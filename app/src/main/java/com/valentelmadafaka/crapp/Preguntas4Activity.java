package com.valentelmadafaka.crapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.valentelmadafaka.crapp.R;
import com.valentelmadafaka.crapp.models.RegistroTemporal;

public class Preguntas4Activity extends AppCompatActivity {

    RegistroTemporal registroTemporal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas4);
        Bundle extras =getIntent().getExtras();
        registroTemporal = (RegistroTemporal)extras.get("Registro");
    }

    public void si(View view) {
        Intent i = new Intent(this, ResultadoActivity.class);
        i.putExtra("Registro", registroTemporal);
        i.putExtra("Respuesta", "YSD");
        startActivity(i);
    }

    public void no(View view){
        Intent i = new Intent(this, ResultadoActivity.class);
        i.putExtra("Registro", registroTemporal);
        startActivity(i);
    }
}
