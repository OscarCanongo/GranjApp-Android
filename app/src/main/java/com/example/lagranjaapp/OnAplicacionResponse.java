package com.example.lagranjaapp;

import com.example.lagranjaapp.model.Aplicacion;
import com.example.lagranjaapp.model.DataAplicacion;

import java.util.List;

public interface OnAplicacionResponse  {
    void aplicacion(List<DataAplicacion> aplicacion);
}
