package com.alfonso.service.JPA;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alfonso.interfaces.IPeliculasService;
import com.alfonso.model.Pelicula;
import com.alfonso.repository.PeliculaRepository;

@Service //Anotacion que indica que es un servicio
public class PeliculasServiceJPA implements IPeliculasService {

	@Autowired //Inyeccion de independencia
	private PeliculaRepository peliculasRepo;
	
	@Override //Anotacion que sobre escribe un metodo
	public List<Pelicula> buscarTodas() {
		List<Pelicula> lista = peliculasRepo.findAll();
		return lista;
	}

	@Override //Anotacion que sobre escribe un metodo
	public Pelicula buscarPorId(int idPelicula) {
		//Se utiliza el metodo findByAll, el cual devuelve un optional
		Optional<Pelicula> pelicula = peliculasRepo.findById(idPelicula);
		//Se retorna la pelicula en caso de que el optional tenga un valor presente
		if(pelicula.isPresent()) {
			return pelicula.get();
		}
		return null;
	}

	@Override
	public void insertar(Pelicula pelicula) {
		peliculasRepo.save(pelicula);		
	}

	@Override //Anotacion que sobre escribe un metodo
	public List<String> buscarGeneros() {
		List<String> generos = new LinkedList<>();
		generos.add("Accion");
		generos.add("Aventura");
		generos.add("Clasicas");
		generos.add("Comedias Romanticas");
		generos.add("Dramas");
		generos.add("Terror");
		generos.add("Infantil");
		generos.add("Accion y Aventura");
		generos.add("Romanticas");
		generos.add("Ciencia Ficción");
	
		return generos;
	}

	// se crea un metodo para eliminar y de parametros tiene un id
	@Override
	public void eliminar(int idDelete) {
		peliculasRepo.deleteById(idDelete);
	}

	@Override
	public Page<Pelicula> peliculas(Pageable page) {
		return peliculasRepo.findAll(page);
	}

	@Override
	public List<Pelicula> buscarPorFecha(Date fecha) {
		return peliculasRepo.findByFechaEstreno(fecha);
	}
}
