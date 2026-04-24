package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Casos;

public interface ICasosService {
	Casos save(Casos casos) throws  Exception;
	Casos update(Casos casos, Integer id) throws  Exception;
    List<Casos> findAll() throws  Exception;
    Casos findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
}
