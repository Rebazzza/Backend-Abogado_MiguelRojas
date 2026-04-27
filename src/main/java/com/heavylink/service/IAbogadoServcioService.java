package com.heavylink.service;

import java.util.List;

import com.heavylink.model.AbogadoServicio;

public interface IAbogadoServcioService {
	AbogadoServicio save(AbogadoServicio abogadoServicio) throws  Exception;
    AbogadoServicio update(AbogadoServicio abogadoServicio, Integer id) throws  Exception;
    List<AbogadoServicio> findAll() throws  Exception;
    AbogadoServicio findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
}
