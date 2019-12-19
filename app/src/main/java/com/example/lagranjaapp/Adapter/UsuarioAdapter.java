package com.example.lagranjaapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lagranjaapp.R;
import com.example.lagranjaapp.model.DataUsuario;
import com.example.lagranjaapp.model.Usuario;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioAdapterHolder> {

    private List<DataUsuario> usuarios;

    public UsuarioAdapter(List<DataUsuario> usuarios){
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public UsuarioAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_lista_usuario, parent, false);
        return new UsuarioAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioAdapterHolder holder, int position) {
        DataUsuario usuario = usuarios.get(position);
        holder.usuarioName.setText(usuario.getName());
        holder.usuarioStars.setText(usuario.getEstrellas());
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public void setData(List<DataUsuario> usuarios){
        this.usuarios = usuarios;
        notifyDataSetChanged();
    }

    public class UsuarioAdapterHolder extends RecyclerView.ViewHolder{
        private TextView usuarioName;
        private TextView usuarioStars;

        public UsuarioAdapterHolder (@NonNull View itemView){
            super(itemView);
            usuarioName = itemView.findViewById(R.id.usuarioName);
            usuarioStars = itemView.findViewById(R.id.usuarioStars);
        }
    }
}