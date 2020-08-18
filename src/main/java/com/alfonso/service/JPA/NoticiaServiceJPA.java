package com.alfonso.service.JPA;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alfonso.interfaces.INoticiasService;
import com.alfonso.model.Noticia;
import com.alfonso.repository.NoticiaRepository;

@Service
public class NoticiaServiceJPA implements INoticiasService {
	
	@Autowired
	private NoticiaRepository noticiasRepo;

	@Override
	public void guardar(Noticia noticia) {
		noticiasRepo.save(noticia);		
	}

	@Override
	public List<Noticia> buscarTodas() {
		return noticiasRepo.findAll();
	}

	@Override
	public Page<Noticia> buscarTodas(Pageable pagina) {
		return noticiasRepo.findAll(pagina);
	}
	
	@Override
	public Noticia buscarPorId(int idNoticia) {
		Optional<Noticia> noti = noticiasRepo.findById(idNoticia);
		
		if(noti.isPresent()) {
			return noti.get();
		}
		
		return null;
	}
	
	@Override
	public void deleteNoticia(int idNoticia) {
		noticiasRepo.deleteById(idNoticia);
		
	}

	@Override
	public List<Noticia> buscarEstatus(String estatus) {
		return noticiasRepo.findByEstatus(estatus);
	}
}
