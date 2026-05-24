package com.heavylink.controller;


import java.net.URI;
import java.util.List;


import com.heavylink.dto.DocumentosLegalesDTO;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heavylink.model.DocumentosLegales;
import com.heavylink.service.IDocumentosLegalesService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
@RequiredArgsConstructor
@RequestMapping("/documentos")
@CrossOrigin(origins = "*")
public class DocumentosLegalesController {

    private final IDocumentosLegalesService service;

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
    public ResponseEntity<Void> save(@RequestBody DocumentosLegalesDTO dto) throws Exception {
        DocumentosLegales obj = service.save(modelMapper.map(dto, DocumentosLegales.class));

        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        //localhost:9090/categories/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdDocumento()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentosLegalesDTO> update(@PathVariable Integer id, @RequestBody DocumentosLegalesDTO dto) throws Exception {
        DocumentosLegales obj = service.update(modelMapper.map(dto, DocumentosLegales.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, DocumentosLegalesDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
