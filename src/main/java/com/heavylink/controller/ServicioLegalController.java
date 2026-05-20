package com.heavylink.controller;


import java.net.URI;
import java.util.List;


import com.heavylink.dto.PagoDTO;
import com.heavylink.dto.ServicioLegalDTO;
import com.heavylink.model.Pago;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heavylink.model.ServicioLegal;
import com.heavylink.service.IServiciosLegalesService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/servicios")
@CrossOrigin(origins = "*")
public class ServicioLegalController {

    private final IServiciosLegalesService service;

    ModelMapper modelMapper = new ModelMapper();
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
    public ResponseEntity<Void> save(@RequestBody ServicioLegalDTO dto) throws Exception {
        ServicioLegal obj = service.save(modelMapper.map(dto, ServicioLegal.class));

        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        //localhost:9090/categories/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdServicio()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioLegalDTO> update(@PathVariable Integer id, @RequestBody ServicioLegalDTO dto) throws Exception {
        ServicioLegal obj = service.update(modelMapper.map(dto, ServicioLegal.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, ServicioLegalDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
