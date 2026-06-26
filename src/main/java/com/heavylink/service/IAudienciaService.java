package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Abogado;
import com.heavylink.model.AreaDerecho;
import com.heavylink.model.Audiencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAudienciaService extends IGenericService<Audiencia,Integer>{
    Page<Audiencia> listPage(Pageable pageable);
}
