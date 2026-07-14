package com.heavylink.Repository;

import com.heavylink.model.Audiencia;

import java.util.List;

public interface IAudiencia extends IGenericRepository<Audiencia, Integer> {
    List<Audiencia> findByAbogadoIdAbogado(Integer idAbogado);
}
