package com.alfonso.service.JPA;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.alfonso.interfaces.IHorariosService;
import com.alfonso.model.Horario;
import com.alfonso.model.Pelicula;
import com.alfonso.repository.HorarioRepository;

@Service
public class HorariosServiceJPA implements IHorariosService {

	@Autowired
	HorarioRepository horariosRepo;
	
	@Override
	public void guardar(Horario horario) {
		horariosRepo.save(horario);		
	}

	@Override
	public List<Horario> buscarTodos() {
		return horariosRepo.findAll();
	}
	
	@Override
	public List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha) {
		// TODO Auto-generated method stub
		return horariosRepo.findByPelicula_IdAndFechaOrderByHora(idPelicula, fecha);	
	}

	@Override
	public Horario buscarPorId(int idHorario) {
		Optional<Horario> horario = horariosRepo.findById(idHorario);
		
		if(horario.isPresent()) {
			return horario.get();
		}
		return null;
	}

	@Override
	public void deleteHorario(int idHorario) {
		horariosRepo.deleteById(idHorario);
		
	}

	@Override
	public Page<Horario> horarios(Pageable page) {
		// TODO Auto-generated method stub
		return horariosRepo.findAll(page);
	}

	@Override
	public List<Pelicula> buscarPorFecha(Date fecha) {
		List<Horario> listaHorario= horariosRepo.findByFecha(fecha);
		List<Pelicula> listaPelicula = new LinkedList<Pelicula>();
		for (Horario horario : listaHorario) {
			 listaPelicula.add(horario.getPelicula());
		}
		return listaPelicula;
	}
	

	

}
