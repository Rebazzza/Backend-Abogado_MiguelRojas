package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Servicios_Legales;

public interface IServicios_LegalesService {
	Servicios_Legales save(Servicios_Legales servicios) throws  Exception;
	Servicios_Legales update(Servicios_Legales servicios, Integer id) throws  Exception;
    List<Servicios_Legales> findAll() throws  Exception;
    Servicios_Legales findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
}
