package gt.edu.umg.geonaturaapp;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class UbicacionNuevoRegistroActivity extends AppCompatActivity implements OnMapReadyCallback {

    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private GoogleMap mMap;
    private double latitud, longitud;

    private Button btnObtenerUbicacion, btnSiguientePasoElegirRecurso;
    private ImageButton btnMenuPrincipal;
    private TextView txtLatitud, txtLongitud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ubicacion_nuevo_registro);

        btnObtenerUbicacion = findViewById(R.id.btnObtenerUbicacion);
        btnSiguientePasoElegirRecurso = findViewById(R.id.btnSiguientePasoElegirRecurso);
        btnMenuPrincipal = findViewById(R.id.btnMenuPrincipal);
        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);

        btnObtenerUbicacion.setOnClickListener(v -> {
            Toast.makeText(this, "Obteniendo ubicacion...", Toast.LENGTH_SHORT).show();

            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            assert mapFragment != null;
            mapFragment.getMapAsync(this);

            getCurrentLocation();
        });

        btnSiguientePasoElegirRecurso.setOnClickListener(v -> {
            if(latitud!= 0 || longitud!= 0){
                Toast.makeText(this, "Abriendo elección de recurso...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, SeleccionarNuevoRegistroActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Presiona el boton para obtener ubicación...", Toast.LENGTH_SHORT).show();
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

    private void getCurrentLocation(){
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(
                    this, new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE_LOCATION_PERMISSION
            );
            return;
        }

        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, location -> {
            if(location != null){

                latitud = location.getLatitude();
                longitud = location.getLongitude();

                if (mMap != null) {
                    LatLng currentLocation = new LatLng(latitud, longitud);
                    mMap.addMarker(new MarkerOptions().position(currentLocation).title("Ubicación Actual"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));

                    txtLatitud.setText("Latitud: "+latitud);
                    txtLongitud.setText("Longitud: "+longitud);
                    guardarUbicacionEnSharedPreferences(latitud, longitud);
                }
            }else{
                Toast.makeText(this, "No se puedo obtener la ubicación.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int [] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED || grantResults[1] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }else{
                Toast.makeText(this, "Permiso de ubicación denegado.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
    }

    private void guardarUbicacionEnSharedPreferences(double latitud, double longitud) {

        SharedPreferences sharedPreferences = getSharedPreferences("MiAppPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putFloat("latitud", (float) latitud);
        editor.putFloat("longitud", (float) longitud);
        editor.apply();

        Log.d("Registro", "Mensaje: latitud: " + (float) latitud);
        Log.d("Registro", "Mensaje: longitud: " + (float) longitud);
    }
}