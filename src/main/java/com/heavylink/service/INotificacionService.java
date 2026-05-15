package com.heavylink.service;

import com.heavylink.model.Notificacion;

import java.util.List;

public interface INotificacionService {
	Notificacion save(Notificacion notificacion) throws  Exception;
	Notificacion update(Notificacion notificacion, Integer id) throws  Exception;
    List<Notificacion> findAll() throws  Exception;
    Notificacion findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
}
