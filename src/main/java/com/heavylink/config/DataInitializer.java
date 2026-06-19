package com.heavylink.config;

import com.heavylink.Repository.IRol;
import com.heavylink.Repository.IUsuario;
import com.heavylink.model.Rol;
import com.heavylink.model.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final IRol rolRepo;
    private final IUsuario usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        if (rolRepo.count() == 0) {
            log.info("No roles found. Creating default roles...");
            rolRepo.save(new Rol(1, "ADMIN", "Administrador del sistema"));
            rolRepo.save(new Rol(2, "ABOGADO", "Abogado de la firma"));
            rolRepo.save(new Rol(3, "ASISTENTE", "Asistente legal"));
            log.info("Default roles created: ADMIN, ABOGADO, ASISTENTE");
        } else {
            log.info("Roles already exist, skipping initialization.");
        }

        if (usuarioRepo.findOneByUsername("admin") == null) {
            log.info("Admin user not found. Creating default admin user...");
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRol("ADMIN");
            Rol adminRole = rolRepo.findById(1).orElse(null);
            if (adminRole != null) {
                admin.setRoles(List.of(adminRole));
            }
            usuarioRepo.save(admin);
            log.info("Default admin user created (username: admin, password: admin123)");
        } else {
            log.info("Admin user already exists, skipping initialization.");
        }
    }
}
