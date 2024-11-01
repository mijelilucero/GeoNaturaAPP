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

import gt.edu.umg.geonaturaapp.DataBase.Model.Fauna;
import gt.edu.umg.geonaturaapp.R;

public class ListaFaunaAdapter extends RecyclerView.Adapter<ListaFaunaAdapter.FaunaViewHolder>{

    ArrayList<Fauna> listaFauna;

    public ListaFaunaAdapter(ArrayList<Fauna> listaFauna){
        this.listaFauna= listaFauna;
    }

    @NonNull
    @Override
    public ListaFaunaAdapter.FaunaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_fauna,null,false);
        return new ListaFaunaAdapter.FaunaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaFaunaAdapter.FaunaViewHolder holder, int position) {
        holder.txtNombre.setText(listaFauna.get(position).getNombre());
        holder.txtClasificacion.setText(listaFauna.get(position).getClasificacion());
        holder.txtTamanio.setText(String.valueOf(listaFauna.get(position).getTamanio()));
        holder.txtPeso.setText(String.valueOf(listaFauna.get(position).getPeso()));
        holder.txtHabitat.setText(listaFauna.get(position).getHabitat());
        holder.txtEstadoConservacion.setText(listaFauna.get(position).getEstadoConservacion());
        holder.txtFechaHora.setText(listaFauna.get(position).getFechaHora());
        holder.txtLatitud.setText(String.valueOf(listaFauna.get(position).getLatitud()));
        holder.txtLongitud.setText(String.valueOf(listaFauna.get(position).getLongitud()));

        byte[] imagenBytes = listaFauna.get(position).getImagen();
        if (imagenBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);
            holder.imageViewFoto.setImageBitmap(bitmap);
        } else {
            holder.imageViewFoto.setImageResource(R.drawable.formalogin);
        }
    }

    @Override
    public int getItemCount() {
        return listaFauna.size();
    }

    public class FaunaViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre, txtClasificacion, txtTamanio, txtPeso,
                txtHabitat, txtEstadoConservacion, txtFechaHora, txtLatitud, txtLongitud;

        ImageView imageViewFoto;

        public FaunaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtClasificacion = itemView.findViewById(R.id.txtClasificacion);
            txtTamanio = itemView.findViewById(R.id.txtTamanio);
            txtPeso = itemView.findViewById(R.id.txtPeso);
            txtHabitat = itemView.findViewById(R.id.txtHabitat);
            txtEstadoConservacion = itemView.findViewById(R.id.txtEstadoConservacion);
            txtFechaHora = itemView.findViewById(R.id.txtFechaHora);
            txtLatitud = itemView.findViewById(R.id.txtLatitud);
            txtLongitud = itemView.findViewById(R.id.txtLongitud);

            imageViewFoto = itemView.findViewById(R.id.imageViewFoto);
        }
    }
}
