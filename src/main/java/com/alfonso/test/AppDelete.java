package com.alfonso.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alfonso.repository.NoticiaRepository;

public class AppDelete {

	public static void main(String[] args) {
		//Crea el contexto de la aplicacion
		ClassPathXmlApplicationContext contexto = new ClassPathXmlApplicationContext("root-context.xml");
		//Se crea la intancia a nuestro repositorio
		NoticiaRepository repositorio= contexto.getBean("noticiaRepository", NoticiaRepository.class);
		if (repositorio.existsById(32)) {
			repositorio.deleteById(32);
		}
		
		Long numero= repositorio.count();
		// se usa el metodo para borrar todos
		repositorio.deleteAll();
		
		System.out.println("Se encontraron "+numero+" Noticias.");
		
		contexto.close();
		
	}
}
