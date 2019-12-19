package com.example.lagranjaapp;

import android.os.Bundle;

import com.example.lagranjaapp.model.DataAplicacion;
import com.example.lagranjaapp.model.Recurso;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AplicacionesTest {

    private Bundle bundle = null;
    List<DataAplicacion>  call = null;
    Recurso recurso = new Recurso("1","1","1");

    @Test
    public void isNoEmpty() {
        assertFalse( Aplicaciones.isNoEmpty(bundle));
    }

    @Test
    public void isNoNull() {
        assertFalse( Aplicaciones.isNoNull(null));
    }

    @Test
    public void isNoNullAplicaciones() {
        assertFalse( Aplicaciones.isNoNullAplicaciones(call));
    }

    @Test
    public void isNoNullRecurso() {
        assertFalse( Aplicaciones.isNoNullRecurso(null));
    }

    @Test
    public void isCreated() {
        assertFalse( Aplicaciones.isNoNullRecurso(null));
    }
}