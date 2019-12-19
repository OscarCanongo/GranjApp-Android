package com.example.lagranjaapp.webService;
import com.example.lagranjaapp.model.Data;
import com.example.lagranjaapp.model.DataActividades;
import com.example.lagranjaapp.model.DataPlanting;
import com.example.lagranjaapp.model.DataSubzone;
import com.example.lagranjaapp.model.DataUsuario;
import com.example.lagranjaapp.model.DataZonas;
import com.example.lagranjaapp.model.Subzone;
import com.example.lagranjaapp.model.Zona;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface WebServiceClient {
    @GET("animal")
    Call<List<Data>> getAnimales();

    @GET("{id}")
    Call<Zona> getSubzones(@Path("id") String id);

    @GET("{id}")
    Call<Subzone> getPlantings(@Path("id") String id);

    @GET("users")
    Call<List<DataUsuario>> getUsuarios();

    @GET()
    Call<DataUsuario> getUsuarios(@Url String url);

    @GET("zone")
    Call<List<DataZonas>> getZonas();

    @GET()
    Call<List<DataZonas>> getZonas(@Url String url);

    @GET("activities")
    Call<List<DataActividades>> getActividades();

    @GET()
    Call<DataActividades> getActividades(@Url String url);

}
