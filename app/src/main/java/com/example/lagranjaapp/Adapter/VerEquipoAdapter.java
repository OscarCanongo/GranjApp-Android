package com.example.lagranjaapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lagranjaapp.R;
import com.example.lagranjaapp.model.DataEquipos;

import java.util.List;

public class VerEquipoAdapter extends RecyclerView.Adapter<VerEquipoAdapter.VerEquipoAdapterHolder> {

    private List<DataEquipos> equipos;

    public VerEquipoAdapter(List<DataEquipos> equipos){
        this.equipos = equipos;
    }

    @NonNull
    @Override
    public VerEquipoAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_lista_ver_equipo, parent, false);
        return new VerEquipoAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VerEquipoAdapterHolder holder, int position) {
        DataEquipos equipo = equipos.get(position);
        holder.equipoName.setText(equipo.getName());
        holder.equipoDescripcion.setText(equipo.getDescription());
    }

    @Override
    public int getItemCount() {
        return equipos.size();
    }

    public void setData(List<DataEquipos> equipos){
        this.equipos = equipos;
        notifyDataSetChanged();
    }

    public class VerEquipoAdapterHolder extends RecyclerView.ViewHolder {
        private TextView equipoName;
        private TextView equipoDescripcion;
        private Context context;
        private RelativeLayout equipo;

        public VerEquipoAdapterHolder (@NonNull View itemView){
            super(itemView);
            context = itemView.getContext();
            equipoName = itemView.findViewById(R.id.nameEquipo);
            equipoDescripcion= itemView.findViewById(R.id.equipoDescripcion);
            equipo = itemView.findViewById(R.id.equipo);
        }
    }
}

