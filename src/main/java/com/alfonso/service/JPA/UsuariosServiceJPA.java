package com.alfonso.service.JPA;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alfonso.interfaces.IUsuariosService;
import com.alfonso.model.Pelicula;
import com.alfonso.model.Usuarios;
import com.alfonso.repository.UsuarioRepository;

@Service
public class UsuariosServiceJPA implements IUsuariosService{

	//Inyeccion de dependencia del repository
	@Autowired
	UsuarioRepository usuariosRepo;
	
	@Override
	public void guardar(Usuarios usuario) {
		usuariosRepo.save(usuario);
	}

	@Override
	public List<Usuarios> mostrarUsuarios() {
		return 	usuariosRepo.findAll();
	}

	@Override
	public void eliminar(int id) {
		usuariosRepo.deleteById(id);
	}

	@Override //Anotacion que sobre escribe un metodo
	public Usuarios buscarId(int id) {
		//Se utiliza el metodo findByAll, el cual devuelve un optional
		Optional<Usuarios> usuario = usuariosRepo.findById(id);
		//Se retorna la pelicula en caso de que el optional tenga un valor presente
		if(usuario.isPresent()) {
			return usuario.get();
		}
		return null;
	}
	
	

}
