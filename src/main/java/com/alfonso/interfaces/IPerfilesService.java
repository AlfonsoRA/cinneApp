package com.alfonso.interfaces;

import java.util.List;

import com.alfonso.model.Perfil;

public interface IPerfilesService {
	void guardar(Perfil perfil);
	List<Perfil> buscarPerfil (String cuenta);
}
