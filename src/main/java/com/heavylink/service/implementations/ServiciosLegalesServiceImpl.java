package com.heavylink.service.implementations;

import java.util.List;

import com.heavylink.Repository.IGenericRepository;
import com.heavylink.Repository.INotificacion;
import com.heavylink.model.Notificacion;
import com.heavylink.service.INotificacionService;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.IServicioLegal;
import com.heavylink.model.ServicioLegal;
import com.heavylink.service.IServiciosLegalesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiciosLegalesServiceImpl extends GenericService<ServicioLegal, Integer> implements IServiciosLegalesService {

    private final IServicioLegal repo;

    @Override
    protected IGenericRepository<ServicioLegal, Integer> getRepo() {
        return repo;
    }
}