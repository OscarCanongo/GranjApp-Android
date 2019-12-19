package com.example.lagranjaapp;

import com.example.lagranjaapp.model.DataAplicacion;
import com.example.lagranjaapp.model.DataRecursos;
import com.example.lagranjaapp.model.Recurso;

import java.util.List;

public interface OnRecursosResponse {
    void recursos(List<DataRecursos> recursos);
}
