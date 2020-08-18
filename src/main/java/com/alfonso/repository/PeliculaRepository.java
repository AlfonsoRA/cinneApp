package com.alfonso.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alfonso.model.Pelicula;

@Repository //Anotacion que indica que es una interface repositorio
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
	
	//SELECT * FROM Pelicula WHERE fechaEstreno = ?
	// SELECT p FROM Pelicula p WHERE p.fechaEstreno = ?
	List<Pelicula> findByFechaEstreno(Date fecha);
	
}
