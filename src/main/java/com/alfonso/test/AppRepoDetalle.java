package com.alfonso.test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alfonso.model.Detalle;
import com.alfonso.repository.DetalleRepository;

public class AppRepoDetalle {

	public static void main(String[] args) {
		
		//Crea el contexto de la aplicacion
		ClassPathXmlApplicationContext contexto = new ClassPathXmlApplicationContext("root-context.xml");
		//Se crea la intancia a nuestro repositorio		
		DetalleRepository repositorio = contexto.getBean("DetalleRepository", DetalleRepository.class);
		
		// se crea una lista y se llena con el metodo buscar todas
		List<Detalle> lista = repositorio.findAll();
		
		for (Detalle detalle: lista) {
			System.out.println(detalle);
		}
		
		contexto.close();
	}

}
