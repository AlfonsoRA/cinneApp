package com.alfonso.service.Imp;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alfonso.interfaces.INoticiasService;
import com.alfonso.model.Noticia;

//@Service //Anotacion que indica que es un servicio
public class NoticiasServiceImp implements INoticiasService {

	@Override //Anotacion que sobre escribe metodos 
	public void guardar(Noticia noticia) {
		// TODO Auto-generated method stub
		System.out.println(noticia.toString());
	}

	@Override
	public List<Noticia> buscarTodas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Noticia> buscarTodas(Pageable pagina) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Noticia buscarPorId(int idNoticia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteNoticia(int idNoticia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Noticia> buscarEstatus(String estatus) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
