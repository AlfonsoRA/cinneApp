package com.alfonso.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alfonso.interfaces.IBannerService;
import com.alfonso.model.Banner;
import com.alfonso.model.Pelicula;
import com.alfonso.utileria.Utileria;

@Controller
@RequestMapping("/banners")
public class BannerController {
	
	@Autowired
	IBannerService bannerService;
	
	@RequestMapping("/create")
	public String crear(@ModelAttribute Banner banner) {		
		return "banners/formBanner";		
	}
	
	@GetMapping(value ="indexPagina")
	public String indicePaginado(Model modelo, Pageable pagina) {
		Page<Banner> lista = bannerService.buscarTodas(pagina);
		modelo.addAttribute("banners", lista);
		return "banners/listBanners";
	} 
	
//	@RequestMapping("/index")
//	public String mostrarIndex(Model modelo) {
//		List<Banner> listaBanner= bannerService.buscarTodos();
//		modelo.addAttribute("banners", listaBanner);
//		return "banners/listBanners";
//	}
	
	@RequestMapping(value="/edit/{id}")
	public String editarBanners(@PathVariable("id") int idBanner, Model modelo) {
		Banner banner = bannerService.buscarPorId(idBanner);
		modelo.addAttribute("banner", banner);
		return "banners/formBanner";
	}
	
	@RequestMapping("/save")
	public String guardar(@ModelAttribute Banner banner, BindingResult result, RedirectAttributes redirect,
						  @RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request,
						  Model model) {
		if(result.hasErrors()) {
			return "banners/formBanner";
		}
		if(!multiPart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multiPart, request);
			banner.setArchivo(nombreImagen);
		}else {
			if (banner.getArchivo()== null || banner.getArchivo().equals("")) {
				model.addAttribute("imgFaltante", "La imagen es obligatoria");
				return "banners/formBanner";
			}
		}
		
		
		bannerService.insertar(banner);
		
		redirect.addFlashAttribute("mensaje", "Su banner fue insertado");
		
		return "redirect:/banners/indexPagina";
	}
	
	@GetMapping(value="/delete/{id}")
	public String eliminar(@PathVariable("id")int idEliminar, RedirectAttributes atributo) {
		bannerService.eliminar(idEliminar);
		//se añade un objeto de tipo RedirectAttributes para enviar mensaje en un redirect
		atributo.addFlashAttribute("mensajeDelete", "El banner fue eliminado.");
		return "redirect:/banners/indexPagina";
	}
	

}
