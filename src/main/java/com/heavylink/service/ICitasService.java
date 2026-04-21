package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Citas;

public interface ICitasService {
	Citas save(Citas cita) throws  Exception;
    Citas update(Citas citas, Integer id) throws  Exception;
    List<Citas> findAll() throws  Exception;
    Citas findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
}
