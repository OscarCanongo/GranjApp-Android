package com.example.lagranjaapp;

import com.example.lagranjaapp.model.DataCultivos;
import com.example.lagranjaapp.model.DataRecursos;

import java.util.List;

public interface OnCultivoResponse {
    void cultivos(List<DataCultivos> cultivos);
}
