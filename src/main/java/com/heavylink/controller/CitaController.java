package com.heavylink.controller;


import java.net.URI;
import java.util.List;



import com.heavylink.dto.CitaDTO;
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

import com.heavylink.model.Cita;
import com.heavylink.service.ICitasService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/citas")

public class CitaController {

    private final ICitasService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<List<CitaDTO>> findAll() throws Exception {
        List<CitaDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, CitaDTO.class)).toList();

        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> findById(@PathVariable Integer id) throws Exception {
        Cita obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj,CitaDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CitaDTO dto) throws Exception {
        Cita obj = service.save(modelMapper.map(dto, Cita.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCita()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> update(@PathVariable Integer id,@Valid @RequestBody CitaDTO dto) throws Exception {
        Cita obj = service.update(modelMapper.map(dto, Cita.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, CitaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/hateoas/{id}")
    public EntityModel<CitaDTO> findByIdHateoas(@PathVariable Integer id) throws Exception {
        Cita obj = service.findById(id);
        CitaDTO dto = modelMapper.map(obj, CitaDTO.class);
        EntityModel<CitaDTO> entityModel = EntityModel.of(dto);
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(CitaController.class).findById(id)).withRel("cita-self-info"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(CitaController.class).findAll()).withRel("cita-all-list"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(AbogadoController.class).findAll()).withRel("abogados-list"));
        return entityModel;
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Cita>> listPageable(Pageable pageable){
        Page<Cita> page =service.listPage(pageable);
        return ResponseEntity.ok(page);
    }

}
