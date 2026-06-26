package com.heavylink.service;

import com.heavylink.model.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRoleService extends IGenericService<Rol, Integer> {
    Page<Rol> listPage(Pageable pageable);
}