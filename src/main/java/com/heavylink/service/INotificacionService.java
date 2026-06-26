package com.heavylink.service;


import com.heavylink.model.Notificacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface INotificacionService extends IGenericService<Notificacion,Integer>{
    Page<Notificacion> listPage(Pageable pageable);
}
