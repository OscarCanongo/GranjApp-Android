package com.example.lagranjaapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lagranjaapp.MainActivity;
import com.example.lagranjaapp.OnActividadesResponse;
import com.example.lagranjaapp.R;
import com.example.lagranjaapp.model.Actividad;
import com.example.lagranjaapp.model.DataActividades;
import com.example.lagranjaapp.ui.home.HomeFragment;
import com.example.lagranjaapp.webService.WebServiceClient;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        holder.descripcion.setText(actividad.getDescripcion());
        holder.id.setText(actividad.getId());
        holder.complejidad.setText(actividad.getComplejidad());
        holder.setOnClickListener();
        if(Integer.parseInt(actividad.getTerminado()) == 1) {
            holder.actividadName.setChecked(true);
        }
        if(Integer.parseInt(actividad.getTerminado()) == 2) {
            holder.actividadName.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return actividades.size();
    }

    public void setData(List<DataActividades> actividades){
        this.actividades = actividades;
        notifyDataSetChanged();
    }

    public class ActividadAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CheckBox actividadName;
        private TextView tareaAsignacion;
        private TextView descripcion;
        private TextView complejidad;
        private TextView id;
        private Context context;
        private WebServiceClient client;
        private HttpLoggingInterceptor loggingInterceptor;
        private OkHttpClient.Builder httpClientBuilder;
        private Retrofit retrofit;
        private Actividad actividad2;
        private String id1;
        private ImageView btnCalif;

        public ActividadAdapterHolder (@NonNull View itemView){
            super(itemView);
            context = itemView.getContext();
            actividadName = itemView.findViewById(R.id.actividadName);
            tareaAsignacion = itemView.findViewById(R.id.tareaAsignacion);
            id = itemView.findViewById(R.id.idTarea);
            //btnCalif = itemView.findViewById(R.id.btnCalifTarea);
            descripcion = itemView.findViewById(R.id.descripcion);
            complejidad = itemView.findViewById(R.id.complejidad);
        }

        public void setOnClickListener(){
            actividadName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Intent intent = new Intent(context, MainActivity.class);
            id1 = id.getText().toString();
            loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://granjapp2.appspot.com/activities/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClientBuilder.build())
                    .build();
            client = retrofit.create(WebServiceClient.class);
            getActividad1(id1, new OnActividadesResponse() {
                @Override
                public void actividad(Actividad actividad) {
                    actividad2 = actividad;
                    updateActividad();
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
            });
            //System.out.println(actividad2.getName());
            //updateActividad();
            notifyDataSetChanged();
            // context.startActivity(intent);
        }

        public void getActividad1(String id, OnActividadesResponse callback){
            Call<Actividad> call = client.getActividad(id);
            call.enqueue(new Callback<Actividad>() {
                @Override
                public void onResponse(Call<Actividad> call, Response<Actividad> response) {

                    //actividad2 = response.body();
                    //System.out.println("El nombre de la actividad es: " + actividad2.getName());
                    callback.actividad((response.body()));
                }

                @Override
                public void onFailure(Call<Actividad> call, Throwable t) {
                    Log.d("TAG1", "Error: " + t.getMessage());
                }
            });
        }

        private void updateActividad(){
            Actividad actividad = actividad2;
            System.out.println(actividad.getName());
            if (actividad.getTerminado() == 1)
                actividad.setTerminado(2);
            else
                actividad.setTerminado(1);
            Call<Actividad> call = client.putActividad(id1,actividad);
            call.enqueue(new Callback<Actividad>() {
                @Override
                public void onResponse(Call<Actividad> call, Response<Actividad> response) {
                    Actividad actividadResponse = response.body();
                }

                @Override
                public void onFailure(Call<Actividad> call, Throwable t) {

                }
            });
        }
    }
}