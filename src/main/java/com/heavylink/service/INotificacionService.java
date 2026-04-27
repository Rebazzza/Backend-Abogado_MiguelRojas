package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Notifiacion;

public interface INotificacionService {
	Notifiacion save(Notifiacion notifiacion) throws  Exception;
	Notifiacion update(Notifiacion notifiacion, Integer id) throws  Exception;
    List<Notifiacion> findAll() throws  Exception;
    Notifiacion findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
}
