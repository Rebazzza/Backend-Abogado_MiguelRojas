package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Notificacion;

public interface INotificacionService {
	Notificacion save(Notificacion notifiacion) throws  Exception;
	Notificacion update(Notificacion notifiacion, Integer id) throws  Exception;
    List<Notificacion> findAll() throws  Exception;
    Notificacion findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
}
