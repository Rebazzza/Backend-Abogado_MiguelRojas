package com.heavylink.config;

import com.heavylink.Repository.IRol;
import com.heavylink.Repository.IUsuario;
import com.heavylink.model.Rol;
import com.heavylink.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final IRol rolRepo;
    private final IUsuario usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        if (rolRepo.findAll().isEmpty()) {
            rolRepo.save(new Rol(1, "ADMIN", "Administrador del sistema"));
            rolRepo.save(new Rol(2, "ABOGADO", "Abogado"));
            rolRepo.save(new Rol(3, "ASISTENTE", "Asistente legal"));
        }

        if (usuarioRepo.findOneByUsername("admin") == null) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            Rol adminRole = rolRepo.findById(1).orElse(null);
            if (adminRole != null) {
                admin.setRoles(List.of(adminRole));
            }
            usuarioRepo.save(admin);
        }
    }
}
