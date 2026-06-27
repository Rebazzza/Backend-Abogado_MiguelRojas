package com.heavylink.controller;


import java.net.URI;
import java.util.List;


import com.heavylink.dto.DocumentosLegalesDTO;

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

import com.heavylink.model.DocumentosLegales;
import com.heavylink.service.IDocumentosLegalesService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
@RequiredArgsConstructor
@RequestMapping("/documentos")

public class DocumentosLegalesController {

    private final IDocumentosLegalesService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<List<DocumentosLegalesDTO>> findAll() throws Exception {
        List<DocumentosLegalesDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, DocumentosLegalesDTO.class)).toList();

        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DocumentosLegalesDTO> findById(@PathVariable Integer id) throws Exception {
        DocumentosLegales obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj,DocumentosLegalesDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody DocumentosLegalesDTO dto) throws Exception {
        DocumentosLegales obj = service.save(modelMapper.map(dto, DocumentosLegales.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdDocumento()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentosLegalesDTO> update(@PathVariable Integer id,@Valid @RequestBody DocumentosLegalesDTO dto) throws Exception {
        DocumentosLegales obj = service.update(modelMapper.map(dto, DocumentosLegales.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, DocumentosLegalesDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/hateoas/{id}")
    public EntityModel<DocumentosLegalesDTO> findByIdHateoas(@PathVariable Integer id) throws Exception {
        DocumentosLegales obj = service.findById(id);
        DocumentosLegalesDTO dto = modelMapper.map(obj, DocumentosLegalesDTO.class);
        EntityModel<DocumentosLegalesDTO> entityModel = EntityModel.of(dto);
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(DocumentosLegalesController.class).findById(id)).withRel("documento-self-info"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(DocumentosLegalesController.class).findAll()).withRel("documento-all-list"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ExpedienteController.class).findAll()).withRel("expedientes-list"));
        return entityModel;
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<DocumentosLegales>> listPageable(Pageable pageable){
        Page<DocumentosLegales> page =service.listPage(pageable);
        return ResponseEntity.ok(page);
    }
}
