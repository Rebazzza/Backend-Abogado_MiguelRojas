package com.heavylink.controller;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.heavylink.Repository.IAbogado;
import com.heavylink.dto.UsuarioDTO;

import com.heavylink.model.Abogado;
import com.heavylink.model.Rol;
import com.heavylink.Repository.IRol;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.heavylink.model.Usuario;
import com.heavylink.service.IUsuarioService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")

public class UsuarioController {

    private final IUsuarioService service;
    private final IRol rolRepo;
    private final IAbogado abogadoRepo;
    private final PasswordEncoder passwordEncoder;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    private UsuarioDTO toDto(Usuario entity) {
        UsuarioDTO dto = modelMapper.map(entity, UsuarioDTO.class);
        if (entity.getRoles() != null && !entity.getRoles().isEmpty()) {
            dto.setIdRol(entity.getRoles().get(0).getIdRole());
            dto.setRolName(entity.getRoles().get(0).getName());
        }
        if (entity.getAbogado() != null) {
            dto.setIdAbogado(entity.getAbogado().getIdAbogado());
            dto.setAbogadoNombre(entity.getAbogado().getNombre() + " " + entity.getAbogado().getApellido());
        }
        return dto;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() throws Exception {
        List<UsuarioDTO> list = service.findAll().stream().map(this::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(toDto(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody UsuarioDTO dto) throws Exception {
        Usuario entity = modelMapper.map(dto, Usuario.class);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        if (dto.getIdRol() != null) {
            Rol rol = rolRepo.findById(dto.getIdRol()).orElse(null);
            if (rol != null) entity.setRoles(List.of(rol));
        }
        entity.setAbogado(null);
        Usuario obj = service.save(entity);

        if (dto.getIdAbogado() != null) {
            abogadoRepo.findById(dto.getIdAbogado()).ifPresent(a -> {
                abogadoRepo.findByUsuarioIdUsuario(obj.getIdUsuario()).ifPresent(old -> {
                    if (!old.getIdAbogado().equals(a.getIdAbogado())) {
                        old.setUsuario(null);
                        abogadoRepo.save(old);
                    }
                });
                a.setUsuario(obj);
                abogadoRepo.save(a);
            });
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdUsuario()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Integer id, @Valid @RequestBody UsuarioDTO dto) throws Exception {

        Usuario entity = service.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }


        Optional<Abogado> currentAbogado = abogadoRepo.findByUsuarioIdUsuario(id);

        if (dto.getIdAbogado() != null) {
            if (currentAbogado.isEmpty() || !currentAbogado.get().getIdAbogado().equals(dto.getIdAbogado())) {
                currentAbogado.ifPresent(a -> {
                    a.setUsuario(null);
                    abogadoRepo.save(a);
                });
                abogadoRepo.findById(dto.getIdAbogado()).ifPresent(a -> {
                    Usuario ref = new Usuario();
                    ref.setIdUsuario(id);
                    a.setUsuario(ref);
                    abogadoRepo.save(a);
                });
            }
        } else if (currentAbogado.isPresent()) {
            currentAbogado.get().setUsuario(null);
            abogadoRepo.save(currentAbogado.get());
        }


        entity.setUsername(dto.getUsername());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        if (dto.getIdRol() != null) {
            Rol rol = rolRepo.findById(dto.getIdRol()).orElse(null);
            if (rol != null) {
                entity.setRoles(List.of(rol));
            }
        }


        if (dto.getIdAbogado() != null) {
            Abogado ab = new Abogado();
            ab.setIdAbogado(dto.getIdAbogado());
            entity.setAbogado(ab);
        } else {
            entity.setAbogado(null);
        }

        
        Usuario obj = service.update(entity, id);
        return ResponseEntity.ok(toDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<UsuarioDTO> findByIdHateoas(@PathVariable Integer id) throws Exception {
        UsuarioDTO dto = toDto(service.findById(id));
        EntityModel<UsuarioDTO> entityModel = EntityModel.of(dto);
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(UsuarioController.class).findById(id)).withRel("usuario-self-info"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(UsuarioController.class).findAll()).withRel("usuario-all-list"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(AbogadoController.class).findAll()).withRel("abogados-list"));
        return entityModel;
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Usuario>> listPageable(Pageable pageable){
        Page<Usuario> page =service.listPage(pageable);
        return ResponseEntity.ok(page);
    }
}
