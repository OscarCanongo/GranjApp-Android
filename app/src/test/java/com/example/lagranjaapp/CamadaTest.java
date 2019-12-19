package com.example.lagranjaapp;

import android.os.Bundle;

import org.junit.Test;

import static org.junit.Assert.*;

public class CamadaTest {

    private Bundle bundle = null;
    private String cadena = "BUUUUU";
    private String cadena1 = "2";

    @Test
    public void isNoEmpty() {
        assertFalse( Camada.isNoEmpty(bundle));
    }

    @Test
    public void isNoNull() {
        assertFalse( Camada.isNoNull(cadena));

    }

    @Test
    public void isaNumber() {
        assertTrue( Camada.isNoNull(cadena1));
    }
}