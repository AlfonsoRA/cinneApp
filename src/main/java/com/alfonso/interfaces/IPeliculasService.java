package com.alfonso.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alfonso.model.Pelicula;

public interface IPeliculasService {
	List<Pelicula> buscarTodas();
	Pelicula buscarPorId(int idPelicula);
	void insertar(Pelicula pelicula);
	List<String> buscarGeneros();
	void eliminar(int idDelete);
	Page<Pelicula> peliculas(Pageable page);
	List<Pelicula> buscarPorFecha(Date fecha);
}
