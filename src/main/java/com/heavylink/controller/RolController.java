package com.heavylink.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.heavylink.model.Rol;
import com.heavylink.service.IRoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RolController {

    private final IRoleService service;

    @GetMapping
    public ResponseEntity<List<Rol>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Rol>> listPageable(Pageable pageable) {
        return ResponseEntity.ok(service.listPage(pageable));
    }
}
