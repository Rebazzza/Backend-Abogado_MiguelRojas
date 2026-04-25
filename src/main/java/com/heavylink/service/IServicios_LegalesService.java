package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Servicio_Legal;

public interface IServicios_LegalesService {
	Servicio_Legal save(Servicio_Legal servicios) throws  Exception;
	Servicio_Legal update(Servicio_Legal servicios, Integer id) throws  Exception;
    List<Servicio_Legal> findAll() throws  Exception;
    Servicio_Legal findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
}
