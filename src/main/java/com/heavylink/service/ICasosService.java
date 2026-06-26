package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Abogado;
import com.heavylink.model.AreaDerecho;
import com.heavylink.model.Caso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICasosService extends IGenericService<Caso,Integer>{
    Page<Caso> listPage(Pageable pageable);
}
