package gt.edu.umg.geonaturaapp.DataBase.Conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "GeoNatura.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USUARIOS = "tb_usuarios";
    public static final String TABLE_FLORA = "tb_flora";
    public static final String TABLE_FAUNA = "tb_fauna";
    public static final String TABLE_CARACTERISTICASENTORNO = "tb_caracteristicasEntorno";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USUARIOS + " (" +
                "idUser INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, " +
                "apellido TEXT, " +
                "nombre_usuario TEXT, " +
                "contrasenia TEXT " +
                ");");

        db.execSQL("CREATE TABLE " + TABLE_FLORA + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "latitud REAL, " +
                "longitud REAL, " +
                "imagen BLOB, " +
                "nombre_comun TEXT NOT NULL, " +
                "nombre_cientifico TEXT, " +
                "tipo_planta TEXT, " +
                "altura REAL, " +
                "fruto TEXT, " +
                "estado_concervacion TEXT, " +
                "fecha_hora TEXT," +
                "id_user INTEGER" +
                ");");

        db.execSQL("CREATE TABLE " + TABLE_FAUNA + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "latitud REAL, " +
                "longitud REAL, " +
                "imagen BLOB, " +
                "nombre TEXT NOT NULL, " +
                "clasificacion TEXT, " +
                "tamanio REAL, " +
                "peso REAL, " +
                "habitat TEXT, " +
                "estado_conservacion TEXT, " +
                "fecha_hora TEXT," +
                "id_user INTEGER" +
                ");");

        db.execSQL("CREATE TABLE " + TABLE_CARACTERISTICASENTORNO + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "latitud REAL, " +
                "longitud REAL, " +
                "imagen BLOB, " +
                "nombre TEXT NOT NULL, " +
                "ecosistema TEXT, " +
                "temperatura REAL, " +
                "altitud REAL, " +
                "presencia_agua TEXT, " +
                "densidad_vegetal TEXT, " +
                "fecha_hora TEXT," +
                "id_user INTEGER" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLORA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAUNA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARACTERISTICASENTORNO);
        onCreate(db);
    }
}
