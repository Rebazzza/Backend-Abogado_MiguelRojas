package com.heavylink.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heavylink.model.Pago;

public interface IPago extends JpaRepository<Pago, Integer> {

}
