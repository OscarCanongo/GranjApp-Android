package com.example.lagranjaapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lagranjaapp.EditarTareaPut;
import com.example.lagranjaapp.R;
import com.example.lagranjaapp.model.DataActividades;

import java.util.List;

public class EditarActividadAdapter extends RecyclerView.Adapter<EditarActividadAdapter.EditarActividadAdapterHolder> {

    private List<DataActividades> actividades;

    public EditarActividadAdapter(List<DataActividades> actividades){
        this.actividades = actividades;
    }

    @NonNull
    @Override
    public EditarActividadAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_lista_editar_tarea, parent, false);
        return new EditarActividadAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EditarActividadAdapterHolder holder, int position) {
        DataActividades actividad = actividades.get(position);
        holder.actividadName.setText(actividad.getName());
        holder.tareaAsignacion.setText(actividad.getUsuario());
        holder.id.setText(actividad.getId());
        holder.fecha.setText(actividad.getFechaInicio());
        holder.descrip.setText(actividad.getDescripcion());
        holder.complejidad.setText(actividad.getComplejidad());
        holder.usuario.setText(actividad.getUsuario());
        holder.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return actividades.size();
    }

    public void setData(List<DataActividades> actividades){
        this.actividades = actividades;
        notifyDataSetChanged();
    }

    public class EditarActividadAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView actividadName, tareaAsignacion, id, fecha, usuario, complejidad, descrip;
        private RelativeLayout editarTarea;
        private Context context;

        public EditarActividadAdapterHolder (@NonNull View itemView){
            super(itemView);
            context = itemView.getContext();
            actividadName = itemView.findViewById(R.id.actividadName);
            tareaAsignacion = itemView.findViewById(R.id.tareaAsignacion);
            id = itemView.findViewById(R.id.idTarea);
            editarTarea = itemView.findViewById(R.id.editarTarea);
            fecha = itemView.findViewById(R.id.txtFechaList);
            usuario = itemView.findViewById(R.id.txtUserAsigList);
            complejidad = itemView.findViewById(R.id.txtCompList);
            descrip = itemView.findViewById(R.id.txtDescList);
        }

        public void setOnClickListener(){
            editarTarea.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, EditarTareaPut.class);
            intent.putExtra("id", id.getText());
            intent.putExtra("nombre", actividadName.getText());
            intent.putExtra("fecha",fecha.getText());
            intent.putExtra("desc", descrip.getText());
            intent.putExtra("compl",complejidad.getText());
            intent.putExtra("idUser", usuario.getText());
            context.startActivity(intent);
        }
    }
}