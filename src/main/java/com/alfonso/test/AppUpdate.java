package com.alfonso.test;

import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alfonso.model.Noticia;
import com.alfonso.repository.NoticiaRepository;

public class AppUpdate {

	public static void main(String[] args) {
		//Crea el contexto de la aplicacion
		ClassPathXmlApplicationContext contexto = new ClassPathXmlApplicationContext("root-context.xml");
		//Se crea la intancia a nuestro repositorio		
		NoticiaRepository repositorio= contexto.getBean("noticiaRepository", NoticiaRepository.class);
		
		// se usa el obj optional para evitar la exception nullPointer
		Optional<Noticia> noticia = repositorio.findById(31);
		
		System.out.println(noticia);
		// se usa el obj optional para evitar la exception nullPointer
		Optional<Noticia> optional= repositorio.findById(31);
		// se valida si existe
		if (optional.isPresent()) {
			Noticia news= optional.get();
			news.setEstatus("Inactiva");	
			repositorio.save(news);
			System.out.println(news);
		}
		System.out.println(repositorio.existsById(31));
		
		contexto.close();
	}

}
