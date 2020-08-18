package com.alfonso.service.Imp;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alfonso.interfaces.IBannerService;
import com.alfonso.model.Banner;

//@Service //Anotacion que indica que es un servicio
public class BannerServiceImp implements IBannerService {
	
	List<Banner> listaBanner = null;
	
	public BannerServiceImp() {
		
		listaBanner= new LinkedList();
		Banner banner1 = new Banner();
		banner1.setId(3);
		banner1.setTitulo("Alien Covenant"
				+ "");
		banner1.setArchivo("slide6.jpg");
		
		Banner banner2 = new Banner();
		banner2.setId(2);
		banner2.setTitulo("La Bella y la Bestia");
		banner2.setArchivo("slide2.jpg");
		
		Banner banner3 = new Banner();
		banner3.setId(3);
		banner3.setTitulo("Spider-man");
		banner3.setArchivo("slide3.jpg");
		
		listaBanner.add(banner1);
		listaBanner.add(banner2);
		listaBanner.add(banner3);
	
	}
	
	
	@Override //Anotacion que sobre escribe metodos 
	public void insertar(Banner banner) {
		listaBanner.add(banner);		
	}

	@Override //Anotacion que sobre escribe metodos 
	public List<Banner> buscarTodos() {
		// TODO Auto-generated method stub
		return listaBanner;
	}


	@Override
	public Banner buscarPorId(int idBanner) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void eliminar(int idEliminar) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Page<Banner> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Banner> buscarEstatus(String estatus) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
