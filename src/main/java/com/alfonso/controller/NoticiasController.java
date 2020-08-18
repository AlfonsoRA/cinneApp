package com.alfonso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alfonso.interfaces.INoticiasService;
import com.alfonso.model.Noticia;

@Controller
@RequestMapping("/noticias")
public class NoticiasController {

	@Autowired
	INoticiasService serviceNoticias;
	
	//mapeamos la solicitud 
	@RequestMapping(value="/crear", method = RequestMethod.GET )
	public String crear(@ModelAttribute Noticia noticia) {
		
		return "noticias/formNoticia";
	}
	
	
	@RequestMapping(value="/indexPagina")
	public String indicePaginado(Model modelo, Pageable pagina) {
		//creamos una lista para paginar el index y la llenamos con el metodo de la interface
		Page<Noticia> listaNoticias = serviceNoticias.buscarTodas(pagina);
		modelo.addAttribute("noticias", listaNoticias);
		return "noticias/listNoticias";
	}
	
	
//	@RequestMapping(value="/index")
//	public String mostrarIndex(Model modelo) {
//		List<Noticia> listaNoticias = serviceNoticias.buscarTodas();	
//		modelo.addAttribute("noticias", listaNoticias);
//		return "noticias/listNoticias";
//	}
	
	
	
	//Creamos metodo guardar 
//	public String save(@RequestParam("titulo") String titulo,
//				@RequestParam("estatus") String estatus,
//				@RequestParam("detalle") String detalle){
	
	//Mapeamos la solicitud Post
	@PostMapping(value="/save")
	public String save(Noticia noticia, RedirectAttributes redirect){
		// llamamos al metodo de la interfaz para guardar la noticia
		serviceNoticias.guardar(noticia);
		redirect.addFlashAttribute("mensajeNoticia", "Su noticia fue insertada exitosamente");
		return "redirect:/noticias/indexPagina";
	}
	
	@GetMapping(value="/edit/{id}")
	public String editNoticia(@PathVariable("id") int idNoticia, Model modelo) {
		Noticia noticia = serviceNoticias.buscarPorId(idNoticia);
		modelo.addAttribute("noticia", noticia);
		return "noticias/formNoticia";
	}
	
	@GetMapping(value="/delete/{id}")	
	public String eliminarNoticia(@PathVariable("id") int idNoticia, RedirectAttributes atributo) {
		serviceNoticias.deleteNoticia(idNoticia);
		atributo.addFlashAttribute("msg", "La Noticia fue eliminada.");
		return "redirect:/noticias/indexPagina";
	}
}
