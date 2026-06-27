package com.heavylink.controller;


import java.net.URI;
import java.util.List;



import com.heavylink.dto.ServicioLegalDTO;

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

import com.heavylink.model.ServicioLegal;
import com.heavylink.service.IServiciosLegalesService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/servicios")

public class ServicioLegalController {

    private final IServiciosLegalesService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<List<ServicioLegalDTO>> findAll() throws Exception {
        List<ServicioLegalDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, ServicioLegalDTO.class)).toList();

        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ServicioLegalDTO> findById(@PathVariable Integer id) throws Exception {
        ServicioLegal obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj,ServicioLegalDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid  @RequestBody ServicioLegalDTO dto) throws Exception {
        ServicioLegal obj = service.save(modelMapper.map(dto, ServicioLegal.class));

        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        //localhost:9090/categories/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdServicio()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioLegalDTO> update(@PathVariable Integer id,@Valid @RequestBody ServicioLegalDTO dto) throws Exception {
        ServicioLegal obj = service.update(modelMapper.map(dto, ServicioLegal.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, ServicioLegalDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/hateoas/{id}")
    public EntityModel<ServicioLegalDTO> findByIdHateoas(@PathVariable Integer id) throws Exception {
        ServicioLegal obj = service.findById(id);
        ServicioLegalDTO dto = modelMapper.map(obj, ServicioLegalDTO.class);
        EntityModel<ServicioLegalDTO> entityModel = EntityModel.of(dto);
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ServicioLegalController.class).findById(id)).withRel("servicio-self-info"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ServicioLegalController.class).findAll()).withRel("servicio-all-list"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(AbogadoController.class).findAll()).withRel("abogados-list"));
        return entityModel;
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<ServicioLegal>> listPageable(Pageable pageable){
        Page<ServicioLegal> page =service.listPage(pageable);
        return ResponseEntity.ok(page);
    }
}
