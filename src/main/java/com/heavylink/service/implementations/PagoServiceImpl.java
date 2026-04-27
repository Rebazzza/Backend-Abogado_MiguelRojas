package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.heavylink.repository.IPago;
import com.heavylink.model.Pago;
import com.heavylink.service.IPagoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements IPagoService {

    private final IPago repository;

    @Override
    public Pago save(Pago pago) throws Exception {
        return repository.save(pago);
    }

    @Override
    public Pago update(Pago pago, Integer id) throws Exception {
        Pago actual = findById(id);
        BeanUtils.copyProperties(pago, actual, "idPago");
        return repository.save(actual);
    }

    @Override
    public List<Pago> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public Pago findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Pago no encontrado con id: " + id));
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.delete(findById(id));
    }
}
