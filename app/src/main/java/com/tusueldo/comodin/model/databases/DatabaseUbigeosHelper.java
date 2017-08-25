package com.tusueldo.comodin.model.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.tusueldo.comodin.model.Ubigeo;
import com.tusueldo.comodin.model.databases.UbigeoContract.UbigeoEntry;
import com.tusueldo.comodin.utils.ComodinUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class DatabaseUbigeosHelper extends SQLiteOpenHelper {

    private static String DATABASE_PATH = "/data/data/com.tusueldo.comodin/databases/";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "usuarios.db";

    private SQLiteDatabase sqLiteDatabase;

    private final Context context;

    public DatabaseUbigeosHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        createDataBase();
        openDataBase();
    }

    private void createDataBase() {

        boolean dbExist = checkDataBase();
        if (!dbExist) {
            this.getReadableDatabase();
            copyDataBase();
        }
    }

    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;
        try {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        if (checkDB != null) {
            checkDB.close();
        }

        return checkDB != null;
    }

    private void copyDataBase() {
        InputStream myInput;
        try {
            myInput = context.getAssets().open(DATABASE_NAME);
            String outFileName = DATABASE_PATH + DATABASE_NAME;
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDataBase() throws SQLException {
        String path = DATABASE_PATH + DATABASE_NAME;
        sqLiteDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if (sqLiteDatabase != null)
            sqLiteDatabase.close();
        super.close();
    }


    public List<Ubigeo> getAll() {
        sqLiteDatabase = this.getReadableDatabase();
        List<Ubigeo> ubigeos = new ArrayList<>();
        String[] projection = {UbigeoEntry.COD_UBIGEO, UbigeoEntry.DEPARTAMENTO, UbigeoEntry.PROVINCIA, UbigeoEntry.DISTRITO};
        Cursor c = null;
        try {
            c = sqLiteDatabase.query(UbigeoEntry.TABLE_NAME, projection, null, null, null, null, null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        String cod_ubigeo = c.getString(c.getColumnIndex(UbigeoEntry.COD_UBIGEO));
                        String departamento = c.getString(c.getColumnIndex(UbigeoEntry.DEPARTAMENTO));
                        String provincia = c.getString(c.getColumnIndex(UbigeoEntry.PROVINCIA));
                        String distrito = c.getString(c.getColumnIndex(UbigeoEntry.DISTRITO));
                        Ubigeo ubigeo = new Ubigeo(cod_ubigeo, departamento, provincia, distrito);
                        ubigeos.add(ubigeo);
                    } while (c.moveToNext());
                }
            }
        } catch (Exception e) {
            if (c != null)
                c.close();
            sqLiteDatabase.close();
        }

        return ubigeos;
    }

    public List<String> getDistritos() {

        List<Ubigeo> ubigeos = getAll();
        List<String> distritos = new ArrayList<>();
        for (Ubigeo u : ubigeos) {
            distritos.add(ComodinUtils.formatDistrito(u.getDistrito(), u.getProvincia()));
        }
        return distritos;

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
