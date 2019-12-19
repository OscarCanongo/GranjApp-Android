package com.example.lagranjaapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lagranjaapp.R;
import com.example.lagranjaapp.model.Actividad;
import com.example.lagranjaapp.model.DataActividades;
import com.example.lagranjaapp.model.DataAplicacion;
import com.example.lagranjaapp.model.DataTipoRecurso;
import com.example.lagranjaapp.model.DataUsuario;
import com.example.lagranjaapp.model.Recurso;
import com.example.lagranjaapp.model.TipoRecurso;

import java.util.List;

public class AplicacionAdapter extends RecyclerView.Adapter<AplicacionAdapter.AplicacionAdapterHolder> {

    private List<DataAplicacion> aplicaciones;
    private Recurso recurso;

    public AplicacionAdapter(List<DataAplicacion> aplicaciones){
        this.aplicaciones = aplicaciones;
    }

    @NonNull
    @Override
    public AplicacionAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_lista_aplicaciones, parent, false);
        return new AplicacionAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AplicacionAdapterHolder holder, int position) {
        DataAplicacion aplicacion = aplicaciones.get(position);
        holder.aplicacionesRecurso.setText(aplicacion.getRecurso());
        holder.aplicacionesFecha.setText(aplicacion.getFecha());
    }

    @Override
    public int getItemCount() {
        return aplicaciones.size();
    }

    public void setData(List<DataAplicacion> aplicaciones){
        this.aplicaciones = aplicaciones;
        notifyDataSetChanged();
    }

    public class AplicacionAdapterHolder extends RecyclerView.ViewHolder{
        private TextView aplicacionesRecurso;
        private TextView aplicacionesFecha;

        public AplicacionAdapterHolder (@NonNull View itemView){
            super(itemView);
            aplicacionesRecurso = itemView.findViewById(R.id.aplicacionesRecurso);
            aplicacionesFecha = itemView.findViewById(R.id.aplicacionesFecha);
        }
    }
}
