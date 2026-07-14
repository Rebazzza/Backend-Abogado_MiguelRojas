package com.heavylink.controller;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.heavylink.Repository.ICasos;
import com.heavylink.Repository.IDocumentosLegales;
import com.heavylink.dto.ExpedienteDTO;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.heavylink.model.DocumentosLegales;
import com.heavylink.model.Expediente;
import com.heavylink.service.IExpedienteService;
import java.lang.reflect.Method;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expedientes")
public class ExpedienteController {

    private final IExpedienteService service;
    private final ICasos casoRepo;
    private final IDocumentosLegales documentosRepo;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    private ExpedienteDTO toDto(Expediente entity) {
        ExpedienteDTO dto = modelMapper.map(entity, ExpedienteDTO.class);
        if (entity.getCaso() != null) {
            dto.setIdCaso(entity.getCaso().getIdCaso());
        }
        return dto;
    }

    private Object invokeService(String[] candidates, Class<?>[] paramTypes, Object[] args) throws Exception {
        for (String name : candidates) {
            try {
                Method m = IExpedienteService.class.getMethod(name, paramTypes);
                return m.invoke(service, args);
            } catch (NoSuchMethodException ignored) {
            }
        }
        throw new NoSuchMethodException("No compatible method found in IExpedienteService");
    }

    private Expediente toEntity(ExpedienteDTO dto) {
        Expediente entity = modelMapper.map(dto, Expediente.class);
        if (dto.getIdCaso() != null) {
            casoRepo.findById(dto.getIdCaso()).ifPresent(entity::setCaso);
        }
        return entity;
    }

    private void savePdfDocument(Expediente expediente, MultipartFile pdf) throws Exception {
        if (pdf == null || pdf.isEmpty()) {
            return;
        }
        String contentType = pdf.getContentType();
        String originalName = pdf.getOriginalFilename() == null ? "expediente.pdf" : pdf.getOriginalFilename();
        if ((contentType == null || !contentType.equalsIgnoreCase(MediaType.APPLICATION_PDF_VALUE))
                && !originalName.toLowerCase().endsWith(".pdf")) {
            throw new IllegalArgumentException("El archivo debe ser un PDF");
        }

        Path folder = Paths.get("uploads", "expedientes", String.valueOf(expediente.getIdExPediente()));
        Files.createDirectories(folder);
        String safeName = originalName.replaceAll("[^a-zA-Z0-9._-]", "_");
        String fileName = UUID.randomUUID() + "_" + safeName;
        Path target = folder.resolve(fileName);
        Files.copy(pdf.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        DocumentosLegales documento = new DocumentosLegales();
        documento.setNombreArchivo(originalName.length() > 70 ? originalName.substring(0, 70) : originalName);
        documento.setRuta(target.toString().replace("\\", "/"));
        documento.setFechaCreacion(LocalDateTime.now());
        documento.setExpediente(expediente);
        documentosRepo.save(documento);
    }

    @GetMapping
    public ResponseEntity<List<ExpedienteDTO>> findAll() throws Exception {
        Object result = invokeService(new String[]{"findAll","listar","getAll","listAll"}, new Class<?>[] {}, new Object[] {});
        @SuppressWarnings("unchecked")
        List<Expediente> entities = (List<Expediente>) result;
        List<ExpedienteDTO> list = entities.stream().map(this::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpedienteDTO> findById(@PathVariable Integer id) throws Exception {
        Object result = invokeService(new String[]{"findById","getById","findOne"}, new Class<?>[]{Integer.class}, new Object[]{id});
        Expediente obj = (Expediente) result;
        return ResponseEntity.ok(toDto(obj));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ExpedienteDTO dto) throws Exception {
        Expediente entity = toEntity(dto);
        Expediente obj = service.save(entity);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdExPediente()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public ResponseEntity<Void> saveWithPdf(
            @Valid @RequestPart("expediente") ExpedienteDTO dto,
            @RequestPart(value = "pdf", required = false) MultipartFile pdf) throws Exception {
        Expediente obj = service.save(toEntity(dto));
        savePdfDocument(obj, pdf);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdExPediente()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpedienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ExpedienteDTO dto) throws Exception {
        Expediente toUpdate = toEntity(dto);
        Object result = invokeService(new String[]{"update","actualizar","edit"}, new Class<?>[]{Expediente.class,Integer.class}, new Object[]{toUpdate,id});
        Expediente obj = (Expediente) result;
        return ResponseEntity.ok(toDto(obj));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public ResponseEntity<ExpedienteDTO> updateWithPdf(
            @PathVariable Integer id,
            @Valid @RequestPart("expediente") ExpedienteDTO dto,
            @RequestPart(value = "pdf", required = false) MultipartFile pdf) throws Exception {
        Expediente toUpdate = toEntity(dto);
        Object result = invokeService(new String[]{"update","actualizar","edit"}, new Class<?>[]{Expediente.class,Integer.class}, new Object[]{toUpdate,id});
        Expediente obj = (Expediente) result;
        savePdfDocument(obj, pdf);
        return ResponseEntity.ok(toDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        invokeService(new String[]{"delete","remove","eliminar"}, new Class<?>[]{Integer.class}, new Object[]{id});
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<ExpedienteDTO> findByIdHateoas(@PathVariable Integer id) throws Exception {
        Object result = invokeService(new String[]{"findById","getById","findOne"}, new Class<?>[]{Integer.class}, new Object[]{id});
        Expediente obj = (Expediente) result;
        ExpedienteDTO dto = toDto(obj);
        EntityModel<ExpedienteDTO> entityModel = EntityModel.of(dto);
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ExpedienteController.class).findById(id)).withRel("expediente-self-info"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ExpedienteController.class).findAll()).withRel("expediente-all-list"));
        entityModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(CasoController.class).findAll()).withRel("casos-list"));
        return entityModel;
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Expediente>> listPageable(Pageable pageable){
        Page<Expediente> page = service.listPage(pageable);
        return ResponseEntity.ok(page);
    }
}
