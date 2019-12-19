package com.example.lagranjaapp.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lagranjaapp.Adapter.ZonaAdapter;
import com.example.lagranjaapp.R;
import com.example.lagranjaapp.model.DataZonas;
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

public class CultivosFragment extends Fragment {

    private RecyclerView recyclerView;
    private ZonaAdapter adapter;
    private List<DataZonas> zonas;
    private Retrofit retrofit;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private WebServiceClient client;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_cultivos, container, false);

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
        Call<List<DataZonas>> call = client.getZonas();
        call.enqueue(new Callback<List<DataZonas>>() {
            @Override
            public void onResponse(Call<List<DataZonas>> call, Response<List<DataZonas>> response) {
                List<DataZonas> tmp = response.body();
                adapter.setData(tmp);
            }

            @Override
            public void onFailure(Call<List<DataZonas>> call, Throwable t) {
                Log.d("TAG1", "Error: " + t.getMessage());
            }
        });
    }

    private void setUpView(){
        zonas = new ArrayList<>();
        adapter = new ZonaAdapter(zonas);
        recyclerView = root.findViewById(R.id.recyclerViewZona);
        LinearLayoutManager lim = new LinearLayoutManager(getActivity());
        lim.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(lim);
        recyclerView.setAdapter(adapter);
    }
}