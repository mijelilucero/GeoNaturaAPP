package gt.edu.umg.geonaturaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private Button btnIniciarSesion, btnRegistarse;
    private TextInputEditText txtUser, txtPassword;
    private String currentUser, currentPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        txtUser = findViewById(R.id.txtUser);
        txtPassword = findViewById(R.id.txtPassword);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnRegistarse = findViewById(R.id.btnRegistrarse);

        currentUser = txtUser.getText().toString();
        currentPassword= txtPassword.getText().toString();

        btnIniciarSesion.setOnClickListener(v -> {
            Toast.makeText(this, "Abriendo menu principal...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MenuPrincipalActivity.class);
            startActivity(intent);
        });

        btnRegistarse.setOnClickListener(v -> {
            Toast.makeText(this, "Abriendo registrar nuevo usuario...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SigninActivity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}