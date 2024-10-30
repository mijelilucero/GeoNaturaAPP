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