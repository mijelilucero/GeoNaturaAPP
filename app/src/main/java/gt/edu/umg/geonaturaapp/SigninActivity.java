package gt.edu.umg.geonaturaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import gt.edu.umg.geonaturaapp.DataBase.Model.Usuario;
import gt.edu.umg.geonaturaapp.DataBase.Service.UsuarioService;

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


        btnVolverInicioSesion.setOnClickListener(v -> {
            Toast.makeText(this, "Volviendo a inicio de sesión...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        btnCrearNuevaCuenta.setOnClickListener(v -> {
            nuevoNombre = txtRegistroNombre.getText().toString();
            nuevoApellido = txtRegistroApellido.getText().toString();
            nuevoUser = txtRegistroUser.getText().toString();
            nuevoPassword = txtRegistroPassword.getText().toString();
            nuevoPasswordConfirm = txtRegistroPasswordConfirm.getText().toString();

            registrarUsuario(nuevoNombre, nuevoApellido, nuevoUser, nuevoPassword, nuevoPasswordConfirm);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void registrarUsuario(String nuevoNombre, String nuevoApellido, String nuevoUser, String nuevoPassword, String nuevoPasswordConfirm){

        UsuarioService usuarioService = new UsuarioService(this);

        if (nuevoNombre.isEmpty() || nuevoApellido.isEmpty() || nuevoUser.isEmpty() || nuevoPassword.isEmpty() || nuevoPasswordConfirm.isEmpty()){
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Usuario usuario = usuarioService.obtenerUsuarioPorNombre(nuevoUser);
            if (usuario == null) {
                if (nuevoPassword.equals(nuevoPasswordConfirm)) {

                    Usuario nuevoUsuario = new Usuario();
                    nuevoUsuario.setNombre(nuevoNombre);
                    nuevoUsuario.setApellido(nuevoApellido);
                    nuevoUsuario.setNombreUsuario(nuevoUser);
                    nuevoUsuario.setContrasenia(nuevoPassword);

                    try {
                        usuarioService.agregarUsuario(nuevoUsuario);
                        Toast.makeText(this, "Registro exitoso...", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(this, LoginActivity.class);
                        startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(this, "Error al registrar el usuario: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("Registro", "Error al registrar el usuario", e);
                    }
                } else {
                    Toast.makeText(this, "La confirmación de la contraseña no coincide.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Ya existe un usuario con estos datos.", Snackbar.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Toast.makeText(this, "Error al buscar el usuario: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("Registro", "Error al buscar el usuario:", e);
        }
    }
}