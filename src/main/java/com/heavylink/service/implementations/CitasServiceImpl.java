package com.heavylink.service.implementations;

import java.util.List;

import com.heavylink.Repository.ICliente;
import com.heavylink.Repository.IGenericRepository;
import com.heavylink.model.Cliente;
import com.heavylink.service.IClienteService;
import org.springframework.stereotype.Service;

import com.heavylink.Repository.ICitas;
import com.heavylink.model.Cita;
import com.heavylink.service.ICitasService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CitasServiceImpl extends GenericService<Cita, Integer> implements ICitasService {

    private final ICitas repo;

    @Override
    protected IGenericRepository<Cita, Integer> getRepo() {
        return repo;
    }
}