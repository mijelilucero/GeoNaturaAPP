package gt.edu.umg.geonaturaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import gt.edu.umg.geonaturaapp.DataBase.Model.Usuario;
import gt.edu.umg.geonaturaapp.DataBase.Service.UsuarioService;

public class LoginActivity extends AppCompatActivity {

    private Button btnIniciarSesion, btnRegistarse;
    private TextInputEditText txtUser, txtPassword;
    private String currentUserName, currentPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        txtUser = findViewById(R.id.txtUser);
        txtPassword = findViewById(R.id.txtPassword);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnRegistarse = findViewById(R.id.btnRegistrarse);

        btnIniciarSesion.setOnClickListener(v -> {
            currentUserName = txtUser.getText().toString().trim();
            currentPassword= txtPassword.getText().toString().trim();
            iniciarSesion(currentUserName,currentPassword);
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


    public void iniciarSesion(String currentUserName, String currentPassword){

        UsuarioService usuarioService = new UsuarioService(this);

        if (currentUserName.isEmpty() || currentPassword.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Usuario usuario = usuarioService.obtenerUsuarioPorNombre(currentUserName);

            if (usuario != null) {
                if (usuario.getContrasenia().equals(currentPassword)) {

                    SharedPreferences sharedPreferences = getSharedPreferences("MiAppPreferences", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("idUsuario", usuario.getIdUser());
                    editor.putString("nombreUsuario", usuario.getNombre());
                    editor.apply();

                    Toast.makeText(this, "Inicio de sesión exitoso...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MenuPrincipalActivity.class);
                    startActivity(intent);
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Usuario o contraseña incorrectos.", Snackbar.LENGTH_SHORT).show();
                }
            } else {
                Snackbar.make(findViewById(android.R.id.content), "No se encontró usuario con los datos ingresados.", Snackbar.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Toast.makeText(this, "Error al buscar el usuario: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("Registro", "Error al buscar el usuario:", e);
        }
    }
}