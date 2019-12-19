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

import com.example.lagranjaapp.R;
import com.example.lagranjaapp.Subzona;
import com.example.lagranjaapp.model.DataPlanting;
import com.example.lagranjaapp.model.DataZonas;
import com.example.lagranjaapp.model.Planting;

import java.util.List;

public class PlantingAdapter extends RecyclerView.Adapter<PlantingAdapter.PlantingAdapterHolder> {

    private List<DataPlanting> plantings;

    public PlantingAdapter(List<DataPlanting> plantings){
        this.plantings = plantings;
    }

    @NonNull
    @Override
    public PlantingAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_lista_planting, parent, false);

        return new PlantingAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantingAdapterHolder holder, int position) {
        DataPlanting planting = plantings.get(position);
        holder.plantingCultivo.setText(planting.getCultivo());
        holder.plantingId.setText(planting.getId());
        holder.plantingSalud.setText(planting.getSalud());
        holder.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return plantings.size();
    }

    public void setData(List<DataPlanting> plantings){
        this.plantings = plantings;
        notifyDataSetChanged();
    }

    public class PlantingAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView plantingCultivo;
        private TextView plantingId;
        private TextView plantingSalud;
        private RelativeLayout planting;
        private Context context;

        public PlantingAdapterHolder (@NonNull View itemView){
            super(itemView);
            context = itemView.getContext();
            plantingCultivo = itemView.findViewById(R.id.plantingCultivo);
            plantingId = itemView.findViewById(R.id.plantingId);
            plantingSalud = itemView.findViewById(R.id.plantingSalud);
            planting = itemView.findViewById(R.id.planting);
        }

        public void setOnClickListener(){
            planting.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, Subzona.class);
            intent.putExtra("id", plantingId.getText());
            context.startActivity(intent);
        }
    }


}

