package com.alfonso.service.JPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alfonso.interfaces.IDetalleService;
import com.alfonso.model.Detalle;
import com.alfonso.repository.DetalleRepository;

@Service
public class DetalleServiceJPA implements IDetalleService {

	@Autowired
	private DetalleRepository repo;

	@Override
	public void insertar(Detalle detalle) {
		// TODO Auto-generated method stub
		repo.save(detalle);
	}
	
	@Override
	public void eliminar(int idDetalle) {
		repo.deleteById(idDetalle);		
	}
	

}
