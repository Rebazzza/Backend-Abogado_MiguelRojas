package com.heavylink.service;

import java.util.List;

import com.heavylink.model.Area_Derecho;

public interface IAreasDerechoService {
	Area_Derecho save(Area_Derecho area) throws  Exception;
	Area_Derecho update(Area_Derecho area, Integer id) throws  Exception;
    List<Area_Derecho> findAll() throws  Exception;
    Area_Derecho findById(Integer id) throws  Exception;
    void delete(Integer id) throws  Exception;
	
}
