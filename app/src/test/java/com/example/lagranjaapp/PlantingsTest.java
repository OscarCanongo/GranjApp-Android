package com.example.lagranjaapp;

import android.os.Bundle;

import com.example.lagranjaapp.model.Cultivo;
import com.example.lagranjaapp.model.DataPlanting;
import com.example.lagranjaapp.model.Planting;
import com.example.lagranjaapp.model.Subzone;
import com.example.lagranjaapp.model.Zona;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlantingsTest {

    private Bundle bundle = null;
    private Subzone subzona;
    private Cultivo cultivo;
    ArrayList<DataPlanting> plantings = null;


    @Before
    public void setUp(){
        subzona = new Subzone("1","Hola", plantings);
        cultivo = new Cultivo("1","Hola");
    }

    @Test
    public void isNoEmpty() {
        assertFalse( Plantings.isNoEmpty(bundle));
    }

    @Test
    public void isNoNull() {
        assertFalse( Plantings.isNoNull(null));
    }

    @Test
    public void isNoNullCultivo() {
        assertFalse( Plantings.isNoNullCultivo(null));
    }

    @Test
    public void isCreated() {
        assertTrue( Plantings.isCreated(subzona));
    }

    @Test
    public void isCreatedCultivo() {
        assertTrue( Plantings.isCreatedCultivo(cultivo));
    }
}