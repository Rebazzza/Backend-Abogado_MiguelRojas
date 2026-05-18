package com.heavylink.service;

import java.util.List;

import com.heavylink.model.AbogadoArea;

public interface IAbogadoAreaService {
	AbogadoArea save(AbogadoArea abogadoArea) throws  Exception;
    AbogadoArea update(AbogadoArea abogadoArea, Integer id) throws  Exception;
    List<AbogadoArea> findAll() throws  Exception;
    AbogadoArea findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
}
