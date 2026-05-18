package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Especialista;

public interface IEspecialistaService {
	Especialista save(Especialista especialista) throws  Exception;
	Especialista update(Especialista especialista, Integer id) throws  Exception;
    List<Especialista> findAll() throws  Exception;
    Especialista findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
	
}
