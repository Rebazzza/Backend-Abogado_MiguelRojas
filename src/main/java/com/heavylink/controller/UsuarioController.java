package com.heavylink.controller;


import java.net.URI;
import java.util.List;



import com.heavylink.dto.UsuarioDTO;

import com.heavylink.model.Abogado;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
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
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() throws Exception {
        List<UsuarioDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, UsuarioDTO.class)).toList();

        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id) throws Exception {
        Usuario obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj,UsuarioDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody UsuarioDTO dto) throws Exception {
        Usuario obj = service.save(modelMapper.map(dto, Usuario.class));

        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        //localhost:9090/categories/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdUsuario()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Integer id,@Valid @RequestBody UsuarioDTO dto) throws Exception {
        Usuario obj = service.update(modelMapper.map(dto, Usuario.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, UsuarioDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/hateoas/{id}")
    public EntityModel<UsuarioDTO> findByIdHateoas(@PathVariable Integer id) throws Exception {
        Usuario obj = service.findById(id);
        UsuarioDTO dto = modelMapper.map(obj, UsuarioDTO.class);
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
