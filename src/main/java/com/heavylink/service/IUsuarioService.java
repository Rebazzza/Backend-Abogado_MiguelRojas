package com.heavylink.service;


import com.heavylink.model.Usuario;

public interface IUsuarioService extends IGenericService<Usuario,Integer>{
    Usuario findOneByUsername(String username);
    void changePassword(String username, String password);
}
