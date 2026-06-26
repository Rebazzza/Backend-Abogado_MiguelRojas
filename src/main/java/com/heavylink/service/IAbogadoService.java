package com.heavylink.service;

import com.heavylink.model.Abogado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAbogadoService extends IGenericService<Abogado,Integer> {

    Page<Abogado> listPage(Pageable pageable);
	
}
