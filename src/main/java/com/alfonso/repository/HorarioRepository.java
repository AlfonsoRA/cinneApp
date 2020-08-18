package com.alfonso.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alfonso.model.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer>{
	
	List<Horario> findByPelicula_IdAndFechaOrderByHora(int idPelicula, Date fecha);
	
	List<Horario> findByFecha(Date fecha);
	
}
