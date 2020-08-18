package com.alfonso.test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alfonso.model.Pelicula;
import com.alfonso.repository.PeliculaRepository;

public class AppFindAll {

	public static void main(String[] args) {
		
		//Crea el contexto de la aplicacion
		ClassPathXmlApplicationContext contexto = new ClassPathXmlApplicationContext("root-context.xml");
		//Se crea la intancia a nuestro repositorio
		PeliculaRepository repositorio = contexto.getBean("PeliculaRepository", PeliculaRepository.class);
		
		
		//se usa el metodo del repositorio para buscar todos
		List<Pelicula> lista = repositorio.findAll();
		
		for (Pelicula pelicula : lista) {
			System.out.println(pelicula);
		}
		
		
		
		
		
		
		
		contexto.close();
	}

}
