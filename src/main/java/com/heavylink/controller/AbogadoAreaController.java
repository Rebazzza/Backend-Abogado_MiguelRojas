package com.heavylink.controller;

import java.net.URI;
import java.util.List;

import com.heavylink.dto.AbogadoAreaDTO;
import com.heavylink.model.Abogado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

import com.heavylink.model.AbogadoArea;
import com.heavylink.service.IAbogadoAreaService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/abogado-areas")
@CrossOrigin(origins = "*")
public class AbogadoAreaController {

    private final IAbogadoAreaService service;

    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<AbogadoAreaDTO>> findAll() throws Exception {
        List<AbogadoAreaDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, AbogadoAreaDTO.class)).toList();

        return ResponseEntity.ok(list);
    }
    @PreAuthorize("@authorizeLogic.hasAccess('findAll')")
    @GetMapping
    public ResponseEntity<AbogadoAreaDTO> findById(@PathVariable Integer id) throws Exception {
        AbogadoArea obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj,AbogadoAreaDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AbogadoAreaDTO dto) throws Exception {
        AbogadoArea obj = service.save(modelMapper.map(dto, AbogadoArea.class));

        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        //localhost:9090/categories/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdAbogadoArea()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbogadoAreaDTO> update(@PathVariable Integer id, @RequestBody AbogadoAreaDTO dto) throws Exception {
        AbogadoArea obj = service.update(modelMapper.map(dto, AbogadoArea.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, AbogadoAreaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
    


}
