package com.heavylink.service;

import java.awt.print.Pageable;
import java.util.List;

import com.heavylink.model.Abogado;
import com.heavylink.model.AbogadoArea;
import jdk.jfr.Category;
import org.springframework.data.domain.Page;

public interface IAbogadoAreaService extends IGenericService<AbogadoArea,Integer>{
	Page<AbogadoArea> listPage(Pageable pageable);
}
