package com.heavylink.controller;


import java.net.URI;
import java.util.List;


import com.heavylink.dto.AreaDerechoDTO;

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

import com.heavylink.model.AreaDerecho;
import com.heavylink.service.IAreasDerechoService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/areas")

public class AreaDerechoController {

    private final IAreasDerechoService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<List<AreaDerechoDTO>> findAll() throws Exception {
        List<AreaDerechoDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, AreaDerechoDTO.class)).toList();

        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AreaDerechoDTO> findById(@PathVariable Integer id) throws Exception {
        AreaDerecho obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj,AreaDerechoDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody AreaDerechoDTO dto) throws Exception {
        AreaDerecho obj = service.save(modelMapper.map(dto, AreaDerecho.class));

        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        //localhost:9090/categories/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdArea()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaDerechoDTO> update(@PathVariable Integer id,@Valid @RequestBody AreaDerechoDTO dto) throws Exception {
        AreaDerecho obj = service.update(modelMapper.map(dto, AreaDerecho.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, AreaDerechoDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/hateoas/{id}")
    public EntityModel<AreaDerechoDTO> findByIdHateoas(@PathVariable Integer id) throws Exception {
        AreaDerecho obj = service.findById(id);
        AreaDerechoDTO dto = modelMapper.map(obj, AreaDerechoDTO.class);
        EntityModel<AreaDerechoDTO> entityModel = EntityModel.of(dto);
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(AreaDerechoController.class).findById(id)).withRel("area-self-info"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(AreaDerechoController.class).findAll()).withRel("area-all-list"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(AbogadoController.class).findAll()).withRel("abogados-list"));
        return entityModel;
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<AreaDerecho>> listPageable(Pageable pageable){
        Page<AreaDerecho> page =service.listPage(pageable);
        return ResponseEntity.ok(page);
    }

}
