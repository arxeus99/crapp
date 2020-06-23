package com.valentelmadafaka.crapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.valentelmadafaka.crapp.models.Registro;

public class CrappDB {

    public static final int VERSION_DB = 3;
    public static final String TAG = "DBInterface";
    public static final String NOMBRE_DB = "crapp.db";
    public static final String TAB_REGISTROS = "registros";
    public static final String COL_REGISTRO_ID = "id";
    public static final String COL_REGISTRO_PUNTUACION = "puntuacion";
    public static final String COL_REGISTRO_BRISTOL = "bristol";
    public static final String COL_REGISTRO_COLOR = "color";
    public static final String COL_REGISTRO_COLOR_DIAGNOSTICO = "diagnosticocolor";
    public static final String COL_REGISTRO_GASES_DIAGNOSTICO = "diagnosticogases";
    public static final String COL_REGISTRO_DOLOR_DIAGNOSTICO = "diagnosticodolor";
    public static final String COL_REGISTRO_FECHA = "fecha";
    public static final String COL_REGISTRO_SEMANA = "semana";
    public static final String COL_REGISTRO_DIA = "dia";
    public static final String COL_REGISTRO_MES = "mes";
    public static final String COL_REGISTRO_AÑO = "año";
    public static final String CREATE_TABLE_REGISTRO = "create table " + TAB_REGISTROS +
            "("
            + COL_REGISTRO_ID + " integer primary key autoincrement, "
            + COL_REGISTRO_PUNTUACION + " integer not null, "
            + COL_REGISTRO_BRISTOL + " text not null, "
            + COL_REGISTRO_COLOR + " text not null, "
            + COL_REGISTRO_COLOR_DIAGNOSTICO + " text not null, "
            + COL_REGISTRO_GASES_DIAGNOSTICO + " text, "
            + COL_REGISTRO_DOLOR_DIAGNOSTICO + " text, "
            + COL_REGISTRO_FECHA + " text not null, "
            + COL_REGISTRO_SEMANA+ " text not null, "
            + COL_REGISTRO_DIA+ " text not null, "
            + COL_REGISTRO_MES+ " text not null, "
            + COL_REGISTRO_AÑO+ " text not null)";



    private Context context;
    private HelperDB helperDB;
    private SQLiteDatabase bd;

    public CrappDB(Context context){
        this.context = context;
        helperDB = new HelperDB(context);
    }

    public CrappDB open() throws SQLException{
        bd = helperDB.getWritableDatabase();
        return this;
    }

    public void close(){ helperDB.close(); }

    public SQLiteDatabase getBd(){ return this.bd; }

    public long insertaRegistro(Registro registro){
        ContentValues initialValues = new ContentValues();
        initialValues.put(COL_REGISTRO_PUNTUACION, registro.getPuntuacionFinal());
        initialValues.put(COL_REGISTRO_BRISTOL, registro.getBristolTable());
        initialValues.put(COL_REGISTRO_COLOR, registro.getColor());
        initialValues.put(COL_REGISTRO_COLOR_DIAGNOSTICO, registro.getDiagnosticoColor());
        if(registro.getDiagnosticoDolor() != null){
            initialValues.put(COL_REGISTRO_DOLOR_DIAGNOSTICO, registro.getDiagnosticoDolor());
        }
        if(registro.getDiagnosticoGases() != null){
            initialValues.put(COL_REGISTRO_GASES_DIAGNOSTICO, registro.getDiagnosticoGases());
        }
        initialValues.put(COL_REGISTRO_FECHA, registro.getFecha());
        initialValues.put(COL_REGISTRO_SEMANA, registro.getSemana());
        initialValues.put(COL_REGISTRO_DIA, registro.getDia());
        initialValues.put(COL_REGISTRO_MES, registro.getMes());
        initialValues.put(COL_REGISTRO_AÑO, registro.getAño());
        return bd.insert(TAB_REGISTROS, null, initialValues);
    }

    public Cursor obtenerRegistros(){
        return bd.query(TAB_REGISTROS, new String[]{COL_REGISTRO_ID, COL_REGISTRO_PUNTUACION, COL_REGISTRO_COLOR,
        COL_REGISTRO_COLOR_DIAGNOSTICO, COL_REGISTRO_DOLOR_DIAGNOSTICO, COL_REGISTRO_GASES_DIAGNOSTICO, COL_REGISTRO_FECHA, COL_REGISTRO_SEMANA, COL_REGISTRO_DIA, COL_REGISTRO_MES, COL_REGISTRO_AÑO}, null, null, null, null, null);
    }

    public Cursor obtenerPuntuaciones(){
        return bd.query(TAB_REGISTROS, new String[]{COL_REGISTRO_PUNTUACION}, null, null, null, null, null);
    }
}
