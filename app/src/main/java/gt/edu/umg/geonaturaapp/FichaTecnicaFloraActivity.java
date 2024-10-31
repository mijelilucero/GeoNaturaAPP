package gt.edu.umg.geonaturaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

import gt.edu.umg.geonaturaapp.DataBase.Model.Flora;
import gt.edu.umg.geonaturaapp.DataBase.Model.Usuario;
import gt.edu.umg.geonaturaapp.DataBase.Service.FloraService;
import gt.edu.umg.geonaturaapp.DataBase.Service.UsuarioService;

public class FichaTecnicaFloraActivity extends AppCompatActivity {

    private Button btnGuardarRegistro;
    private ImageButton btnMenuPrincipal;
    private Spinner spinnerEstadoConservacion;
    private EditText txtNombreComun, txtNombreCientifico, txtTipoPlanta, txtAltura, txtFruto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ficha_tecnica_flora);

        btnGuardarRegistro = findViewById(R.id.btnGuardarRegistro);
        btnMenuPrincipal = findViewById(R.id.btnMenuPrincipal);
        txtNombreComun = findViewById(R.id.txtNombreComun);
        txtNombreCientifico = findViewById(R.id.txtNombreCientifico);
        txtTipoPlanta = findViewById(R.id.txtTipoPlanta);
        txtAltura = findViewById(R.id.txtAltura);
        txtFruto= findViewById(R.id.txtFruto);
        spinnerEstadoConservacion= findViewById(R.id.spinnerEstadoConservacion);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinnerEstadoConservacion, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstadoConservacion.setAdapter(adapter);

        spinnerEstadoConservacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    ((TextView) view).setTextColor(Color.GRAY);
                } else {
                    ((TextView) view).setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnGuardarRegistro.setOnClickListener(v -> {

            if(txtNombreComun.getText().toString().isEmpty() || txtNombreCientifico.getText().toString().isEmpty() || txtTipoPlanta.getText().toString().isEmpty() ||
                    txtAltura.getText().toString().isEmpty() || txtFruto.getText().toString().isEmpty() || spinnerEstadoConservacion.getSelectedItem().toString().equals("Elige uno")){
                Toast.makeText(this, "Rellena todos los campos...", Toast.LENGTH_SHORT).show();
            }else{

                try {
                    double altura = Double.parseDouble(txtAltura.getText().toString());

                    Log.d("Registro", "Mijeli: sacando datos de sharedpreferences");
                    SharedPreferences sharedPreferences = getSharedPreferences("MiAppPreferences", MODE_PRIVATE);
                    float latitudFloat = sharedPreferences.getFloat("latitud", 0.00f);
                    float longitudFloat = sharedPreferences.getFloat("longitud", 0.00f);

                    double latitud = (double) latitudFloat;
                    double longitud = (double) longitudFloat;

                    Log.d("Registro", "Mijeli: latitud y longitud: "+latitud+longitud);

                    int idUsuario = sharedPreferences.getInt("idUsuario", -1);

                    Log.d("Registro", "Mijeli: Latitud:"+latitud);
                    Log.d("Registro", "Mijeli: Longitud:"+longitud);
                    Log.d("Registro", "Mijeli: IdUser:"+idUsuario);

                    String imagenBase64 = sharedPreferences.getString("imagen_key", null);
                    byte[] imagenBytes = null;

                    if (imagenBase64 != null) {
                        try {
                            imagenBytes = Base64.decode(imagenBase64, Base64.DEFAULT);
                            Log.d("Registro", "Mijeli: Imagen decodificada correctamente, longitud de bytes: " + imagenBytes.length);
                        } catch (IllegalArgumentException e) {
                            Log.e("Registro", "Mijeli:Error al decodificar la imagen Base64: " + e.getMessage());
                        }
                    } else {
                        Log.w("Registro", "Mijeli: No se encontró imagen en SharedPreferences.");
                    }

                    String fechaHora = obtenerFechaHoraActual();
                    Log.d("Registro", "Mijeli: fecha y hora: "+fechaHora);

                    Flora floraDatos = new Flora();
                    floraDatos.setNombreComun(txtNombreComun.getText().toString());
                    floraDatos.setNombreCientifico(txtNombreCientifico.getText().toString());
                    floraDatos.setTipoPlanta(txtTipoPlanta.getText().toString());
                    floraDatos.setAltura(Double.parseDouble(txtAltura.getText().toString()));
                    floraDatos.setFruto(txtFruto.getText().toString());
                    floraDatos.setEstadoConservacion(spinnerEstadoConservacion.getSelectedItem().toString());
                    floraDatos.setLatitud(latitud);
                    floraDatos.setLongitud(longitud);
                    floraDatos.setImagen(imagenBytes);
                    floraDatos.setFechaHora(fechaHora);
                    floraDatos.setIdUser(idUsuario);

                    guardarregistroFlora(floraDatos);
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Ingresa un valor numérico válido en altura.", Toast.LENGTH_SHORT).show();
                }
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

    private void guardarregistroFlora(Flora flora) {
        FloraService floraService = new FloraService(this);
        Log.d("Registro", "Mijeli: Intentando guardar el registro...");

        try {
            floraService.addFlora(flora);
            Log.d("Registro", "Mijeli: Registro guardado en la base de datos");

            Toast.makeText(this, "Registro guardado exitosamente...", Toast.LENGTH_SHORT).show();

            SharedPreferences sharedPreferences = getSharedPreferences("MiAppPreferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("latitud");
            editor.remove("longitud");
            editor.remove("imagen_key");
            editor.apply();

            Intent intent = new Intent(this, MenuPrincipalActivity.class);
            startActivity(intent);
            Log.d("Registro", "Mijeli: Cambio de actividad a MenuPrincipalActivity");
        } catch (Exception e) {
            Toast.makeText(this, "Error al guardar el registro: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("Registro", "Mijeli: Error al guardar el registro:", e);
        }
    }

    private String obtenerFechaHoraActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(new Date());
    }
}