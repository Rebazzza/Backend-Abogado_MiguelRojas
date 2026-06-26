package com.heavylink.service;

import com.heavylink.model.Especialista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEspecialistaService extends IGenericService<Especialista,Integer>{
    Page<Especialista> listPage(Pageable pageable);
	
}
