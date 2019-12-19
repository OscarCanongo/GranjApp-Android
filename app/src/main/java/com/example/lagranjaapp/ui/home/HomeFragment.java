package com.example.lagranjaapp.ui.home;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lagranjaapp.Adapter.ActividadAdapter;
import com.example.lagranjaapp.R;
import com.example.lagranjaapp.model.DataActividades;
import com.example.lagranjaapp.model.DataUsuario;
import com.example.lagranjaapp.webService.WebServiceClient;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private ActividadAdapter adapter;
    private List<DataActividades> actividades;
    private List<DataActividades> actividadesTMP;
    private List<DataUsuario> usuarios;
    private Retrofit retrofit;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private WebServiceClient client;
    private View root;
    private TextView actHechas;
    private ProgressBar barra;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);

        setUpView();
        lanzarPeticion2();


        return root;
    }

    private void lanzarPeticion2 (){
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
                usuarios = response.body();
                int actCounter = 0, actTerminadas = 0;
                for (int i = 0; i < usuarios.size(); i++){
                    actividadesTMP = usuarios.get(i).getActivities();
                    for (int j = 0; j< actividadesTMP.size(); j++){
                        actividades.add(actividadesTMP.get(j));
                        actividades.get(actCounter).setUsuario(usuarios.get(i).getName());
                        if(Integer.parseInt(actividades.get(actCounter).getTerminado())== 1) {
                            actTerminadas++;
                        }
                        actCounter++;
                    }

                }
                adapter.setData(actividades);


                actHechas.setText(actTerminadas+"/"+actCounter+" actividades completadas");
                barra.setMax(actCounter);
                barra.setProgress(actTerminadas);
            }

            @Override
            public void onFailure(Call<List<DataUsuario>> call, Throwable t) {
                Log.d("TAG1", "Error: " + t.getMessage());
            }
        });
    }

    private void setUpView(){
        actividades = new ArrayList<>();
        usuarios = new ArrayList<>();
        adapter = new ActividadAdapter(actividades);
        recyclerView = root.findViewById(R.id.recyclerViewActividades);
        LinearLayoutManager lim = new LinearLayoutManager(getActivity());
        lim.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(lim);
        recyclerView.setAdapter(adapter);
        actHechas = root.findViewById(R.id.actividadesHechas);
        barra = root.findViewById(R.id.progreso);
    }


}