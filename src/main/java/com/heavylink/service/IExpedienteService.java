package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Expediente;

public interface IExpedienteService {
	Expediente save(Expediente expediente) throws  Exception;
	Expediente update(Expediente expediente, Integer id) throws  Exception;
    List<Expediente> findAll() throws  Exception;
    Expediente findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
}
