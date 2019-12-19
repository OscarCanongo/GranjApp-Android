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

import com.example.lagranjaapp.Camada;
import com.example.lagranjaapp.R;
import com.example.lagranjaapp.model.Data;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalAdapterHolder> {

    private List<Data> animals;
    private String id;

    public AnimalAdapter(List<Data> animals){
        this.animals = animals;
    }

    @NonNull
    @Override
    public AnimalAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_lista_camadas, parent, false);

        return new AnimalAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalAdapterHolder holder, int position) {
        Data animal = animals.get(position);
        holder.tvName.setText(animal.getName());
        id = animal.getId();
        holder.camadaNacimiento.setText(animal.getfechaNacimiento() );
        holder.hembras.setText(animal.getNo_h());
        holder.machos.setText(animal.getNo_m());
        holder.salud.setText(animal.getSalud());
        holder.etapa.setText(animal.getEtapa());
        holder.setOnClickListener();

    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    public void setData(List<Data> animals){
        this.animals.addAll(animals);
        notifyDataSetChanged();
    }


    public class AnimalAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvName;
        private TextView camadaNacimiento;
        private TextView hembras;
        private TextView machos;
        private TextView salud;
        private TextView etapa;
        private RelativeLayout camada;
        private Context context;

        public AnimalAdapterHolder (@NonNull View itemView){
            super(itemView);
            context = itemView.getContext();
            tvName = itemView.findViewById(R.id.tvName);
            camadaNacimiento = itemView.findViewById(R.id.camadaNacimiento);
            hembras = itemView.findViewById(R.id.descripcion);
            machos = itemView.findViewById(R.id.machos);
            camada = itemView.findViewById(R.id.camada);
            salud = itemView.findViewById(R.id.salud);
            etapa = itemView.findViewById(R.id.etapa);
        }

        public void setOnClickListener(){
            camada.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, Camada.class);
            intent.putExtra("id", id);
            intent.putExtra("name", tvName.getText());
            intent.putExtra("nacimiento", camadaNacimiento.getText());
            intent.putExtra("hembras", hembras.getText());
            intent.putExtra("machos", machos.getText());
            intent.putExtra("salud", salud.getText());
            intent.putExtra("etapa", etapa.getText());
            context.startActivity(intent);
        }
    }
}
