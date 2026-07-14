package com.heavylink.Repository;

import com.heavylink.model.Cita;

import java.util.List;

public interface ICitas extends IGenericRepository<Cita, Integer> {
    List<Cita> findByAbogadoIdAbogado(Integer idAbogado);
}
