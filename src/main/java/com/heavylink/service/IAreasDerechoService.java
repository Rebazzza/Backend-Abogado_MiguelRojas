package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Abogado;
import com.heavylink.model.AreaDerecho;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAreasDerechoService extends IGenericService<AreaDerecho,Integer>{
    Page<AreaDerecho> listPage(Pageable pageable);

}
