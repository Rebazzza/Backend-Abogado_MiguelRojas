package com.heavylink.security;
import org.springframework.security.core.userdetails.User;
import com.heavylink.model.Usuario;
import com.heavylink.Repository.IUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Clase S4
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final IUsuario repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Buscamos el usuario en tu base de datos
        Usuario user = repo.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        List<GrantedAuthority> roles = new ArrayList<>();

        try {
            // 2. Intentamos cargar los roles reales de la base de datos
            if (user.getRoles() != null && !user.getRoles().isEmpty()) {
                user.getRoles().forEach(role -> {
                    // He puesto un condicional por si acaso tu entidad usa 'getNombre()' en vez de 'getName()'
                    String roleName = role.getName() != null ? role.getName() : "ROLE_USER";
                    roles.add(new SimpleGrantedAuthority(roleName));
                });
            } else {
                // Si el usuario no tiene roles asignados en la BD, le asignamos uno por defecto para que no explote
                roles.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
        } catch (Exception e) {
            System.out.println("⚠️ Alerta: Error cargando roles perezosos (Lazy). Usando rol por defecto. " + e.getMessage());
            roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        // 3. Retornamos el usuario listo para Spring Security
        return new User(
                user.getUsername(),
                user.getPassword(),
                roles
        );
    }
}