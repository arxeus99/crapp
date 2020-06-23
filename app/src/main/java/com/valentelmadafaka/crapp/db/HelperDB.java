package com.valentelmadafaka.crapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.valentelmadafaka.crapp.db.CrappDB.CREATE_TABLE_REGISTRO;
import static com.valentelmadafaka.crapp.db.CrappDB.NOMBRE_DB;
import static com.valentelmadafaka.crapp.db.CrappDB.TAB_REGISTROS;
import static com.valentelmadafaka.crapp.db.CrappDB.VERSION_DB;

public class HelperDB extends SQLiteOpenHelper {

    HelperDB(Context con){ super(con, NOMBRE_DB, null, VERSION_DB);}

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_REGISTRO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w(CrappDB.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TAB_REGISTROS);
        onCreate(db);
    }
}
