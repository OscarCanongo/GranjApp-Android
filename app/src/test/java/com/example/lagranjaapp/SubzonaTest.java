package com.example.lagranjaapp;

import android.os.Bundle;

import com.example.lagranjaapp.model.DataSubzone;
import com.example.lagranjaapp.model.Zona;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SubzonaTest {

    private Subzona extras = null;
    private Zona zona;
    private ArrayList<DataSubzone> subzonas = null;
    private Bundle bundle = null;

    @Before
    public void setUp(){
        extras = new Subzona();
        zona = new Zona("1", "1", subzonas);
    }

    @Test
    public void isNoEmpty() {
        assertFalse( Subzona.isNoEmpty(bundle));
    }

    @Test
    public void isNoNull() {
        assertFalse( Subzona.isNoNull(null));
    }

    @Test
    public void isCreated() {
        assertTrue( Subzona.isCreated(zona));

    }
}