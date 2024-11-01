package gt.edu.umg.geonaturaapp.Adaptadores;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import gt.edu.umg.geonaturaapp.DataBase.Model.CaracteristicaEntorno;
import gt.edu.umg.geonaturaapp.R;

public class ListaCaracteristicaEntornoAdapter extends RecyclerView.Adapter<ListaCaracteristicaEntornoAdapter.CaracEntornoViewHolder>{

    ArrayList<CaracteristicaEntorno> listaCaracteristicasEntorno;

    public ListaCaracteristicaEntornoAdapter(ArrayList<CaracteristicaEntorno> listaCaracteristicasEntorno){
        this.listaCaracteristicasEntorno = listaCaracteristicasEntorno;
    }

    @NonNull
    @Override
    public ListaCaracteristicaEntornoAdapter.CaracEntornoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_caracteristica_entorno,null,false);
        return new ListaCaracteristicaEntornoAdapter.CaracEntornoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaCaracteristicaEntornoAdapter.CaracEntornoViewHolder holder, int position) {
        holder.txtNombre.setText(listaCaracteristicasEntorno.get(position).getNombre());
        holder.txtEcosistema.setText(listaCaracteristicasEntorno.get(position).getEcosistema());
        holder.txtTemperatura.setText(String.valueOf(listaCaracteristicasEntorno.get(position).getTemperatura()));
        holder.txtAltitud.setText(String.valueOf(listaCaracteristicasEntorno.get(position).getAltitud()));
        holder.txtPresenciaAgua.setText(listaCaracteristicasEntorno.get(position).getPresenciaAgua());
        holder.txtDensidadVegetal.setText(listaCaracteristicasEntorno.get(position).getDensidadVegetal());
        holder.txtFechaHora.setText(listaCaracteristicasEntorno.get(position).getFechaHora());
        holder.txtLatitud.setText(String.valueOf(listaCaracteristicasEntorno.get(position).getLatitud()));
        holder.txtLongitud.setText(String.valueOf(listaCaracteristicasEntorno.get(position).getLongitud()));

        byte[] imagenBytes = listaCaracteristicasEntorno.get(position).getImagen();
        if (imagenBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);
            holder.imageViewFoto.setImageBitmap(bitmap);
        } else {
            holder.imageViewFoto.setImageResource(R.drawable.formalogin);
        }
    }

    @Override
    public int getItemCount() {
        return listaCaracteristicasEntorno.size();
    }

    public class CaracEntornoViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre, txtEcosistema, txtTemperatura, txtAltitud,
                txtPresenciaAgua, txtDensidadVegetal, txtFechaHora, txtLatitud, txtLongitud;

        ImageView imageViewFoto;

        public CaracEntornoViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtEcosistema = itemView.findViewById(R.id.txtEcosistema);
            txtTemperatura = itemView.findViewById(R.id.txtTemperatura);
            txtAltitud = itemView.findViewById(R.id.txtAltitud);
            txtPresenciaAgua = itemView.findViewById(R.id.txtPresenciaAgua);
            txtDensidadVegetal = itemView.findViewById(R.id.txtDensidadVegetal);
            txtFechaHora = itemView.findViewById(R.id.txtFechaHora);
            txtLatitud = itemView.findViewById(R.id.txtLatitud);
            txtLongitud = itemView.findViewById(R.id.txtLongitud);

            imageViewFoto = itemView.findViewById(R.id.imageViewFoto);
        }
    }
}
