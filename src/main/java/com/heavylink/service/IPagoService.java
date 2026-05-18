package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Pago;

public interface IPagoService {
	Pago save(Pago pago) throws  Exception;
	Pago update(Pago pago, Integer id) throws  Exception;
    List<Pago> findAll() throws  Exception;
    Pago findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
}
