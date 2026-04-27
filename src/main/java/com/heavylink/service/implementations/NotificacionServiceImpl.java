package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.heavylink.repository.INotificacion;
import com.heavylink.model.Notificacion;
import com.heavylink.service.INotificacionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificacionServiceImpl implements INotificacionService {

    private final INotificacion repository;

    @Override
    public Notificacion save(Notificacion notifiacion) throws Exception {
        return repository.save(notifiacion);
    }

    @Override
    public Notificacion update(Notificacion notifiacion, Integer id) throws Exception {
        Notificacion actual = findById(id);
        BeanUtils.copyProperties(notifiacion, actual, "idNotificacion");
        return repository.save(actual);
    }

    @Override
    public List<Notificacion> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Notificacion findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Notificacion no encontrada con id: " + id));
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.delete(findById(id));
    }
}
