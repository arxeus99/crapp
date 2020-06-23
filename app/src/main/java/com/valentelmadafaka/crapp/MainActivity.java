package com.valentelmadafaka.crapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.valentelmadafaka.crapp.db.CrappDB;
import com.valentelmadafaka.crapp.models.Registro;
import com.valentelmadafaka.crapp.models.RegistroTemporal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


class MyXAxisFormatter extends ValueFormatter{
    private String[] days = {"Mo", "Tu", "Wed", "Th", "Fr", "Sa", "Su"};

    @Override
    public String getFormattedValue(float value) {
        return days[(int)value];
    }
}



public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        List<BarEntry> entries = new ArrayList<BarEntry>();

        CrappDB crappDB = new CrappDB(this);

        crappDB.open();

        Cursor c = crappDB.obtenerRegistros();

        c.moveToFirst();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int week =  cal.get(Calendar.WEEK_OF_YEAR);

        ArrayList<Registro> registros = new ArrayList<>();


        BarChart chart = (BarChart) findViewById(R.id.chart);

        while(!c.isAfterLast()){
            Registro registro = new Registro();
            registro.setPuntuacionFinal(c.getInt(1));
            registro.setColor(c.getString(2));
            registro.setDiagnosticoColor(c.getString(3));
            if(c.getString(4)!=null){
                registro.setDiagnosticoDolor(c.getString(4));
            }
            if(c.getString(5)!=null){
                registro.setDiagnosticoGases(c.getString(5));
            }
            registro.setFecha(c.getString(6));
            if(week == Integer.parseInt(c.getString(7))){
                registro.setSemana(c.getString(7));
                registro.setDia(c.getString(8));
                registro.setMes(c.getInt(9));
                registro.setAño(c.getInt(10));
                registros.add(registro);
            }
            c.moveToNext();
        }

        crappDB.close();

        int totalLunes = 0;
        int countLunes = 0;
        int totalMartes = 0;
        int countMartes = 0;
        int totalMiercoles = 0;
        int countMiercoles = 0;
        int totalJueves = 0;
        int countJueves = 0;
        int totalViernes = 0;
        int countViernes = 0;
        int totalSabado = 0;
        int countSabado = 0;
        int totalDomingo = 0;
        int countDomingo = 0;

        for(Registro r : registros){
            switch(r.getDia()){
                case "Lunes":{
                    totalLunes += r.getPuntuacionFinal();
                    countLunes++;
                    break;
                }
                case "Martes":{
                    totalMartes += r.getPuntuacionFinal();
                    countMartes++;
                    break;
                }
                case "Miercoles":{
                    totalMiercoles += r.getPuntuacionFinal();
                    countMiercoles++;
                    break;
                }
                case "Jueves":{
                    totalJueves += r.getPuntuacionFinal();
                    countJueves++;
                    break;
                }
                case "Viernes":{
                    totalViernes += r.getPuntuacionFinal();
                    countViernes++;
                    break;
                }
                case "Sabado":{
                    totalSabado += r.getPuntuacionFinal();
                    countSabado++;
                    break;
                }
                case "Domingo":{
                    totalDomingo += r.getPuntuacionFinal();
                    countDomingo++;
                    break;
                }
            }
        }

        int mediaLunes;
        int mediaMartes;
        int mediaMiercoles;
        int mediaJueves;
        int mediaViernes;
        int mediaSabado;
        int mediaDomingo;
        if(countLunes>0){
            mediaLunes = totalLunes/countLunes;
        }else{
            mediaLunes = 0;
        }
        if(countMartes>0){
            mediaMartes = totalMartes/countMartes;
        }else{
            mediaMartes = 0;
        }
        if(countMiercoles>0){
            mediaMiercoles = totalMiercoles/countMiercoles;
        }else{
            mediaMiercoles = 0;
        }
        if(countJueves>0){
            mediaJueves = totalJueves/countJueves;
        }else{
            mediaJueves = 0;
        }
        if(countViernes>0){
            mediaViernes = totalViernes/countViernes;
        }else{
            mediaViernes = 0;
        }
        if(countSabado>0){
            mediaSabado = totalSabado/countSabado;
        }else{
            mediaSabado = 0;
        }
        if(countDomingo>0){
            mediaDomingo = totalDomingo/countDomingo;
        }else {
            mediaDomingo = 0;
        }

        entries.add(new BarEntry(0, mediaLunes));
        entries.add(new BarEntry(1, mediaMartes));
        entries.add(new BarEntry(2, mediaMiercoles));
        entries.add(new BarEntry(3, mediaJueves));
        entries.add(new BarEntry(4, mediaViernes));
        entries.add(new BarEntry(5, mediaSabado));
        entries.add(new BarEntry(6, mediaDomingo));


        BarDataSet dataSet = new BarDataSet(entries, "");
        dataSet.setValueTextSize(0f);

        BarData barData = new BarData(dataSet);

        chart.setData(barData);
        chart.invalidate();
        chart.getAxis(YAxis.AxisDependency.LEFT).setAxisMinimum(0f);
        chart.getAxis(YAxis.AxisDependency.LEFT).setAxisMaximum(100f);
        chart.getAxis(YAxis.AxisDependency.LEFT).setDrawGridLines(false);
        chart.getAxis(YAxis.AxisDependency.LEFT).setDrawAxisLine(false);
        chart.getAxis(YAxis.AxisDependency.LEFT).setTextSize(0f);
        chart.getAxis(YAxis.AxisDependency.RIGHT).setEnabled(false);
        MyXAxisFormatter myXAxisFormatter = new MyXAxisFormatter();
        chart.getXAxis().setValueFormatter(myXAxisFormatter);
        chart.getXAxis().setAxisMaximum(6.5f);
        chart.getXAxis().setAxisMinimum(-0.5f);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setDrawAxisLine(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.setFocusableInTouchMode(false);




    }

    public void nuevoRegistro(View view) {
        RegistroTemporal registroTemporal = new RegistroTemporal();
        startActivity(new Intent(this, BristolActivity.class));
    }
}
