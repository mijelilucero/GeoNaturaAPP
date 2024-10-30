package gt.edu.umg.geonaturaapp;

import android.annotation.SuppressLint;
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

public class MenuPrincipalActivity extends AppCompatActivity {

    private TextView tvSaludo;
    private Button btnNuevoRegistro, btnVerRegistros;
    private ImageButton btnMenuPrincipal;
    private String nombre="Mijeli";



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_principal);

        btnNuevoRegistro = findViewById(R.id.btnNuevoRegistro);
        btnVerRegistros = findViewById(R.id.btnVerRegistros);
        btnMenuPrincipal = findViewById(R.id.btnMenuPrincipal);
        tvSaludo = findViewById(R.id.tvSaludo);

        tvSaludo.setText("¡Hola, "+nombre+"!");

        btnNuevoRegistro.setOnClickListener(v -> {
            Toast.makeText(this, "Abriendo nuevo registro...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, CamaraNuevoRegistroActivity.class);
            startActivity(intent);
        });

        btnVerRegistros.setOnClickListener(v -> {
            Toast.makeText(this, "Abriendo los registros...", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this, MenuPrincipalActivity.class);
//            startActivity(intent);
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