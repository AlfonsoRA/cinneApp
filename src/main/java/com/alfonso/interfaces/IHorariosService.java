package com.alfonso.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alfonso.model.Horario;
import com.alfonso.model.Pelicula;

public interface IHorariosService {
	List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha);
	List<Horario> buscarTodos();
	void guardar(Horario horario);
	Horario buscarPorId(int idHorario);
	void deleteHorario(int idHorario);
	Page<Horario> horarios(Pageable page);
	List<Pelicula> buscarPorFecha(Date fecha);
}
