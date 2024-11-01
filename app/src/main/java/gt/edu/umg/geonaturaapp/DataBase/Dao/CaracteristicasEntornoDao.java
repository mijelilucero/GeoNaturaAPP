package gt.edu.umg.geonaturaapp.DataBase.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import gt.edu.umg.geonaturaapp.DataBase.Conexion.DBHelper;
import gt.edu.umg.geonaturaapp.DataBase.Model.CaracteristicaEntorno;

public class CaracteristicasEntornoDao {

    private DBHelper dbHelper;

    public CaracteristicasEntornoDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void insertCaracteristicaEntorno(CaracteristicaEntorno caracteristicaEntorno) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("latitud", caracteristicaEntorno.getLatitud());
            values.put("longitud", caracteristicaEntorno.getLongitud());
            values.put("imagen", caracteristicaEntorno.getImagen()); // byte[]
            values.put("nombre", caracteristicaEntorno.getNombre());
            values.put("ecosistema", caracteristicaEntorno.getEcosistema());
            values.put("temperatura", caracteristicaEntorno.getTemperatura());
            values.put("altitud", caracteristicaEntorno.getAltitud());
            values.put("presencia_agua", caracteristicaEntorno.getPresenciaAgua());
            values.put("densidad_vegetal", caracteristicaEntorno.getDensidadVegetal());
            values.put("fecha_hora", caracteristicaEntorno.getFechaHora());
            values.put("id_user", caracteristicaEntorno.getIdUser());

            database.insert(DBHelper.TABLE_CARACTERISTICASENTORNO, null, values);
        } finally {
            database.close();
        }
    }

    public void updateCaracteristicaEntorno(CaracteristicaEntorno caracteristicaEntorno) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("latitud", caracteristicaEntorno.getLatitud());
            values.put("longitud", caracteristicaEntorno.getLongitud());
            values.put("imagen", caracteristicaEntorno.getImagen()); // byte[]
            values.put("nombre", caracteristicaEntorno.getNombre());
            values.put("ecosistema", caracteristicaEntorno.getEcosistema());
            values.put("temperatura", caracteristicaEntorno.getTemperatura());
            values.put("altitud", caracteristicaEntorno.getAltitud());
            values.put("presencia_agua", caracteristicaEntorno.getPresenciaAgua());
            values.put("densidad_vegetal", caracteristicaEntorno.getDensidadVegetal());
            values.put("fecha_hora", caracteristicaEntorno.getFechaHora());
            values.put("id_user", caracteristicaEntorno.getIdUser());

            String whereClause = "id = ?";
            String[] whereArgs = {String.valueOf(caracteristicaEntorno.getId())};

            database.update(DBHelper.TABLE_CARACTERISTICASENTORNO, values, whereClause, whereArgs);
        } finally {
            database.close();
        }
    }

    public void deleteCaracteristicaEntornoById(int id) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        try {
            String whereClause = "id = ?";
            String[] whereArgs = {String.valueOf(id)};
            database.delete(DBHelper.TABLE_CARACTERISTICASENTORNO, whereClause, whereArgs);
        } finally {
            database.close();
        }
    }

    public CaracteristicaEntorno getCaracteristicaEntornoById(int id) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        CaracteristicaEntorno caracteristicaEntorno = null;
        try {
            String query = "SELECT * FROM tb_caracteristicasEntorno WHERE id = ?";
            String[] selectionArgs = {String.valueOf(id)};
            try (Cursor cursor = database.rawQuery(query, selectionArgs)) {
                if (cursor.moveToFirst()) {
                    caracteristicaEntorno = new CaracteristicaEntorno();
                    caracteristicaEntorno.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    caracteristicaEntorno.setLatitud(cursor.getDouble(cursor.getColumnIndexOrThrow("latitud")));
                    caracteristicaEntorno.setLongitud(cursor.getDouble(cursor.getColumnIndexOrThrow("longitud")));
                    caracteristicaEntorno.setImagen(cursor.getBlob(cursor.getColumnIndexOrThrow("imagen")));
                    caracteristicaEntorno.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
                    caracteristicaEntorno.setEcosistema(cursor.getString(cursor.getColumnIndexOrThrow("ecosistema")));
                    caracteristicaEntorno.setTemperatura(cursor.getDouble(cursor.getColumnIndexOrThrow("temperatura")));
                    caracteristicaEntorno.setAltitud(cursor.getDouble(cursor.getColumnIndexOrThrow("altitud")));
                    caracteristicaEntorno.setPresenciaAgua(cursor.getString(cursor.getColumnIndexOrThrow("presencia_agua")));
                    caracteristicaEntorno.setDensidadVegetal(cursor.getString(cursor.getColumnIndexOrThrow("densidad_vegetal")));
                    caracteristicaEntorno.setFechaHora(cursor.getString(cursor.getColumnIndexOrThrow("fecha_hora")));
                    caracteristicaEntorno.setIdUser(cursor.getInt(cursor.getColumnIndexOrThrow("id_user")));
                }
            }
        } finally {
            database.close();
        }
        return caracteristicaEntorno;
    }

    public ArrayList<CaracteristicaEntorno> getAllCaracteristicasEntornoByIdUser(int idUser) {
        ArrayList<CaracteristicaEntorno> caracteristicaEntornoList = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM tb_caracteristicasEntorno WHERE id_user = ?";
        String[] selectionArgs = {String.valueOf(idUser)};

        try (Cursor cursor = database.rawQuery(query, selectionArgs)) {
            if (cursor.moveToFirst()) {
                do {
                    CaracteristicaEntorno caracteristicaEntorno = new CaracteristicaEntorno();
                    caracteristicaEntorno.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    caracteristicaEntorno.setLatitud(cursor.getDouble(cursor.getColumnIndexOrThrow("latitud")));
                    caracteristicaEntorno.setLongitud(cursor.getDouble(cursor.getColumnIndexOrThrow("longitud")));
                    caracteristicaEntorno.setImagen(cursor.getBlob(cursor.getColumnIndexOrThrow("imagen")));
                    caracteristicaEntorno.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
                    caracteristicaEntorno.setEcosistema(cursor.getString(cursor.getColumnIndexOrThrow("ecosistema")));
                    caracteristicaEntorno.setTemperatura(cursor.getDouble(cursor.getColumnIndexOrThrow("temperatura")));
                    caracteristicaEntorno.setAltitud(cursor.getDouble(cursor.getColumnIndexOrThrow("altitud")));
                    caracteristicaEntorno.setPresenciaAgua(cursor.getString(cursor.getColumnIndexOrThrow("presencia_agua")));
                    caracteristicaEntorno.setDensidadVegetal(cursor.getString(cursor.getColumnIndexOrThrow("densidad_vegetal")));
                    caracteristicaEntorno.setFechaHora(cursor.getString(cursor.getColumnIndexOrThrow("fecha_hora")));
                    caracteristicaEntorno.setIdUser(cursor.getInt(cursor.getColumnIndexOrThrow("id_user")));

                    caracteristicaEntornoList.add(caracteristicaEntorno);
                } while (cursor.moveToNext());
            }
        } finally {
            database.close();
        }

        Log.d("FloraService", "Mijeli: Número de floras encontradas: " + caracteristicaEntornoList.size());
        return caracteristicaEntornoList;
    }

}
