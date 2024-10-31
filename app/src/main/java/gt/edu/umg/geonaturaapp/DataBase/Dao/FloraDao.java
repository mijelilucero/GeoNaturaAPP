package gt.edu.umg.geonaturaapp.DataBase.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gt.edu.umg.geonaturaapp.DataBase.Conexion.DBHelper;
import gt.edu.umg.geonaturaapp.DataBase.Model.Flora;

public class FloraDao {

    private DBHelper dbHelper;

    public FloraDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void insertFlora(Flora flora) {
        try (SQLiteDatabase database = dbHelper.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put("latitud", flora.getLatitud());
            values.put("longitud", flora.getLongitud());
            values.put("imagen", flora.getImagen()); // byte[]
            values.put("nombre_comun", flora.getNombreComun());
            values.put("nombre_cientifico", flora.getNombreCientifico());
            values.put("tipo_planta", flora.getTipoPlanta());
            values.put("altura", flora.getAltura());
            values.put("fruto", flora.getFruto());
            values.put("estado_concervacion", flora.getEstadoConservacion());
            values.put("fecha_hora", flora.getFechaHora());
            values.put("id_user", flora.getIdUser());

            database.insert(DBHelper.TABLE_FLORA, null, values);
        }
    }

    public void updateFlora(Flora flora) {
        try (SQLiteDatabase database = dbHelper.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put("latitud", flora.getLatitud());
            values.put("longitud", flora.getLongitud());
            values.put("imagen", flora.getImagen()); // byte[]
            values.put("nombre_comun", flora.getNombreComun());
            values.put("nombre_cientifico", flora.getNombreCientifico());
            values.put("tipo_planta", flora.getTipoPlanta());
            values.put("altura", flora.getAltura());
            values.put("fruto", flora.getFruto());
            values.put("estado_concervacion", flora.getEstadoConservacion());
            values.put("fecha_hora", flora.getFechaHora());
            values.put("id_user", flora.getIdUser());

            String whereClause = "id = ?";
            String[] whereArgs = {String.valueOf(flora.getId())};

            database.update(DBHelper.TABLE_FLORA, values, whereClause, whereArgs);
        }
    }

    public void deleteFloraById(int id) {
        try (SQLiteDatabase database = dbHelper.getWritableDatabase()) {
            String whereClause = "id = ?";
            String[] whereArgs = {String.valueOf(id)};
            database.delete(DBHelper.TABLE_FLORA, whereClause, whereArgs);
        }
    }

    public Flora getFloraById(int id) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Flora flora = null;
        try {
            String query = "SELECT * FROM tb_flora WHERE id = ?";
            String[] selectionArgs = {String.valueOf(id)};
            try (Cursor cursor = database.rawQuery(query, selectionArgs)) {
                if (cursor.moveToFirst()) {
                    flora = new Flora();
                    flora.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    flora.setLatitud(cursor.getDouble(cursor.getColumnIndexOrThrow("latitud")));
                    flora.setLongitud(cursor.getDouble(cursor.getColumnIndexOrThrow("longitud")));
                    flora.setImagen(cursor.getBlob(cursor.getColumnIndexOrThrow("imagen")));
                    flora.setNombreComun(cursor.getString(cursor.getColumnIndexOrThrow("nombre_comun")));
                    flora.setNombreCientifico(cursor.getString(cursor.getColumnIndexOrThrow("nombre_cientifico")));
                    flora.setTipoPlanta(cursor.getString(cursor.getColumnIndexOrThrow("tipo_planta")));
                    flora.setAltura(cursor.getDouble(cursor.getColumnIndexOrThrow("altura")));
                    flora.setFruto(cursor.getString(cursor.getColumnIndexOrThrow("fruto")));
                    flora.setEstadoConservacion(cursor.getString(cursor.getColumnIndexOrThrow("estado_concervacion")));
                    flora.setFechaHora(cursor.getString(cursor.getColumnIndexOrThrow("fecha_hora")));
                    flora.setIdUser(cursor.getInt(cursor.getColumnIndexOrThrow("id_user")));
                }
            }
        } finally {
            database.close();
        }
        return flora;
    }

    public List<Flora> getAllFloraByIdUser(int idUser) {
        List<Flora> floraList = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM tb_flora WHERE id_user = ?";
        String[] selectionArgs = {String.valueOf(idUser)};

        try (Cursor cursor = database.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    Flora flora = new Flora();
                    flora.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    flora.setLatitud(cursor.getDouble(cursor.getColumnIndexOrThrow("latitud")));
                    flora.setLongitud(cursor.getDouble(cursor.getColumnIndexOrThrow("longitud")));
                    flora.setImagen(cursor.getBlob(cursor.getColumnIndexOrThrow("imagen")));
                    flora.setNombreComun(cursor.getString(cursor.getColumnIndexOrThrow("nombre_comun")));
                    flora.setNombreCientifico(cursor.getString(cursor.getColumnIndexOrThrow("nombre_cientifico")));
                    flora.setTipoPlanta(cursor.getString(cursor.getColumnIndexOrThrow("tipo_planta")));
                    flora.setAltura(cursor.getDouble(cursor.getColumnIndexOrThrow("altura")));
                    flora.setFruto(cursor.getString(cursor.getColumnIndexOrThrow("fruto")));
                    flora.setEstadoConservacion(cursor.getString(cursor.getColumnIndexOrThrow("estado_concervacion")));
                    flora.setFechaHora(cursor.getString(cursor.getColumnIndexOrThrow("fecha_hora")));
                    flora.setIdUser(cursor.getInt(cursor.getColumnIndexOrThrow("id_user")));

                    floraList.add(flora);
                } while (cursor.moveToNext());
            }
        } finally {
            database.close();
        }

        return floraList;
    }
}
