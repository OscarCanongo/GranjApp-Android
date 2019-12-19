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
import com.example.lagranjaapp.model.DataUsuario;

import java.util.List;

public class ActividadAdapter extends RecyclerView.Adapter<ActividadAdapter.ActividadAdapterHolder> {

    private List<DataActividades> actividades;

    public ActividadAdapter(List<DataActividades> actividades){
        this.actividades = actividades;
    }

    @NonNull
    @Override
    public ActividadAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_lista_tarea, parent, false);
        return new ActividadAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ActividadAdapterHolder holder, int position) {
        DataActividades actividad = actividades.get(position);
        holder.actividadName.setText(actividad.getName());
        holder.tareaAsignacion.setText(actividad.getUsuario());
    }

    @Override
    public int getItemCount() {
        return actividades.size();
    }

    public void setData(List<DataActividades> actividades){
        this.actividades = actividades;
        notifyDataSetChanged();
    }

    public class ActividadAdapterHolder extends RecyclerView.ViewHolder{
        private CheckBox actividadName;
        private TextView tareaAsignacion;

        public ActividadAdapterHolder (@NonNull View itemView){
            super(itemView);
            actividadName = itemView.findViewById(R.id.actividadName);
            tareaAsignacion = itemView.findViewById(R.id.tareaAsignacion);
        }
    }
}
