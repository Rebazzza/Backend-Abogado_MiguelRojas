package com.heavylink.service.implementations;

import java.util.List;

import com.heavylink.Repository.ICliente;
import com.heavylink.Repository.IGenericRepository;
import com.heavylink.model.Abogado;
import com.heavylink.model.Cliente;
import com.heavylink.model.Notificacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.INotificacion;
import com.heavylink.service.INotificacionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificacionServiceImpl extends GenericService<Notificacion, Integer> implements INotificacionService {

    private final INotificacion repo;

    @Override
    protected IGenericRepository<Notificacion, Integer> getRepo() {
        return repo;
    }
    @Override
    public Page<Notificacion> listPage(Pageable pageable) {
        return repo.findAll(pageable);
    }
}