package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Abogado;

public interface IAbogadoService {
	Abogado save(Abogado abogado) throws  Exception;
    Abogado update(Abogado abogado, Integer id) throws  Exception;
    List<Abogado> findAll() throws  Exception;
    Abogado findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
	
	
}
