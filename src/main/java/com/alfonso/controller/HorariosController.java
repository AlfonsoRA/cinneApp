package com.alfonso.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alfonso.interfaces.IHorariosService;
import com.alfonso.interfaces.IPeliculasService;
import com.alfonso.model.Horario;
import com.alfonso.model.Pelicula;
import com.alfonso.utileria.Utileria;

@Controller
@RequestMapping ("/horarios")
public class HorariosController {
	
	@Autowired
	IHorariosService serviceHorarios;
	
	@Autowired
	IPeliculasService servicePeliculas;
	
	// mapeamos la solicitud
	@RequestMapping("/create")
	public String crear(@ModelAttribute Horario horario, Model modelo) {
		//se crea una lista y se llena con el metodo de la interfaz
		List<Pelicula> listPeliculas = servicePeliculas.buscarTodas();
		modelo.addAttribute("peliculas", listPeliculas);
		return "horarios/formHorario";
	}
	
	// mapeamos la solicitud
	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Horario horario, BindingResult resultado, RedirectAttributes redirect, HttpServletRequest request) {
		
		// se recorre la lista de errores generada por el BindingResult en caso de
		// fallas
		// con el DataBinding
		for (ObjectError error : resultado.getAllErrors()) {
			System.out.println("Error: " + error.getDefaultMessage());
			return "horarios/formHorario";
		}

		//Mandamos a la vista un objeto flash que muestra un mensaje 
		serviceHorarios.guardar(horario);
		redirect.addFlashAttribute("mensaje", "Su horario fue insertado exitosamente");
		System.out.println("Horario:  " + horario);
		return "redirect:/horarios/indexPagina";
	}
	
	@GetMapping(value ="indexPagina")
	public String indicePaginado(Model modelo, Pageable page) {
		// se llama al metodo de la implementacion
		Page<Horario> lista = serviceHorarios.horarios(page);
		modelo.addAttribute("horarios", lista);
		return "horarios/listHorarios";
	}	
//	@GetMapping(value="/index")
//	public String index(Model modelo) {
//		//se crea una lista y se llena con el metodo de la interfaz
//		List<Horario> listaHorario = serviceHorarios.buscarTodos();
//		modelo.addAttribute("horarios", listaHorario);
//		return "horarios/listHorarios";
//	}

	@GetMapping(value="/edit/{id}")
	public String editHorario(@PathVariable("id") int idHorario, Model modelo) {
		// se busca un horario por id
		Horario horario = serviceHorarios.buscarPorId(idHorario);
		List<Pelicula> listPeliculas = servicePeliculas.buscarTodas();
		modelo.addAttribute("peliculas", listPeliculas); 
		modelo.addAttribute("horario", horario);
		return "horarios/formHorario";
	}
	
	@GetMapping(value="/delete/{id}")	
	public String eliminarHorario(@PathVariable("id") int idHorario, RedirectAttributes atributo) {
		serviceHorarios.deleteHorario(idHorario);
		atributo.addFlashAttribute("mensajeEliminado", "El horario fue eliminado.");
		return "redirect:/horarios/indexPagina";
	}
	
	/**
	 * Personalizamos el Data Binding para todas las propiedades de tipo Date
	 * @param binder
	 */
	@InitBinder("horario")
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));		
	}
}
