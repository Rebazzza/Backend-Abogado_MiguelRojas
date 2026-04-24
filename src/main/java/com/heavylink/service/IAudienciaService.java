package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Audiencia;

public interface IAudienciaService {
	Audiencia save(Audiencia audiencia) throws  Exception;
	Audiencia update(Audiencia audiencia, Integer id) throws  Exception;
    List<Audiencia> findAll() throws  Exception;
    Audiencia findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
}
