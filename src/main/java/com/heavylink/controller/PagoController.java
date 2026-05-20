package com.heavylink.controller;

import java.net.URI;
import java.util.List;

import com.heavylink.dto.NotificacionDTO;
import com.heavylink.dto.PagoDTO;
import com.heavylink.model.Notificacion;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heavylink.model.Pago;
import com.heavylink.service.IPagoService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pagos")
@CrossOrigin(origins = "*")
public class PagoController {

    private final IPagoService service;


    ModelMapper modelMapper = new ModelMapper();
    @GetMapping
    public ResponseEntity<List<PagoDTO>> findAll() throws Exception {
        List<PagoDTO> list = service.findAll().stream().map(e -> modelMapper.map(e, PagoDTO.class)).toList();

        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> findById(@PathVariable Integer id) throws Exception {
        Pago obj = service.findById(id);

        return ResponseEntity.ok(modelMapper.map(obj,PagoDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody PagoDTO dto) throws Exception {
        Pago obj = service.save(modelMapper.map(dto, Pago.class));

        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        //localhost:9090/categories/4
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPago()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> update(@PathVariable Integer id, @RequestBody PagoDTO dto) throws Exception {
        Pago obj = service.update(modelMapper.map(dto, Pago.class), id);
        return ResponseEntity.ok(modelMapper.map(obj, PagoDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
