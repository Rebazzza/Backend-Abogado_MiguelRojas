package com.heavylink.Repository;

import com.heavylink.model.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IUsuario extends IGenericRepository<Usuario, Integer> {
    //@Query("FROM User u WHERE u.username = ?);
    //Queries derivados
    Usuario findOneByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.username = :username") //JQPL
    void changePassword(@Param("username") String username, @Param("password") String newPassword);
}