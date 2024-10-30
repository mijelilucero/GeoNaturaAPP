package gt.edu.umg.geonaturaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SigninActivity extends AppCompatActivity {


    private ImageButton btnVolverInicioSesion;
    private Button btnCrearNuevaCuenta;
    private EditText txtRegistroNombre, txtRegistroApellido, txtRegistroUser, txtRegistroPassword, txtRegistroPasswordConfirm;
    private String nuevoNombre, nuevoApellido, nuevoUser, nuevoPassword, nuevoPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signin);

        btnVolverInicioSesion = findViewById(R.id.btnVolverInicioSesion);
        btnCrearNuevaCuenta = findViewById(R.id.btnCrearNuevaCuenta);
        txtRegistroNombre = findViewById(R.id.txtRegistroNombre);
        txtRegistroApellido = findViewById(R.id.txtRegistroApellido);
        txtRegistroUser = findViewById(R.id.txtRegistroUser);
        txtRegistroPassword = findViewById(R.id.txtRegistroPassword);
        txtRegistroPasswordConfirm = findViewById(R.id.txtRegistroPasswordConfirm);

        nuevoNombre = txtRegistroNombre.getText().toString();
        nuevoApellido = txtRegistroApellido.getText().toString();
        nuevoUser = txtRegistroUser.getText().toString();
        nuevoPassword = txtRegistroPassword.getText().toString();
        nuevoPasswordConfirm = txtRegistroPasswordConfirm.getText().toString();

        btnVolverInicioSesion.setOnClickListener(v -> {
            Toast.makeText(this, "Volviendo a inicio de sesión...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        btnCrearNuevaCuenta.setOnClickListener(v -> {
            Toast.makeText(this, "Creando nueva cuenta...", Toast.LENGTH_SHORT).show();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}