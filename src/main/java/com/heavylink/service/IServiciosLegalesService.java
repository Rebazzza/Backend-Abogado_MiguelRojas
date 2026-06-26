package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Abogado;
import com.heavylink.model.ServicioLegal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IServiciosLegalesService  extends IGenericService<ServicioLegal,Integer>{
    Page<ServicioLegal> listPage(Pageable pageable);
}
