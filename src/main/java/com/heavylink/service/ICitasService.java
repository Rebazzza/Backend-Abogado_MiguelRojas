package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Abogado;
import com.heavylink.model.AreaDerecho;
import com.heavylink.model.Cita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICitasService extends IGenericService<Cita,Integer>{
    Page<Cita> listPage(Pageable pageable);

}
