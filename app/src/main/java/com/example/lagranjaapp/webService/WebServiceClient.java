package com.example.lagranjaapp.webService;
import com.example.lagranjaapp.model.Actividad;
import com.example.lagranjaapp.model.Animal;
import com.example.lagranjaapp.model.Aplicacion;
import com.example.lagranjaapp.model.Cultivo;
import com.example.lagranjaapp.model.Data;
import com.example.lagranjaapp.model.DataAplicacion;
import com.example.lagranjaapp.model.DataCultivos;
import com.example.lagranjaapp.model.DataEquipos;
import com.example.lagranjaapp.model.DataRecursos;
import com.example.lagranjaapp.model.DataUsuario;
import com.example.lagranjaapp.model.DataZonas;
import com.example.lagranjaapp.model.Planting;
import com.example.lagranjaapp.model.Recurso;
import com.example.lagranjaapp.model.Rol;
import com.example.lagranjaapp.model.Subzone;
import com.example.lagranjaapp.model.Usuario;
import com.example.lagranjaapp.model.Zona;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface WebServiceClient {
    @GET("animal")
    Call<List<Data>> getAnimales();

    @GET("crops")
    Call<List<DataCultivos>> getCultivos();

    @GET("resources")
    Call<List<DataRecursos>> getRecursos();

    @GET("teams")
    Call<List<DataEquipos>> getEquipos();

    @GET("{id}")
    Call<Cultivo> getCultivo(@Path("id") String id);

    @GET("{id}")
    Call<Zona> getSubzones(@Path("id") String id);

    @GET("{id}")
    Call<Subzone> getPlantings(@Path("id") String id);

    @GET("{id}")
    Call<Recurso> getRecurso(@Path("id") String id);

    @GET("application")
    Call<List<DataAplicacion>> getAplicaciones();

    @GET("users")
    Call<List<DataUsuario>> getUsuarios();

    @GET("zone")
    Call<List<DataZonas>> getZonas();

    @POST("activities")
    Call<Actividad> postActividades (@Body Actividad actividad);

    @POST("users")
    Call<Usuario> postUsuario (@Body Usuario usuario);

    @POST("animal")
    Call<Animal> postAnimal (@Body Animal animal);

    @POST("roles")
    Call<Rol> postRol (@Body Rol rol);

    @POST("planting")
    Call<Planting> postPlanting (@Body Planting planting);

    @POST("application")
    Call<Aplicacion> postAplicaciones (@Body Aplicacion aplicacion);

    @PUT("{id}")
    Call<Actividad> putActividad(@Path("id") String id, @Body Actividad actividad);

    @PUT("{id}")
    Call<Animal> putAnimal(@Path("id") String id, @Body Animal animal);

    @PUT("{id}")
    Call<Usuario> putUsuario(@Path("id") String id, @Body Usuario usuario);

    @GET("{id}")
    Call<Usuario> getUsuario(@Path("id") String id);

    @GET("{id}")
    Call<Actividad> getActividad(@Path("id") String id);


}