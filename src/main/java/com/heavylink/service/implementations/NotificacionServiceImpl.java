package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.INotificacion;
import com.heavylink.model.Notifiacion;
import com.heavylink.service.INotificacionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificacionServiceImpl implements INotificacionService {

    private final INotificacion repository;

    @Override
    public Notifiacion save(Notifiacion notifiacion) throws Exception {
        return repository.save(notifiacion);
    }

    @Override
    public Notifiacion update(Notifiacion notifiacion, Integer id) throws Exception {
        Notifiacion actual = findById(id);
        BeanUtils.copyProperties(notifiacion, actual, "idNotificacion");
        return repository.save(actual);
    }

    @Override
    public List<Notifiacion> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Notifiacion findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Notificacion no encontrada con id: " + id));
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.delete(findById(id));
    }
}
