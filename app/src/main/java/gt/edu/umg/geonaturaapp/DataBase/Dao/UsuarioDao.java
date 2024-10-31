package gt.edu.umg.geonaturaapp.DataBase.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import gt.edu.umg.geonaturaapp.DataBase.Conexion.DBHelper;
import gt.edu.umg.geonaturaapp.DataBase.Model.Usuario;

public class UsuarioDao {

    private DBHelper dbHelper;

    public UsuarioDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void insertUsuario(Usuario usuario) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("nombre", usuario.getNombre());
            values.put("apellido", usuario.getApellido());
            values.put("nombre_usuario", usuario.getNombreUsuario());
            values.put("contrasenia", usuario.getContrasenia());

            database.insert(DBHelper.TABLE_USUARIOS, null, values);
        } finally {
            database.close();
        }
    }

    public Usuario getUsuarioByNombreUsuario(String nombreUsuario) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Usuario usuario = null;
        try {
            String query = "SELECT * FROM tb_usuarios WHERE nombre_usuario = ?";
            String[] selectionArgs = {nombreUsuario};
            try (Cursor cursor = database.rawQuery(query, selectionArgs)) {
                if (cursor.moveToFirst()) {
                    usuario = new Usuario();
                    usuario.setIdUser(cursor.getInt(cursor.getColumnIndexOrThrow("idUser")));
                    usuario.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
                    usuario.setApellido(cursor.getString(cursor.getColumnIndexOrThrow("apellido")));
                    usuario.setNombreUsuario(cursor.getString(cursor.getColumnIndexOrThrow("nombre_usuario")));
                    usuario.setContrasenia(cursor.getString(cursor.getColumnIndexOrThrow("contrasenia")));
                }
            }
        } finally {
            database.close();
        }
        return usuario;
    }
}
