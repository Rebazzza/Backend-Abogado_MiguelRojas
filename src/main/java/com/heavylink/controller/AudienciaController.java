package com.heavylink.controller;


import java.net.URI;
import java.util.List;

import com.heavylink.Repository.IAudiencia;
import com.heavylink.model.Abogado;
import com.heavylink.service.IAudienciaService;
import com.heavylink.dto.AudienciaDTO;
import com.heavylink.model.Audiencia;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/audiencias")

public class AudienciaController {

    private final IAudienciaService service;
    private final IAudiencia audienciaRepo;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;
    @GetMapping
    public ResponseEntity<List<AudienciaDTO>> findAll() throws Exception {
        List<AudienciaDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, AudienciaDTO.class)).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "abogadoId")
    public ResponseEntity<List<AudienciaDTO>> findAllByAbogado(@RequestParam Integer abogadoId) throws Exception {
        List<AudienciaDTO> list = audienciaRepo.findByAbogadoIdAbogado(abogadoId).stream().map(e -> modelMapper.map(e, AudienciaDTO.class)).toList();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AudienciaDTO> findById(@PathVariable Integer id) throws Exception {
        Audiencia obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj,AudienciaDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody AudienciaDTO dto) throws Exception {
        Audiencia obj = service.save(modelMapper.map(dto, Audiencia.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdAudiencia()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AudienciaDTO> update(@PathVariable Integer id,@Valid @RequestBody AudienciaDTO dto) throws Exception {
        Audiencia obj = service.update(modelMapper.map(dto, Audiencia.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, AudienciaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/hateoas/{id}")
    public EntityModel<AudienciaDTO> findByIdHateoas(@PathVariable Integer id) throws Exception {
        Audiencia obj = service.findById(id);
        AudienciaDTO dto = modelMapper.map(obj, AudienciaDTO.class);
        EntityModel<AudienciaDTO> entityModel = EntityModel.of(dto);
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(AudienciaController.class).findById(id)).withRel("audiencia-self-info"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(AudienciaController.class).findAll()).withRel("audiencia-all-list"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(CasoController.class).findAll()).withRel("casos-list"));
        return entityModel;
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Audiencia>> listPageable(Pageable pageable){
        Page<Audiencia> page =service.listPage(pageable);
        return ResponseEntity.ok(page);
    }
}
