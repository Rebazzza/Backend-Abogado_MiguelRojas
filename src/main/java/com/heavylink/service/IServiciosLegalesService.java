package com.heavylink.service;

import java.util.List;

import com.heavylink.model.ServicioLegal;

public interface IServiciosLegalesService {
	ServicioLegal save(ServicioLegal servicios) throws  Exception;
	ServicioLegal update(ServicioLegal servicios, Integer id) throws  Exception;
    List<ServicioLegal> findAll() throws  Exception;
    ServicioLegal findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
}
