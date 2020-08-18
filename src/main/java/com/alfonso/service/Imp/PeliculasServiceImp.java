package com.alfonso.service.Imp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alfonso.interfaces.IPeliculasService;
import com.alfonso.model.Horario;
import com.alfonso.model.Pelicula;
 
//@Service //Anotacion que indica que es un servicio
public class PeliculasServiceImp implements IPeliculasService {

	
	//Se instancia la lista pelicula de manera global
	private List<Pelicula> lista = null;
	
	//Constructor 
	public PeliculasServiceImp() {
		
		
		//Se instancia un formato de fechas 
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		//manejamos la exception
		try {
			
			lista = new LinkedList<>();
			//creamos un objeto pelicula 
			Pelicula pelicula1 = new Pelicula();
			// seteamos los atributos del objeto
			pelicula1.setId(1);
			pelicula1.setTitulo("Aliens");
			pelicula1.setDuracion(136);
			pelicula1.setClasificacion("C");
			pelicula1.setGenero("ficcion");
			pelicula1.setFechaEstreno(sdf.parse("20-02-2019"));
			
			//creamos un objeto pelicula
			Pelicula pelicula2 = new Pelicula();
			// seteamos los atributos del objeto
			pelicula2.setId(5);
			pelicula2.setTitulo("Batman");
			pelicula2.setDuracion(153);
			pelicula2.setClasificacion("B");
			pelicula2.setGenero("Accion");
			pelicula2.setFechaEstreno(sdf.parse("10-05-2017"));
			
			//creamos un objeto pelicula
			Pelicula pelicula3 = new Pelicula();
			// seteamos los atributos del objeto
			pelicula3.setId(3);
			pelicula3.setTitulo("Cars");
			pelicula3.setDuracion(116);
			pelicula3.setClasificacion("A");
			pelicula3.setGenero("Infantil");
			pelicula3.setFechaEstreno(sdf.parse("28-10-2015"));
			
			// añadimos los objetos a la lista
			lista.add(pelicula1);
			lista.add(pelicula2);
			lista.add(pelicula3);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Creamos metodo que retorna una lista de peliculas 
	@Override //Anotacion que sobre escribe metodos 
	public List<Pelicula> buscarTodas() {
		// TODO Auto-generated method stub
		return lista;
	}
	
	//Creamos metodo que busca una pelicula por el ID
	@Override //Anotacion que sobre escribe metodos 
	public Pelicula buscarPorId(int idPelicula) {
			//Creamos bucle que recorre toda la lisa
			for(Pelicula p: lista) {
				//validamos si el id de la pelicula es igual al id que pone el usuario 
				if(p.getId()== idPelicula) {
					//Retorna el objeto pelicula 
					return p;
				}
			}
			return null;
		}

	@Override //Anotacion que sobre escribe metodos 
	public void insertar(Pelicula pelicula) {
		// TODO Auto-generated method stub
		lista.add(pelicula);
	}

	@Override //Anotacion que sobre escribe metodos 
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

	@Override
	public void eliminar(int idDelete) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Pelicula> peliculas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha) {
		return null;
	}

	@Override
	public List<Pelicula> buscarPorFecha(Date fecha) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
