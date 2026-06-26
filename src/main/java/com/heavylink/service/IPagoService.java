package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Abogado;
import com.heavylink.model.Pago;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPagoService extends IGenericService<Pago,Integer>{
    Page<Pago> listPage(Pageable pageable);
}
