package com.valentelmadafaka.crapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.valentelmadafaka.crapp.models.RegistroTemporal;

public class BristolActivity extends AppCompatActivity {

    SeekBar scroll;
    TextView textView;
    RegistroTemporal registro;
    int puntuacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bristol);
        scroll = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        registro = new RegistroTemporal();
        scroll.getProgress();
        scroll.setMax(170);
        scroll.setProgress(85);
        textView.setText("");
        scroll.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(progress+"");
                if(progress>= 0 && progress<15){
                    textView.setText("BRISTOL 1");
                    registro.setBristolTable(1);
                }else if(progress>=-15 && progress <45){
                    textView.setText("BRISTOL 2");
                    registro.setBristolTable(2);
                }else if(progress>=- 45 && progress <75){
                    textView.setText("BRISTOL 3");
                    registro.setBristolTable(3);
                }else if(progress>=75 && progress <=95){
                    textView.setText("BRISTOL 4");
                    registro.setBristolTable(4);
                }else if(progress > 95 && progress <=125){
                    textView.setText("BRISTOL 5");
                    registro.setBristolTable(5);
                }else if(progress>125 && progress<=155){
                    textView.setText("BRISTOL 6");
                    registro.setBristolTable(6);
                }else if(progress>155 && progress <=170){
                    textView.setText("BRISTOL 7");
                    registro.setBristolTable(7);
                }

                puntuacion = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void color(View view) {
        if(puntuacion<=85){
            puntuacion += 15;
        }else{
            puntuacion = -puntuacion + 185;
        }
        registro.setBristol(puntuacion);
        Intent i = new Intent(this, ColorActivity.class);
        i.putExtra("Registro", registro);
        startActivity(i);
    }
}
