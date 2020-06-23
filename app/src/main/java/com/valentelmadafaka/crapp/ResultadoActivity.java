package com.valentelmadafaka.crapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.valentelmadafaka.crapp.db.CrappDB;
import com.valentelmadafaka.crapp.models.Registro;
import com.valentelmadafaka.crapp.models.RegistroTemporal;

public class ResultadoActivity extends AppCompatActivity {

    RegistroTemporal registroTemporal;
    String respuesta = "";
    Registro registro;

    TextView puntuacion, color, textura, tituloDolor, dolor, tituloGases, gases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        registroTemporal = (RegistroTemporal)extras.get("Registro");
        if(extras.get("Respuesta") != null){
            respuesta = (String)extras.get("Respuesta");
        }
        registro = new Registro();
        sacarInfo(registroTemporal, registro);
        super.onCreate(savedInstanceState);
        if(registro.getDiagnosticoDolor() == null){
            setContentView(R.layout.activity_resultado_no_dolor);
            gases = findViewById(R.id.infoGases);
        }else if(registro.getDiagnosticoGases() == null){
            setContentView(R.layout.activity_resultado_no_gases);
            dolor = findViewById(R.id.infoDolor);
        }else if(registro.getDiagnosticoDolor() == null && registro.getDiagnosticoGases() == null){
            setContentView(R.layout.activity_resultado_no_dolor_gases);
        }else{
            setContentView(R.layout.activity_resultado);
            dolor = findViewById(R.id.infoDolor);
            gases = findViewById(R.id.infoGases);
        }
        puntuacion = findViewById(R.id.puntosText);
        color = findViewById(R.id.infoColor);
        textura = findViewById(R.id.infoTextura);

        double puntuacionFinal = registroTemporal.getPuntosGases()*0.1+registroTemporal.getPuntosDolor() *0.15+registroTemporal.getBristol()*0.25+registro.getColorPuntuacion()*0.4+registroTemporal.getPuntosFlota()*0.1;
        registro.setPuntuacionFinal((int)Math.round(puntuacionFinal));

        puntuacion.setText(registro.getPuntuacionFinal()+"");
        if(respuesta.isEmpty())
            color.setText(registro.getDiagnosticoColor());
        else
            color.setText(respuesta);
        textura.setText(registro.getBristolTable());
        if(registro.getDiagnosticoDolor() != null)
            dolor.setText(registro.getDiagnosticoDolor());
        if(registro.getDiagnosticoGases() != null)
            gases.setText(registro.getDiagnosticoGases());


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy EEEE HH:mm", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        registro.setFecha(currentDateandTime);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        registro.setSemana(cal.get(Calendar.WEEK_OF_YEAR)+"");
        switch (cal.get(Calendar.DAY_OF_WEEK)){
            case 2:{
                registro.setDia("Lunes");
                break;
            }
            case 3:{
                registro.setDia("Martes");
                break;
            }
            case 4:{
                registro.setDia("Miercoles");
                break;
            }
            case 5:{
                registro.setDia("Jueves");
                break;
            }
            case 6:{
                registro.setDia("Viernes");
                break;
            }
            case 7:{
                registro.setDia("Sabado");
                break;
            }
            case 1:{
                registro.setDia("Domingo");
                break;
            }
        }
        registro.setMes(cal.get(Calendar.MONTH));
        registro.setAño(Calendar.YEAR);

        CrappDB db = new CrappDB(this);

        db.open();

        if(db.insertaRegistro(registro) == -1){
            Toast.makeText(this, "Error a l’afegir",
                    Toast.LENGTH_SHORT).show();
        }

        db.close();


    }

    public void volver(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    void sacarInfo(RegistroTemporal registroTemporal, Registro registro){
        Resources res = getResources();
        registro.setBristolPuntuacion(registroTemporal.getBristol());
        switch (registroTemporal.getBristolTable()){
            case 1:{registro.setBristolTable("BRISTOL 1"); break;}
            case 2:{registro.setBristolTable("BRISTOL 2"); break;}
            case 3:{registro.setBristolTable("BRISTOL 3"); break;}
            case 4:{registro.setBristolTable("BRISTOL 4"); break;}
            case 5:{registro.setBristolTable("BRISTOL 5"); break;}
            case 6:{registro.setBristolTable("BRISTOL 6"); break;}
            case 7:{registro.setBristolTable("BRISTOL 7"); break;}
        }
        switch (registroTemporal.getColor()){
            case "marron":{registro.setColorPuntuacion(res.getInteger(R.integer.marron)); registro.setDiagnosticoColor("BD"); registro.setColor("Marrón"); break;}
            case "verde":{
                registro.setColor("Verde");
                if(registro.getBristolTable().equals("BRISTOL 6") || registro.getBristolTable().equals("BRISTOL 7")){
                    registro.setColorPuntuacion(res.getInteger(R.integer.verde_b6_b7));
                    registro.setDiagnosticoColor("GBD");
                }else if(registroTemporal.getPuntosGases() == res.getInteger(R.integer.gases_mal)){
                    registro.setColorPuntuacion(res.getInteger(R.integer.verde_gases_mal));
                    registro.setDiagnosticoColor("GGD");
                }else{
                    registro.setColorPuntuacion(res.getInteger(R.integer.verde));
                    registro.setDiagnosticoColor("JGD");
                }
                break;
            }
            case "amarillo":{registro.setColor("Amarillo"); registro.setColorPuntuacion(res.getInteger(R.integer.amarillo)); registro.setDiagnosticoColor ("JYD"); break;}
            case "rojo":{
                registro.setColor("Rojo");
                if(registroTemporal.getPuntosDolor() == res.getInteger(R.integer.duele_mucho)){
                    if(registroTemporal.getBristolTable()==1 || registroTemporal.getBristolTable() == 2){ registro.setColorPuntuacion(res.getInteger(R.integer.rojo_duele_b1_b2)); registro.setDiagnosticoColor("RBHD"); }
                    else{registro.setColorPuntuacion(res.getInteger(R.integer.rojo_duele)); registro.setDiagnosticoColor("RHD");}
                }else{registro.setColorPuntuacion(res.getInteger(R.integer.rojo)); registro.setDiagnosticoColor("JRD");}
                break;
            }
            case "negro":{registro.setColor("Negro"); registro.setColorPuntuacion(res.getInteger(R.integer.negro)); registro.setDiagnosticoColor("JBD"); break;}
            case "blanco":{registro.setColor("Blanco"); registro.setColorPuntuacion(res.getInteger(R.integer.blanco)); registro.setDiagnosticoColor("WD"); break;}
        }
        if(res.getInteger(R.integer.gases_mal) == registroTemporal.getPuntosGases()){
            registro.setDiagnosticoGases("POR FAVOR GASES DIAGNOSTICO");
        }else if(res.getInteger(R.integer.gases_normal) == registroTemporal.getPuntosGases()){
            registro.setDiagnosticoGases("GASES MUCHOS DIAGNOSTICO");
        }

        if(registroTemporal.getPuntosDolor() == res.getInteger(R.integer.duele_mucho)){
            registro.setDiagnosticoDolor("DUELE MUCHO DIAGNOSTICO");
        }else if(registroTemporal.getPuntosDolor() == res.getInteger(R.integer.gases_normal)){
            registro.setDiagnosticoDolor("ESFUERZO DIAGNOSTICO");
        }

        if(!respuesta.isEmpty()){
            registro.setDiagnosticoColor(respuesta);
        }
    }
}
