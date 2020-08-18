package com.alfonso.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alfonso.model.Noticia;
import com.alfonso.repository.NoticiaRepository;

public class AppCreate {

	public static void main(String[] args) {
		//Crea el contexto de la aplicacion
		ClassPathXmlApplicationContext contexto = new ClassPathXmlApplicationContext("root-context.xml");
		//Se crea la intancia a nuestro repositorio
		NoticiaRepository repositorio= contexto.getBean("noticiaRepository", NoticiaRepository.class);
		
		Noticia noticia = new Noticia();
		noticia.setTitulo("Proximo estreno: superman 1");
		noticia.setDetalle("El mes de septiembre es el gran estreno de Superman.........!");
		// se usa el metodo save del repositorio
		repositorio.save(noticia);
		contexto.close();
	}

}
