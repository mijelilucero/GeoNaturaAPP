package gt.edu.umg.geonaturaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UbicacionNuevoRegistroActivity extends AppCompatActivity {

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
//            Intent intent = new Intent(this, CamaraNuevoRegistro.class);
//            startActivity(intent);
        });

        btnSiguientePasoElegirRecurso.setOnClickListener(v -> {
            Toast.makeText(this, "Abriendo elección de recurso...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SeleccionarNuevoRegistroActivity.class);
            startActivity(intent);
        });

        btnMenuPrincipal.setOnClickListener(v -> {
            Toast.makeText(this, "Volviendo al menu principal...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MenuPrincipalActivity.class);
            startActivity(intent);
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}