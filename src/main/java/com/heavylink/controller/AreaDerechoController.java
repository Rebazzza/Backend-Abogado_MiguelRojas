package com.heavylink.controller;


import java.net.URI;
import java.util.List;


import com.heavylink.dto.AreaDerechoDTO;

import com.heavylink.model.Abogado;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heavylink.model.AreaDerecho;
import com.heavylink.service.IAreasDerechoService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/areas")
@CrossOrigin(origins = "*")
public class AreaDerechoController {

    private final IAreasDerechoService service;
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
    public ResponseEntity<Void> save(@RequestBody AreaDerechoDTO dto) throws Exception {
        AreaDerecho obj = service.save(modelMapper.map(dto, AreaDerecho.class));

        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        //localhost:9090/categories/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdArea()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaDerechoDTO> update(@PathVariable Integer id, @RequestBody AreaDerechoDTO dto) throws Exception {
        AreaDerecho obj = service.update(modelMapper.map(dto, AreaDerecho.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, AreaDerechoDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/pageable")
    public ResponseEntity<Page<AreaDerecho>> listPageable(Pageable pageable){
        Page<AreaDerecho> page =service.listPage(pageable);
        return ResponseEntity.ok(page);
    }

}
