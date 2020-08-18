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

import com.alfonso.interfaces.IDetalleService;
import com.alfonso.interfaces.IPeliculasService;
import com.alfonso.model.Pelicula;
import com.alfonso.utileria.Utileria;

@Controller
@RequestMapping("/pelicula")
public class PeliculasController {

	@Autowired //Para que se realice la inyeccion de dependencia
	private IPeliculasService servicePeliculas;
	@Autowired//Para que se realice la inyeccion de dependencia
	private IDetalleService serviceDetalle;
	
	// mapeamos la solicitud
	@RequestMapping("/create")
	public String crear(@ModelAttribute Pelicula pelicula, Model modelo) {
		modelo.addAttribute("generos", servicePeliculas.buscarGeneros());
		return "peliculas/formPelicula";
	}
	
	//Mapeamos por el get 
	@GetMapping(value="/edit/{id}")
	public String editar(@PathVariable("id")int idPelicula, Model modelo) {
		Pelicula pelicula = servicePeliculas.buscarPorId(idPelicula);
		//Se añade al model la lista de generos
		modelo.addAttribute("generos", servicePeliculas.buscarGeneros());
		//Se añade añ model que necesita el formulario para renderizar los datos 
		modelo.addAttribute("pelicula",pelicula);
		
		return "peliculas/formPelicula";
	}
	
	//mapeamos la solicitud 
	@GetMapping(value="/delete/{id}")
	public String eliminar(@PathVariable("id")int idEliminar, RedirectAttributes redirect) {
		//Declaramos un objeto de tipo pelicula
		Pelicula pelicula = servicePeliculas.buscarPorId(idEliminar);
		//se elimina por id una pelicula
		servicePeliculas.eliminar(idEliminar);
		//Se eliminan los detalles de la pelicula
		serviceDetalle.eliminar(pelicula.getDetalle().getId());
		//se añade un objeto de tipo RedirectAttributes para enviar mensaje en un redirect
		redirect.addFlashAttribute("mensajeEliminado", "La pelicula fue eliminada.");
		return "redirect:/pelicula/indexPagina";		
	}

	
	
	// mapeamos la solicitud
	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Pelicula pelicula, BindingResult resultado, RedirectAttributes redirect,
							@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request) {
		
		
		if(!multiPart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multiPart, request);
			pelicula.setImagen(nombreImagen);
		}
		
		serviceDetalle.insertar(pelicula.getDetalle()); 
		
		// se recorre la lista de errores generada por el BindingResult en caso de
		// fallas con el DataBinding
		for (ObjectError error : resultado.getAllErrors()) {
			System.out.println("Error: " + error.getDefaultMessage());
			return "peliculas/formPeliculas";
		}

		//Mandamos a la vista un objeto flash que muestra un mensaje 
		servicePeliculas.insertar(pelicula);
		redirect.addFlashAttribute("mensaje", "Su pelicula fue insertada exitosamente");
		
		System.out.println("Pelicula:  " + pelicula);
		return "redirect:/pelicula/indexPagina";
	}

	@GetMapping(value ="indexPagina")
	public String indicePaginado(Model modelo, Pageable page) {
		// se llama al metodo de la implementacion
		Page<Pelicula> lista = servicePeliculas.peliculas(page);
		modelo.addAttribute("peliculas", lista);
		return "peliculas/listPeliculas";
	}
	
//	@RequestMapping("/index")
//	public String mostrarIndex(Model model) {
//		// creamos una lista de peliculas y la llenamos con el metodo de la interfaz
//		List<Pelicula> lista= servicePeliculas.buscarTodas();
//		// mandamos a la vista la lista
//		model.addAttribute("peliculas", lista);
//		return "peliculas/listPeliculas";
//	}

	@ModelAttribute
	public List<String> getGeneros(){
		return servicePeliculas.buscarGeneros();
	}
	
	// declarar un conversor de tipos fcha para que sea manejado por el
	// controlador antes de almacenarlo en el bean
	// Aplica para todos los tipos de variable, tipo fecha
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
