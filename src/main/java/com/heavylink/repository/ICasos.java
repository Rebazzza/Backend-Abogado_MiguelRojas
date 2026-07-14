package com.heavylink.Repository;

import com.heavylink.model.Caso;

import java.util.List;

public interface ICasos extends IGenericRepository<Caso, Integer> {
    List<Caso> findByAbogadoIdAbogado(Integer idAbogado);
}
