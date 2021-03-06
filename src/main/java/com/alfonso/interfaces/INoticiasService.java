package com.alfonso.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alfonso.model.Noticia;

public interface INoticiasService {
	void guardar(Noticia noticia);
	List<Noticia> buscarTodas();
	Page<Noticia> buscarTodas(Pageable pagina);
	Noticia buscarPorId(int idNoticia);
	void deleteNoticia(int idNoticia);
	List<Noticia> buscarEstatus(String estatus);
}
