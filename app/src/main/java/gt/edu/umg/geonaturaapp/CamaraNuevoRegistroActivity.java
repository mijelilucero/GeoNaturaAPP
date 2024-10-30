package gt.edu.umg.geonaturaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CamaraNuevoRegistroActivity extends AppCompatActivity {

    private Button btnTomarFotografia, btnSiguientePasoUbicacion;
    private ImageButton btnMenuPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_camara_nuevo_registro);

        btnTomarFotografia = findViewById(R.id.btnTomarFotografia);
        btnSiguientePasoUbicacion = findViewById(R.id.btnSiguientePasoUbicacion);
        btnMenuPrincipal = findViewById(R.id.btnMenuPrincipal);

        btnTomarFotografia.setOnClickListener(v -> {
            Toast.makeText(this, "Tomando fotografía...", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this, CamaraNuevoRegistro.class);
//            startActivity(intent);
        });

        btnSiguientePasoUbicacion.setOnClickListener(v -> {
            Toast.makeText(this, "Obteniendo ubicación...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, UbicacionNuevoRegistroActivity.class);
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