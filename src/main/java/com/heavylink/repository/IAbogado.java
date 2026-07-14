package com.heavylink.Repository;

import com.heavylink.model.Abogado;

import java.util.Optional;

public interface IAbogado extends IGenericRepository<Abogado,Integer>{
    Optional<Abogado> findByUsuarioIdUsuario(Integer idUsuario);
}
