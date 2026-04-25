package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Cita;

public interface ICitasService {
	Cita save(Cita cita) throws  Exception;
    Cita update(Cita cita, Integer id) throws  Exception;
    List<Cita> findAll() throws  Exception;
    Cita findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
}
