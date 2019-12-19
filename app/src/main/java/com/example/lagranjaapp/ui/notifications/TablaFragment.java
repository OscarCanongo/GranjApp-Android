package com.example.lagranjaapp.ui.notifications;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lagranjaapp.Adapter.UsuarioAdapter;
import com.example.lagranjaapp.Adapter.ZonaAdapter;
import com.example.lagranjaapp.R;
import com.example.lagranjaapp.model.Data;
import com.example.lagranjaapp.model.DataUsuario;
import com.example.lagranjaapp.model.DataZonas;
import com.example.lagranjaapp.model.Usuario;
import com.example.lagranjaapp.model.Zona;
import com.example.lagranjaapp.webService.WebServiceClient;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TablaFragment extends Fragment {

    private RecyclerView recyclerView;
    private UsuarioAdapter adapter;
    private List<DataUsuario> usuarios;
    private Retrofit retrofit;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private WebServiceClient client;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_tabla, container, false);

        setUpView();
        lanzarPeticion();

        return root;
    }

    private void lanzarPeticion(){
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://granjapp2.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();

        WebServiceClient client = retrofit.create(WebServiceClient.class);
        Call<List<DataUsuario>> call = client.getUsuarios();
        call.enqueue(new Callback<List<DataUsuario>>() {
            @Override
            public void onResponse(Call<List<DataUsuario>> call, Response<List<DataUsuario>> response) {
                List<DataUsuario> tmp = response.body();
                crearTabla(tmp);
                adapter.setData(tmp);
            }

            @Override
            public void onFailure(Call<List<DataUsuario>> call, Throwable t) {
                Log.d("TAG1", "Error: " + t.getMessage());
            }
        });
    }

    private void setUpView(){
        usuarios = new ArrayList<>();
        adapter = new UsuarioAdapter(usuarios);
        recyclerView = root.findViewById(R.id.recyclerViewUsuario);
        LinearLayoutManager lim = new LinearLayoutManager(getActivity());
        lim.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(lim);
        recyclerView.setAdapter(adapter);
    }

    private void crearTabla(List<DataUsuario> tmp){
        BarChart chart = root.findViewById(R.id.barchart);

        ArrayList horas = new ArrayList();
        for (int i = 0; i<tmp.size(); i++) {
            horas.add(new BarEntry(i, Integer.parseInt(tmp.get(i).getEstrellas())));
        }


        BarDataSet dataSet = new BarDataSet(horas, "Estrellas");
        dataSet.setColors(Color.parseColor("#486b00"));

        BarData data = new BarData(dataSet);
        data.setBarWidth(0.9f);
        data.setValueTextSize(20f);

        chart.setData(data);
        chart.animateXY(1000, 1000);
        chart.invalidate();
        chart.setNoDataText("Sin datos para graficar");
        chart.setNoDataTextColor(Color.RED);
        chart.setDrawGridBackground(false);

        Description desc = new Description();
        desc.setText("Tabla de Estrellas");
        chart.setDescription(desc);

        Legend leyenda = chart.getLegend();
        leyenda.setTextSize(15f);
        leyenda.setForm(Legend.LegendForm.CIRCLE);
        leyenda.setFormSize(15f);
    }

}