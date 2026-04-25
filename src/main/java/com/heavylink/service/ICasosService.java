package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Caso;

public interface ICasosService {
	Caso save(Caso casos) throws  Exception;
	Caso update(Caso casos, Integer id) throws  Exception;
    List<Caso> findAll() throws  Exception;
    Caso findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
}
