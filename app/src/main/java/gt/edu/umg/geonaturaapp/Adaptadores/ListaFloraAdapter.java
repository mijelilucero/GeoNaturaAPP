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


import gt.edu.umg.geonaturaapp.DataBase.Model.Flora;
import gt.edu.umg.geonaturaapp.R;

public class ListaFloraAdapter extends RecyclerView.Adapter<ListaFloraAdapter.FloraViewHolder>{

    ArrayList<Flora> listaFlora;

    public ListaFloraAdapter(ArrayList<Flora> listaFlora){
        this.listaFlora = listaFlora;
    }

    @NonNull
    @Override
    public FloraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_flora,null,false);
        return new FloraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FloraViewHolder holder, int position) {
        holder.txtNombreComun.setText(listaFlora.get(position).getNombreComun());
        holder.txtNombreCientifico.setText(listaFlora.get(position).getNombreCientifico());
        holder.txtTipoPlanta.setText(listaFlora.get(position).getTipoPlanta());
        holder.txtAltura.setText(String.valueOf(listaFlora.get(position).getAltura()));
        holder.txtFruto.setText(listaFlora.get(position).getFruto());
        holder.txtEstadoConservacion.setText(listaFlora.get(position).getEstadoConservacion());
        holder.txtFechaHora.setText(listaFlora.get(position).getFechaHora());
        holder.txtLatitud.setText(String.valueOf(listaFlora.get(position).getLatitud()));
        holder.txtLongitud.setText(String.valueOf(listaFlora.get(position).getLongitud()));

        byte[] imagenBytes = listaFlora.get(position).getImagen();
        if (imagenBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);
            holder.imageViewFoto.setImageBitmap(bitmap);
        } else {
            holder.imageViewFoto.setImageResource(R.drawable.formalogin);
        }

    }

    @Override
    public int getItemCount() {
        return listaFlora.size();
    }

    public class FloraViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombreComun, txtNombreCientifico, txtTipoPlanta, txtAltura,
                txtFruto, txtEstadoConservacion, txtFechaHora, txtLatitud, txtLongitud;

        ImageView imageViewFoto;

        public FloraViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombreComun = itemView.findViewById(R.id.txtNombreComun);
            txtNombreCientifico = itemView.findViewById(R.id.txtNombreCientifico);
            txtTipoPlanta = itemView.findViewById(R.id.txtTipoPlanta);
            txtAltura = itemView.findViewById(R.id.txtAltura);
            txtFruto = itemView.findViewById(R.id.txtFruto);
            txtEstadoConservacion = itemView.findViewById(R.id.txtEstadoConservacion);
            txtFechaHora = itemView.findViewById(R.id.txtFechaHora);
            txtLatitud = itemView.findViewById(R.id.txtLatitud);
            txtLongitud = itemView.findViewById(R.id.txtLongitud);

            imageViewFoto = itemView.findViewById(R.id.imageViewFoto);
        }
    }
}
