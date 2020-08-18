package com.alfonso.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.alfonso.model.Noticia;
import com.alfonso.repository.NoticiaRepository;

public class AppRead {

	public static void main(String[] args) {
		//Crea el contexto de la aplicacion
		ClassPathXmlApplicationContext contexto = new ClassPathXmlApplicationContext("root-context.xml");
		//Se crea la intancia a nuestro repositorio
		NoticiaRepository repositorio = contexto.getBean("noticiaRepository", NoticiaRepository.class);
		
		// se usa el obj optional para evitar la exception nullPointer
		Optional<Noticia> noticia = repositorio.findById(32);
		
		// se usa la interfaz iterable para recorrer la lista
	//	Iterable<Noticia> it = repositorio.findAll();
	//	for (Noticia noticia2 : it) {
	//		System.out.println(noticia2);
	//		
	//	}
		
		// Lista ordenada ==> Sort
//		List<Noticia> noticia1= repositorio.findAll(Sort.by("titulo").descending()); 
//		for (Noticia noticia2 : noticia1) {
//			System.out.println(noticia2);
//		}
//		
		
		//Lista ordenada por mas de un campo
//		List<Noticia> noticia2= repositorio.findAll(Sort.by("fecha").descending().by("titulo").ascending());
//		for (Noticia noticia3 : noticia2) {
//			System.out.println(noticia3);
//		}
		
		
		//se utiliza el metodo page al que se le manda dos parametros uno es el numero de
			//la pagina y la cantidad de registros por pagina
//		Page<Noticia> pagina = repositorio.findAll(PageRequest.of(1, 5, Sort.by("Titulo").descending()));
//		
//		for (Noticia noticia7 : pagina) {
//			System.out.println(noticia7);
//		}
		
		
//		List<Noticia> lista = repositorio.findByEstatus("Activa");
//		
//		for (Noticia noticia2 : lista) {
//			System.out.println(noticia2);
//		}
		
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		List<Noticia> lista;
//		try {
//			lista = repositorio.findByFecha(sdf.parse("2017-09-01"));
//			for (Noticia noticia1 : lista) {
//				System.out.println(noticia);
//			}
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		
//		List<Noticia> lista = null;
//		
//		lista = repositorio.findByEstatusAndId("Inactiva",31);
//		for (Noticia noticia2 : lista) {
//			System.out.println(noticia2);
//		}
	
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		
//		List<Noticia> lista = null;
//		try {
//			lista = repositorio.findByEstatusOrFecha("Inactiva", sdf.parse("2017-09-01"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		for (Noticia noticia1 : lista) {
//			System.out.println(noticia1);
//		}
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		List<Noticia> lista = null;
		try {
			
			// se usa un query method  para buscar entre dos fechas
			lista = repositorio.findByFechaBetween(sdf.parse("2017-09-01"), sdf.parse("2017-09-06"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Noticia noticia1 : lista) {
			System.out.println(noticia1);
		}
	
	
	}
}
