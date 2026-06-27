package com.heavylink.controller;


import java.net.URI;
import java.util.List;



import com.heavylink.dto.EspecialistaDTO;

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

import com.heavylink.model.Especialista;
import com.heavylink.service.IEspecialistaService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/especialistas")

public class EspecialistaController {

    private final IEspecialistaService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<List<EspecialistaDTO>> findAll() throws Exception {
        List<EspecialistaDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, EspecialistaDTO.class)).toList();

        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EspecialistaDTO> findById(@PathVariable Integer id) throws Exception {
        Especialista obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj,EspecialistaDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody EspecialistaDTO dto) throws Exception {
        Especialista obj = service.save(modelMapper.map(dto, Especialista.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdEspecialista()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialistaDTO> update(@PathVariable Integer id,@Valid @RequestBody EspecialistaDTO dto) throws Exception {
        Especialista obj = service.update(modelMapper.map(dto, Especialista.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, EspecialistaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/hateoas/{id}")
    public EntityModel<EspecialistaDTO> findByIdHateoas(@PathVariable Integer id) throws Exception {
        Especialista obj = service.findById(id);
        EspecialistaDTO dto = modelMapper.map(obj, EspecialistaDTO.class);
        EntityModel<EspecialistaDTO> entityModel = EntityModel.of(dto);
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(EspecialistaController.class).findById(id)).withRel("especialista-self-info"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(EspecialistaController.class).findAll()).withRel("especialista-all-list"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(AreaDerechoController.class).findAll()).withRel("areas-derecho-list"));
        return entityModel;
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Especialista>> listPageable(Pageable pageable){
        Page<Especialista> page =service.listPage(pageable);
        return ResponseEntity.ok(page);
    }
}
