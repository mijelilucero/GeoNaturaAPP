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

public class SeleccionarVerRegistros extends AppCompatActivity {

    Button btnFlora, btnFauna, btnCaracEntorno;
    private ImageButton btnMenuPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seleccionar_ver_registros);

        btnFlora = findViewById(R.id.btnFlora);
        btnFauna = findViewById(R.id.btnFauna);
        btnCaracEntorno = findViewById(R.id.btnCaracEntorno);
        btnMenuPrincipal = findViewById(R.id.btnMenuPrincipal);

        btnFlora.setOnClickListener(v -> {
            Toast.makeText(this, "Abriendo flora...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, FichaTecnicaFloraActivity.class);
            startActivity(intent);
        });

        btnFauna.setOnClickListener(v -> {
            Toast.makeText(this, "Abriendo fauna...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, FichaTecnicaFaunaActivity.class);
            startActivity(intent);
        });

        btnCaracEntorno.setOnClickListener(v -> {
            Toast.makeText(this, "Abriendo características del entorno...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, FichaTecnicaCaracEntornoActivity.class);
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