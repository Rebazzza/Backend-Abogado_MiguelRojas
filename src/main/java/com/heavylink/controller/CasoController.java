package com.heavylink.controller;


import java.net.URI;
import java.util.List;

import com.heavylink.Repository.ICasos;
import com.heavylink.dto.CasoDTO;

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

import com.heavylink.model.Caso;
import com.heavylink.service.ICasosService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/casos")

public class CasoController {

    private final ICasosService service;
    private final ICasos casoRepo;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<List<CasoDTO>> findAll() throws Exception {
        List<CasoDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, CasoDTO.class)).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "abogadoId")
    public ResponseEntity<List<CasoDTO>> findAllByAbogado(@RequestParam Integer abogadoId) throws Exception {
        List<CasoDTO> list = casoRepo.findByAbogadoIdAbogado(abogadoId).stream().map(e -> modelMapper.map(e, CasoDTO.class)).toList();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CasoDTO> findById(@PathVariable Integer id) throws Exception {
        Caso obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj,CasoDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CasoDTO dto) throws Exception {
        Caso obj = service.save(modelMapper.map(dto, Caso.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCaso()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CasoDTO> update(@PathVariable Integer id, @Valid @RequestBody CasoDTO dto) throws Exception {
        Caso obj = service.update(modelMapper.map(dto, Caso.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, CasoDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/hateoas/{id}")
    public EntityModel<CasoDTO> findByIdHateoas(@PathVariable Integer id) throws Exception {
        Caso obj = service.findById(id);
        CasoDTO dto = modelMapper.map(obj, CasoDTO.class);
        EntityModel<CasoDTO> entityModel = EntityModel.of(dto);
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(CasoController.class).findById(id)).withRel("caso-self-info"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(CasoController.class).findAll()).withRel("caso-all-list"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(AbogadoController.class).findAll()).withRel("abogados-list"));
        return entityModel;
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Caso>> listPageable(Pageable pageable){
        Page<Caso> page =service.listPage(pageable);
        return ResponseEntity.ok(page);
    }


}
