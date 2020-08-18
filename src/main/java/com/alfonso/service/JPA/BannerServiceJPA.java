package com.alfonso.service.JPA;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.alfonso.interfaces.IBannerService;
import com.alfonso.model.Banner;
import com.alfonso.repository.BannersRepository;

@Service
public class BannerServiceJPA implements IBannerService {

	@Autowired
	private BannersRepository bannersRepo;
	
	@Override
	public void insertar(Banner banner) {
		bannersRepo.save(banner);
		
	}

	@Override
	public List<Banner> buscarTodos() {
		return bannersRepo.findAll();
	}

	@Override
	public Banner buscarPorId(int idBanner) {
		Optional<Banner> banner = bannersRepo.findById(idBanner);
		if(banner.isPresent()) {
			return banner.get();
		}
		return null;
	}

	@Override
	public void eliminar(int idEliminar) {
		bannersRepo.deleteById(idEliminar);		
	}

	@Override
	public Page<Banner> buscarTodas(Pageable pagina) {
		return bannersRepo.findAll(pagina);
	}

	@Override
	public List<Banner> buscarEstatus(String estatus) {
		return bannersRepo.findByEstatus(estatus);
	}

}
