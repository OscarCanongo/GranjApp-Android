package com.example.lagranjaapp.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.lagranjaapp.R;

public class ExtrasFragment extends Fragment {
    private String nombreC = "", correo = "", estrellas = "", id ="", nombre="", apellido="";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null){
            nombre = extras.getString("nombre");
            apellido = extras.getString("apellido");
            nombreC = nombre +" "+ apellido;
            correo = extras.getString("correo");
            estrellas = extras.getString("estrellas");
            id = extras.getString("id");
        }
        TextView nombreUser = root.findViewById(R.id.nombreUsuario);
        TextView emailUser = root.findViewById(R.id.emailUsuario);
        TextView starsUser = root.findViewById(R.id.estrellasUsuario);
        TextView id1 = root.findViewById(R.id.idTarea);
        TextView txtnombre = root.findViewById(R.id.txtNombreP);
        TextView txtapell = root.findViewById(R.id.txtApellidoP);

        nombreUser.setText(nombreC);
        emailUser.setText(correo);
        starsUser.setText("Tus estrellas: "+estrellas);
        txtnombre.setText(nombre);
        txtapell.setText(apellido);
        id1.setText(id);

        return root;
    }


}