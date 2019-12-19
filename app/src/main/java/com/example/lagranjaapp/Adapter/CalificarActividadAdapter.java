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

import com.example.lagranjaapp.CalificarTareaPut;
import com.example.lagranjaapp.EditarTareaPut;
import com.example.lagranjaapp.R;
import com.example.lagranjaapp.model.DataActividades;

import java.util.List;

public class CalificarActividadAdapter extends RecyclerView.Adapter<CalificarActividadAdapter.CalificarActividadAdapterHolder> {

    private List<DataActividades> actividades;

    public CalificarActividadAdapter(List<DataActividades> actividades){
        this.actividades = actividades;
    }

    @NonNull
    @Override
    public CalificarActividadAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_lista_calificar_tarea, parent, false);
        return new CalificarActividadAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CalificarActividadAdapterHolder holder, int position) {
        DataActividades actividad = actividades.get(position);
        holder.actividadName.setText(actividad.getName());
        holder.tareaAsignacion.setText(actividad.getUsuario());
        holder.id.setText(actividad.getId());
        holder.fecha.setText(actividad.getFechaInicio());
        holder.descrip.setText(actividad.getDescripcion());
        holder.complejidad.setText(actividad.getComplejidad());
        holder.usuario.setText(actividad.getUsuario());
       // holder.terminado.setText(actividad.getTerminado());
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

    public class CalificarActividadAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView actividadName, tareaAsignacion, id, fecha, usuario, complejidad, descrip, terminado;
        private RelativeLayout calificarTarea;
        private Context context;

        public CalificarActividadAdapterHolder (@NonNull View itemView){
            super(itemView);
            context = itemView.getContext();
            actividadName = itemView.findViewById(R.id.actividadNameCalificar);
            tareaAsignacion = itemView.findViewById(R.id.tareaAsignacionCalificar);
            id = itemView.findViewById(R.id.idTareaCalificar);
            calificarTarea = itemView.findViewById(R.id.calificarTarea);
            fecha = itemView.findViewById(R.id.txtFechaListCalificar);
            usuario = itemView.findViewById(R.id.txtUserAsigListCalificar);
            complejidad = itemView.findViewById(R.id.txtCompListCalificar);
            descrip = itemView.findViewById(R.id.txtDescListCalificar);
            //terminado = itemView.findViewById(R.id.txtTerminado);
        }

        public void setOnClickListener(){
            calificarTarea.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, CalificarTareaPut.class);
            intent.putExtra("id", id.getText());
            intent.putExtra("nombre", actividadName.getText());
            intent.putExtra("fecha",fecha.getText());
            intent.putExtra("desc", descrip.getText());
            intent.putExtra("compl",complejidad.getText());
            intent.putExtra("idUser", usuario.getText());
            //intent.putExtra("terminado", terminado.getText());
            context.startActivity(intent);
        }
    }
}