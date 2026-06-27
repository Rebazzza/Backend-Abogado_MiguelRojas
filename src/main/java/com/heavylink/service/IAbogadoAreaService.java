package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Abogado;
import com.heavylink.model.AbogadoArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAbogadoAreaService extends IGenericService<AbogadoArea,Integer>{
	Page<AbogadoArea> listPage(Pageable pageable);
}
