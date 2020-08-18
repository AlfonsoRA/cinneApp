package com.alfonso.service.JPA;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alfonso.interfaces.IPerfilesService;
import com.alfonso.model.Perfil;
import com.alfonso.repository.PerfilesRepository;

@Service
public class PerfilesServiceJPA implements IPerfilesService{

	@Autowired
	PerfilesRepository perfilesRepo;
	
	@Override
	public void guardar(Perfil perfil) {
		// TODO Auto-generated method stub
		perfilesRepo.save(perfil);
	}

	@Override
	public List<Perfil> buscarPerfil(String cuenta) {
		return perfilesRepo.findByCuenta(cuenta);
	}
}
