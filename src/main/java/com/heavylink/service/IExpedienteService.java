package com.heavylink.service;

import com.heavylink.model.Expediente;
import com.heavylink.model.Notificacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IExpedienteService extends IGenericService<Expediente,Integer>{
    Page<Expediente> listPage(Pageable pageable);
}
