package com.heavylink.service;

import com.heavylink.model.Expediente;
import java.util.List;

public interface IExpedienteService {
    Expediente registrar(Expediente expediente) throws Exception;
    Expediente modificar(Expediente expediente) throws Exception;
    List<Expediente> listar() throws Exception;
    Expediente leerPorId(Integer id) throws Exception;
    void eliminar(Integer id) throws Exception;
}