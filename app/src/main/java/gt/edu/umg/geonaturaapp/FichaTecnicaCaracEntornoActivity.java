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

import java.text.SimpleDateFormat;
import java.util.Date;

import gt.edu.umg.geonaturaapp.DataBase.Model.CaracteristicaEntorno;
import gt.edu.umg.geonaturaapp.DataBase.Model.Flora;
import gt.edu.umg.geonaturaapp.DataBase.Service.CaracteristicaEntornoService;
import gt.edu.umg.geonaturaapp.DataBase.Service.FloraService;

public class FichaTecnicaCaracEntornoActivity extends AppCompatActivity {

    private Button btnGuardarRegistro;
    private ImageButton btnMenuPrincipal;
    private Spinner spinnerPresenciaAgua, spinnerDensidadVegetal;
    private EditText txtNombre, txtEcosistema, txtTemperatura, txtAltitud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ficha_tecnica_carac_entorno);

        btnGuardarRegistro = findViewById(R.id.btnGuardarRegistro);
        btnMenuPrincipal = findViewById(R.id.btnMenuPrincipal);
        txtNombre = findViewById(R.id.txtNombre);
        txtEcosistema = findViewById(R.id.txtEcosistema);
        txtTemperatura = findViewById(R.id.txtTemperatura);
        txtAltitud = findViewById(R.id.txtAltitud);
        spinnerPresenciaAgua= findViewById(R.id.spinnerPresenciaAgua);
        spinnerDensidadVegetal= findViewById(R.id.spinnerDensidadVegetal);

        ArrayAdapter<CharSequence> adapterPresenciaAgua = ArrayAdapter.createFromResource(this,
                R.array.spinnerPresenciaAgua, android.R.layout.simple_spinner_item);
        adapterPresenciaAgua.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPresenciaAgua.setAdapter(adapterPresenciaAgua);

        spinnerPresenciaAgua.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    ((TextView) view).setTextColor(Color.GRAY);
                } else {
                    // Lógica para manejar las selecciones válidas
                    ((TextView) view).setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapterDensidadVegetal = ArrayAdapter.createFromResource(this,
                R.array.spinnerDensidadVegetal, android.R.layout.simple_spinner_item);
        adapterDensidadVegetal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDensidadVegetal.setAdapter(adapterDensidadVegetal);

        spinnerDensidadVegetal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    ((TextView) view).setTextColor(Color.GRAY);
                } else {
                    // Lógica para manejar las selecciones válidas
                    ((TextView) view).setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnGuardarRegistro.setOnClickListener(v -> {

            if(txtNombre.getText().toString().isEmpty() || txtEcosistema.getText().toString().isEmpty() || txtTemperatura.getText().toString().isEmpty() ||
                    txtAltitud.getText().toString().isEmpty() || spinnerPresenciaAgua.getSelectedItem().toString().equals("Elige uno") || spinnerDensidadVegetal.getSelectedItem().toString().equals("Elige uno")){
                Toast.makeText(this, "Rellena todos los campos...", Toast.LENGTH_SHORT).show();
            }else{
                try{
                    double temperatura = Double.parseDouble(txtTemperatura.getText().toString());
                    double altitud = Double.parseDouble(txtAltitud.getText().toString());

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


                    CaracteristicaEntorno caracteristicaEntornoDatos = new CaracteristicaEntorno();
                    caracteristicaEntornoDatos.setNombre(txtNombre.getText().toString());
                    caracteristicaEntornoDatos.setEcosistema(txtEcosistema.getText().toString());
                    caracteristicaEntornoDatos.setTemperatura(Double.parseDouble(txtTemperatura.getText().toString()));
                    caracteristicaEntornoDatos.setAltitud(Double.parseDouble(txtAltitud.getText().toString()));
                    caracteristicaEntornoDatos.setPresenciaAgua(spinnerPresenciaAgua.getSelectedItem().toString());
                    caracteristicaEntornoDatos.setDensidadVegetal(spinnerDensidadVegetal.getSelectedItem().toString());
                    caracteristicaEntornoDatos.setLatitud(latitud);
                    caracteristicaEntornoDatos.setLongitud(longitud);
                    caracteristicaEntornoDatos.setImagen(imagenBytes);
                    caracteristicaEntornoDatos.setFechaHora(fechaHora);
                    caracteristicaEntornoDatos.setIdUser(idUsuario);

                    guardarregistroCaracteristicasEntorno(caracteristicaEntornoDatos);
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Ingresa un valor numérico válido en temperatura y altitud.", Toast.LENGTH_SHORT).show();
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

    private void guardarregistroCaracteristicasEntorno(CaracteristicaEntorno caracteristicaEntorno) {
        CaracteristicaEntornoService caracteristicaEntornoService = new CaracteristicaEntornoService(this);
        Log.d("Registro", "Mijeli: Intentando guardar el registro...");

        try {
            caracteristicaEntornoService.addCaracteristicaEntorno(caracteristicaEntorno);
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