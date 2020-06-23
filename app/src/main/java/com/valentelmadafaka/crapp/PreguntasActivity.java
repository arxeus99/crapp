package com.valentelmadafaka.crapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.valentelmadafaka.crapp.models.RegistroTemporal;

public class PreguntasActivity extends AppCompatActivity {

    RegistroTemporal registroTemporal;
    RadioGroup dolor, gases, flota;
    Button siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        siguiente = findViewById(R.id.button14);
        siguiente.setEnabled(false);
        gases = findViewById(R.id.GasesGroup);
        flota = findViewById(R.id.FlotaGroup);
        dolor = findViewById(R.id.DolorGroup);

        gases.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(flota.getCheckedRadioButtonId() != -1 && dolor.getCheckedRadioButtonId() != -1)
                    siguiente.setEnabled(true);
                switch (checkedId){
                    case R.id.GM:{
                        registroTemporal.setPuntosGases(getResources().getInteger(R.integer.gases_mal));
                        break;
                    }
                    case R.id.GN:{
                        registroTemporal.setPuntosGases(getResources().getInteger(R.integer.gases_normal));
                        break;
                    }
                    case R.id.GP:{
                        registroTemporal.setPuntosGases(getResources().getInteger(R.integer.gases_bien));
                        break;
                    }
                }
            }
        });
        flota.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(gases.getCheckedRadioButtonId() != -1 && dolor.getCheckedRadioButtonId() != -1)
                    siguiente.setEnabled(true);
                switch (checkedId){
                    case R.id.FS:{
                        registroTemporal.setPuntosFlota(getResources().getInteger(R.integer.flota));
                        break;
                    }
                    case R.id.FN:{
                        registroTemporal.setPuntosFlota(getResources().getInteger(R.integer.no_flota));
                        break;
                    }
                }
            }
        });
        dolor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(flota.getCheckedRadioButtonId() != -1 && gases.getCheckedRadioButtonId() != -1)
                    siguiente.setEnabled(true);

                switch (checkedId){
                    case R.id.DM:{
                        registroTemporal.setPuntosDolor(getResources().getInteger(R.integer.duele_mucho));
                        break;
                    }
                    case R.id.DN:{
                        registroTemporal.setPuntosDolor(getResources().getInteger(R.integer.duele_normal));
                        break;
                    }
                    case R.id.DP:{
                        registroTemporal.setPuntosDolor(getResources().getInteger(R.integer.duele_poco));
                        break;
                    }
                }
            }
        });

        Bundle extras = getIntent().getExtras();
        registroTemporal = (RegistroTemporal)extras.get("Registro");

    }

    public void volver(View view) {
        finish();
    }

    public void siguiente(View view) {
        if(registroTemporal.getColor().equals("amarillo")){
            if(registroTemporal.getPuntosFlota() == getResources().getInteger(R.integer.flota)){
                Intent i = new Intent(this, Preguntas2Activity.class);
                i.putExtra("Registro", registroTemporal);
                startActivity(i);
            }else if(registroTemporal.getBristolTable() == 6 || registroTemporal.getBristolTable() == 7){
                Intent i = new Intent(this, Preguntas3Activity.class);
                i.putExtra("Registro", registroTemporal);
                startActivity(i);
            }else{
                Intent i = new Intent(this, Preguntas4Activity.class);
                i.putExtra("Registro", registroTemporal);
                startActivity(i);
            }
        }else if(registroTemporal.getColor().equals("negro")){
            Intent i = new Intent(this, Preguntas5Activity.class);
            i.putExtra("Registro", registroTemporal);
            startActivity(i);
        }else{
            Intent i = new Intent(this, ResultadoActivity.class);
            i.putExtra("Registro", registroTemporal);
            startActivity(i);
        }
    }
}
