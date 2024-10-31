package gt.edu.umg.geonaturaapp;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CamaraNuevoRegistroActivity extends AppCompatActivity {

    private Button btnTomarFotografia, btnSiguientePasoUbicacion;
    private ImageButton btnMenuPrincipal;
    private ImageView imageViewVerFoto;
    private static final int REQUEST_CODE_CAMARA_PERMISSION = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private String rutaImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_camara_nuevo_registro);

        btnTomarFotografia = findViewById(R.id.btnTomarFotografia);
        btnSiguientePasoUbicacion = findViewById(R.id.btnSiguientePasoUbicacion);
        imageViewVerFoto = findViewById(R.id.imgVerFoto);
        btnMenuPrincipal = findViewById(R.id.btnMenuPrincipal);

        btnTomarFotografia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermissions();
            }
        });

        btnSiguientePasoUbicacion.setOnClickListener(v -> {
            if(imageViewVerFoto.getDrawable() != null){
                Toast.makeText(this, "Obteniendo ubicación...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, UbicacionNuevoRegistroActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Presiona el boton para tomar la foto...", Toast.LENGTH_SHORT).show();
            }
        });

        btnMenuPrincipal.setOnClickListener(v -> {
            Toast.makeText(this, "Termina de registrar el nuevo recurso...", Toast.LENGTH_SHORT).show();
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.CAMERA},
                    REQUEST_CODE_CAMARA_PERMISSION
            );
            return;
        }else{
            abrirCamara();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_CAMARA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                abrirCamara();
            } else {
                Toast.makeText(this, "Permiso de cámara denegado.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {

            File imagenArchivo = null;

            try{
                imagenArchivo = crearImagen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(imagenArchivo != null){
                Uri fotoUri = FileProvider.getUriForFile(this, "gt.edu.umg.geonaturaapp.fileprovider", imagenArchivo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {
                Bitmap imgBitmap = reducirTamañoBitmap(rutaImagen, 1024, 1024); // Tamaño máximo deseado
                imgBitmap = ajustarOrientacion(imgBitmap, rutaImagen);
                imageViewVerFoto.setImageBitmap(imgBitmap);

                guardarImagenEnSharedPreferences(imgBitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al procesar la imagen.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No se pudo capturar la imagen.", Toast.LENGTH_SHORT).show();
        }
    }

    private File crearImagen() throws IOException {
        String nombreImagen = "foto_";
        File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile(nombreImagen, ".jpg", directorio);

        rutaImagen = imagen.getAbsolutePath();
        return imagen;
    }

    private Bitmap ajustarOrientacion(Bitmap imgBitmap, String rutaImagen) throws IOException {
        ExifInterface exif = new ExifInterface(rutaImagen);
        int orientacion = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

        Matrix matrix = new Matrix();
        switch (orientacion) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.postRotate(90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.postRotate(180);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.postRotate(270);
                break;
            default:
                return imgBitmap;
        }

        return Bitmap.createBitmap(imgBitmap, 0, 0, imgBitmap.getWidth(), imgBitmap.getHeight(), matrix, true);
    }

    private Bitmap reducirTamañoBitmap(String rutaImagen, int maxAncho, int maxAlto) throws IOException {
        // Obtener las dimensiones de la imagen original
        BitmapFactory.Options opciones = new BitmapFactory.Options();
        opciones.inJustDecodeBounds = true; // Solo obtiene las dimensiones
        BitmapFactory.decodeFile(rutaImagen, opciones);

        // Calcular la escala
        int ancho = opciones.outWidth;
        int alto = opciones.outHeight;
        int inSampleSize = 1;

        if (alto > maxAlto || ancho > maxAncho) {
            int mitadAlto = alto / 2;
            int mitadAncho = ancho / 2;

            // Calcula la escala
            while ((mitadAlto / inSampleSize) >= maxAlto && (mitadAncho / inSampleSize) >= maxAncho) {
                inSampleSize *= 2;
            }
        }

        opciones.inSampleSize = inSampleSize;
        opciones.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(rutaImagen, opciones);
    }

    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, stream);
        return stream.toByteArray();
    }

    private void guardarImagenEnSharedPreferences(Bitmap bitmap) {
        byte[] byteArray = bitmapToByteArray(bitmap);
        String imagenBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

        SharedPreferences sharedPreferences = getSharedPreferences("MiAppPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("imagen_key", imagenBase64);
        editor.apply();

        Log.d("Registro", "Mijeli: imagen guardada, longitud: " + imagenBase64.length());
    }
}