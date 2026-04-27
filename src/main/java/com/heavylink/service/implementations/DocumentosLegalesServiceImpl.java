package com.heavylink.service.implementations;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.heavylink.repository.IDocumentosLegales;
import com.heavylink.model.DocumentosLegales;
import com.heavylink.service.IDocumentosLegalesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentosLegalesServiceImpl implements IDocumentosLegalesService {

    private final IDocumentosLegales repository;

    @Override
    public DocumentosLegales save(DocumentosLegales documentos) throws Exception {
        return repository.save(documentos);
    }

    @Override
    public DocumentosLegales update(DocumentosLegales documentos, Integer id) throws Exception {
        DocumentosLegales actual = findById(id);
        BeanUtils.copyProperties(documentos, actual, "idDocumento");
        return repository.save(actual);
    }

    @Override
    public List<DocumentosLegales> findAll() throws Exception {
        return repository.findAll();
    }

    @Override
    public DocumentosLegales findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Documento legal no encontrado con id: " + id));
    }

    @Override
    public void delete(Integer id) throws Exception {
        repository.delete(findById(id));
    }
}
