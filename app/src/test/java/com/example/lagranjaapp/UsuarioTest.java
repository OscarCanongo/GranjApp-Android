package com.example.lagranjaapp;

import android.os.Bundle;

import com.example.lagranjaapp.model.Zona;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UsuarioTest {

    private Usuario extras = null;
    private Bundle bundle = null;


    @Before
    public void setUp(){
        extras = new Usuario();
    }

    @Test
    public void isNoEmpty() {
        assertFalse( Usuario.isNoEmpty(bundle));
    }

    @Test
    public void isNoNull() {
        assertFalse( Usuario.isNoNull(null));
    }
}