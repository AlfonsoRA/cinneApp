package com.alfonso.test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alfonso.model.Horario;
import com.alfonso.repository.HorarioRepository;

public class AppRepoHorario {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		HorarioRepository repo = context.getBean("horarioRepository", HorarioRepository.class);
		List<Horario> lista= repo.findAll();
		for (Horario horario: lista) {
			System.out.println(horario);
		}
		
		context.close();
		
	}

}
