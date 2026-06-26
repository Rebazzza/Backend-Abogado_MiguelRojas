package com.heavylink.service;


import com.heavylink.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUsuarioService extends IGenericService<Usuario,Integer>{
    Usuario findOneByUsername(String username);
    void changePassword(String username, String password);
    Page<Usuario> listPage(Pageable pageable);
}
