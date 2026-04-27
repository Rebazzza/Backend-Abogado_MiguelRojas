package com.heavylink.service;

import java.util.List;

import com.heavylink.model.AreaDerecho;

public interface IAreasDerechoService {
	AreaDerecho save(AreaDerecho area) throws  Exception;
	AreaDerecho update(AreaDerecho area, Integer id) throws  Exception;
    List<AreaDerecho> findAll() throws  Exception;
    AreaDerecho findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;

}
