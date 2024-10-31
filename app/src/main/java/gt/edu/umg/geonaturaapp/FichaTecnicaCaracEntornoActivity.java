package gt.edu.umg.geonaturaapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
        spinnerPresenciaAgua.setAdapter(adapterDensidadVegetal);

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
            Toast.makeText(this, "Guardando registro...", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this, CamaraNuevoRegistro.class);
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