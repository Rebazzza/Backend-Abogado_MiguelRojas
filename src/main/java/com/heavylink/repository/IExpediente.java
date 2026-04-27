package com.heavylink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heavylink.model.Expediente;

public interface IExpediente extends JpaRepository<Expediente, Integer> {

}
