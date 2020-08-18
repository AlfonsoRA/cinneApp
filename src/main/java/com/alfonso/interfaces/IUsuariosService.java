package com.alfonso.interfaces;

import java.util.List;

import com.alfonso.model.Usuarios;

public interface IUsuariosService {
	void guardar(Usuarios usuario);
	List<Usuarios> mostrarUsuarios();
	void eliminar(int id);
	Usuarios buscarId(int id);
}
