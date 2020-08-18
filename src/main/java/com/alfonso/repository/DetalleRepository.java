package com.alfonso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alfonso.model.Detalle;

@Repository //Anotacion que indica que es una interface repositorio
public interface DetalleRepository extends JpaRepository<Detalle, Integer> {
		
}
