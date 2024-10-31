package gt.edu.umg.geonaturaapp.DataBase.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import gt.edu.umg.geonaturaapp.DataBase.Conexion.DBHelper;
import gt.edu.umg.geonaturaapp.DataBase.Model.Fauna;

public class FaunaDao {

    private DBHelper dbHelper;

    public FaunaDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void insertFauna(Fauna fauna) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("latitud", fauna.getLatitud());
            values.put("longitud", fauna.getLongitud());
            values.put("imagen", fauna.getImagen()); // byte[]
            values.put("nombre", fauna.getNombre());
            values.put("clasificacion", fauna.getClasificacion());
            values.put("tamanio", fauna.getTamanio());
            values.put("peso", fauna.getPeso());
            values.put("habitat", fauna.getHabitat());
            values.put("estado_conservacion", fauna.getEstadoConservacion());
            values.put("fecha_hora", fauna.getFechaHora());
            values.put("id_user", fauna.getIdUser());

            database.insert(DBHelper.TABLE_FAUNA, null, values);
        } finally {
            database.close();
        }
    }

    public void updateFauna(Fauna fauna) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("latitud", fauna.getLatitud());
            values.put("longitud", fauna.getLongitud());
            values.put("imagen", fauna.getImagen()); // byte[]
            values.put("nombre", fauna.getNombre());
            values.put("clasificacion", fauna.getClasificacion());
            values.put("tamanio", fauna.getTamanio());
            values.put("peso", fauna.getPeso());
            values.put("habitat", fauna.getHabitat());
            values.put("estado_conservacion", fauna.getEstadoConservacion());
            values.put("fecha_hora", fauna.getFechaHora());
            values.put("id_user", fauna.getIdUser());

            String whereClause = "id = ?";
            String[] whereArgs = {String.valueOf(fauna.getId())};

            database.update(DBHelper.TABLE_FAUNA, values, whereClause, whereArgs);
        } finally {
            database.close();
        }
    }

    public void deleteFaunaById(int id) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        try {
            String whereClause = "id = ?";
            String[] whereArgs = {String.valueOf(id)};
            database.delete(DBHelper.TABLE_FAUNA, whereClause, whereArgs);
        } finally {
            database.close();
        }
    }

    public Fauna getFaunaById(int id) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Fauna fauna = null;
        try {
            String query = "SELECT * FROM tb_fauna WHERE id = ?";
            String[] selectionArgs = {String.valueOf(id)};
            try (Cursor cursor = database.rawQuery(query, selectionArgs)) {
                if (cursor.moveToFirst()) {
                    fauna = new Fauna();
                    fauna.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    fauna.setLatitud(cursor.getDouble(cursor.getColumnIndexOrThrow("latitud")));
                    fauna.setLongitud(cursor.getDouble(cursor.getColumnIndexOrThrow("longitud")));
                    fauna.setImagen(cursor.getBlob(cursor.getColumnIndexOrThrow("imagen")));
                    fauna.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
                    fauna.setClasificacion(cursor.getString(cursor.getColumnIndexOrThrow("clasificacion")));
                    fauna.setTamanio(cursor.getDouble(cursor.getColumnIndexOrThrow("tamanio")));
                    fauna.setPeso(cursor.getDouble(cursor.getColumnIndexOrThrow("peso")));
                    fauna.setHabitat(cursor.getString(cursor.getColumnIndexOrThrow("habitat")));
                    fauna.setEstadoConservacion(cursor.getString(cursor.getColumnIndexOrThrow("estado_conservacion")));
                    fauna.setFechaHora(cursor.getString(cursor.getColumnIndexOrThrow("fecha_hora")));
                    fauna.setIdUser(cursor.getInt(cursor.getColumnIndexOrThrow("id_user")));
                }
            }
        } finally {
            database.close();
        }
        return fauna;
    }

    public List<Fauna> getAllFaunaByIdUser(int idUser) {
        List<Fauna> faunaList = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM tb_fauna WHERE id_user = ?";
        String[] selectionArgs = {String.valueOf(idUser)};

        try (Cursor cursor = database.rawQuery(query, selectionArgs)) {
            if (cursor.moveToFirst()) {
                do {
                    Fauna fauna = new Fauna();
                    fauna.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                    fauna.setLatitud(cursor.getDouble(cursor.getColumnIndexOrThrow("latitud")));
                    fauna.setLongitud(cursor.getDouble(cursor.getColumnIndexOrThrow("longitud")));
                    fauna.setImagen(cursor.getBlob(cursor.getColumnIndexOrThrow("imagen")));
                    fauna.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
                    fauna.setClasificacion(cursor.getString(cursor.getColumnIndexOrThrow("clasificacion")));
                    fauna.setTamanio(cursor.getDouble(cursor.getColumnIndexOrThrow("tamanio")));
                    fauna.setPeso(cursor.getDouble(cursor.getColumnIndexOrThrow("peso")));
                    fauna.setHabitat(cursor.getString(cursor.getColumnIndexOrThrow("habitat")));
                    fauna.setEstadoConservacion(cursor.getString(cursor.getColumnIndexOrThrow("estado_conservacion")));
                    fauna.setFechaHora(cursor.getString(cursor.getColumnIndexOrThrow("fecha_hora")));
                    fauna.setIdUser(cursor.getInt(cursor.getColumnIndexOrThrow("id_user")));

                    faunaList.add(fauna);
                } while (cursor.moveToNext());
            }
        } finally {
            database.close();
        }

        return faunaList;
    }
}
