package com.heavylink.service;

import java.util.List;

import com.heavylink.model.DocumentosLegales;

public interface IDocumentosLegalesService {
	DocumentosLegales save(DocumentosLegales documentos) throws  Exception;
	DocumentosLegales update(DocumentosLegales documentos, Integer id) throws  Exception;
    List<DocumentosLegales> findAll() throws  Exception;
    DocumentosLegales findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
}
