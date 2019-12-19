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

import com.example.lagranjaapp.Plantings;
import com.example.lagranjaapp.R;
import com.example.lagranjaapp.model.DataSubzone;

import java.util.List;

public class SubzonaAdapter extends RecyclerView.Adapter<SubzonaAdapter.SubzonaAdapterHolder> {

    private List<DataSubzone> subzonas;

    public SubzonaAdapter(List<DataSubzone> subzonas){
        this.subzonas = subzonas;
    }

    @NonNull
    @Override
    public SubzonaAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_lista_subzona, parent, false);
        return new SubzonaAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubzonaAdapterHolder holder, int position) {
        DataSubzone subzona = subzonas.get(position);
        holder.subzonaName.setText(subzona.getName());
        holder.id.setText(subzona.getId());
        holder.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return subzonas.size();
    }

    public void setData(List<DataSubzone> subzonas){
        this.subzonas = subzonas;
        notifyDataSetChanged();
    }

    public class SubzonaAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView subzonaName;
        private TextView id;
        private Context context;
        private RelativeLayout subzona;

        public SubzonaAdapterHolder (@NonNull View itemView){
            super(itemView);
            context = itemView.getContext();
            id = itemView.findViewById(R.id.idSubzone);
            subzonaName = itemView.findViewById(R.id.subzonaName);
            subzona = itemView.findViewById(R.id.subzona);
        }

        public void setOnClickListener(){
            subzona.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, Plantings.class);
            intent.putExtra("id", id.getText());
            context.startActivity(intent);
        }
    }
}

