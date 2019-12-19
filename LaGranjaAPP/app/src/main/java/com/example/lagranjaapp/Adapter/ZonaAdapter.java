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
import com.example.lagranjaapp.model.DataZonas;

import java.util.List;

public class ZonaAdapter extends RecyclerView.Adapter<ZonaAdapter.ZonaAdapterHolder> {

    private List<DataZonas> zonas;

    public ZonaAdapter(List<DataZonas> zonas){
        this.zonas = zonas;
    }

    @NonNull
    @Override
    public ZonaAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_lista_zona, parent, false);

        return new ZonaAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ZonaAdapterHolder holder, int position) {
        DataZonas zona = zonas.get(position);
        holder.zonaName.setText(zona.getName());
        holder.id.setText(zona.getId());
        holder.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return zonas.size();
    }

    public void setData(List<DataZonas> zonas){
        this.zonas.addAll(zonas);
        notifyDataSetChanged();
    }

    public class ZonaAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView zonaName;
        private TextView id;
        private RelativeLayout zona;
        private Context context;

        public ZonaAdapterHolder (@NonNull View itemView){
            super(itemView);
            context = itemView.getContext();
            zonaName = itemView.findViewById(R.id.zonaName);
            id = itemView.findViewById(R.id.idSubzone);
            zona = itemView.findViewById(R.id.zona);
        }

        public void setOnClickListener(){
            zona.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, Subzona.class);
            intent.putExtra("id", id.getText());
            context.startActivity(intent);
        }
    }


}
