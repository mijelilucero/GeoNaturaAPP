package gt.edu.umg.geonaturaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import gt.edu.umg.geonaturaapp.Adaptadores.ListaFloraAdapter;
import gt.edu.umg.geonaturaapp.DataBase.Model.Flora;
import gt.edu.umg.geonaturaapp.DataBase.Service.FloraService;

public class MostrarRegistrosFloraActivity extends AppCompatActivity {

    private ImageButton btnMenuPrincipal;
    RecyclerView listaFlora;
    ArrayList<Flora> listaArrayFlora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mostrar_registros_flora);

        btnMenuPrincipal = findViewById(R.id.btnMenuPrincipal);
        listaFlora = findViewById(R.id.listaFlora);

        listaFlora.setLayoutManager(new LinearLayoutManager(this));

        FloraService floraService = new FloraService(MostrarRegistrosFloraActivity.this);

        listaArrayFlora = new ArrayList<>();

        SharedPreferences sharedPreferences = getSharedPreferences("MiAppPreferences", MODE_PRIVATE);
        int idUsuario = sharedPreferences.getInt("idUsuario", -1);

        Log.d("Registro", "Mijeli: id usuario actual: " + idUsuario);

        ListaFloraAdapter adapterFlora = new ListaFloraAdapter(floraService.getAllFloras(idUsuario));

        listaFlora.setAdapter(adapterFlora);

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