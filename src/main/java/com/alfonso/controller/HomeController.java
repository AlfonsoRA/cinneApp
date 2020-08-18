package com.alfonso.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alfonso.interfaces.IBannerService;
import com.alfonso.interfaces.IHorariosService;
import com.alfonso.interfaces.INoticiasService;
import com.alfonso.interfaces.IPeliculasService;
import com.alfonso.model.Banner;
import com.alfonso.model.Horario;
import com.alfonso.model.Noticia;
import com.alfonso.model.Pelicula;
import com.alfonso.utileria.Utileria;

//se pone la anotacion controller 
@Controller
public class HomeController {
	//Se instancia la lista pelicula de manera global
	List<Pelicula> lista = null;
	//Se instancia un formato de fechas de manera global
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	@Autowired
	private IHorariosService serviceHorarios;
	
	@Autowired
	private INoticiasService serviceNoticias;
	
	@Autowired
	private IBannerService serviceBanner;
	
	@Autowired
	IPeliculasService servicePeliculas;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String goHome(){
		return "home";
	}
	
	//Se Recuperan las variables con el @PathVariable
//	@RequestMapping(value="/detail/{id}/{fechaBusqueda}")
//	public String mostrarDetalle(Model modelo, @PathVariable("id") int idPelicula,
//			@PathVariable("fechaBusqueda") String fecha)
	
	@RequestMapping(value ="/detail")
	//Se recuperan los parametros a travez del @RequestParam
	public String mostrarDetalle(Model modelo, @RequestParam("idPelicula") int idPelicula,
								@RequestParam("fecha") Date fecha) {
		//Creo la informacion de la palicula para mostrala en la vista 
		String tituloPelicula = "Rapido y Furioso";
		int duracion = 136;
		double precioEntrada = 50;
		
		// se crea una lista de horarios y se llena con el metodo de la interface
		List<Horario> horarios = serviceHorarios.buscarPorIdPelicula(idPelicula, fecha);
		
		//Se añaden los atributos de la pelicula al modelo para ser enviados al context
		modelo.addAttribute("horarios", horarios);
		modelo.addAttribute("titulo", tituloPelicula);
		modelo.addAttribute("duracion", duracion);
		modelo.addAttribute("precio", precioEntrada);
		modelo.addAttribute("fechaBusqueda", sdf.format(fecha));
		
		
		modelo.addAttribute("pelicula", servicePeliculas.buscarPorId(idPelicula));
		return "detalle";		
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String mostrarPrincipal(Model model) {	
		//Se instancia la clase utileria 
		Utileria utileria= new Utileria();
		//Se llena la lista llamando al metodo getlista
//		List<Pelicula> peliculas = getLista();
		//se llama la clase utileria para usar su metodo listaFecha
		List<String> listaFechas = Utileria.listaFecha(4);
//		peliculas.add("Papido y Furioso");
//		peliculas.add("El aro dos");
//		peliculas.add("Aliens");
		
		//Creamos lista de noticias por medio de la interface
		List<Noticia> listaNoticias= serviceNoticias.buscarEstatus("Activa");
		
		//Creo lista por medio de la interface IPeliculasService y se usa el metodo buscarTodas
		List<Pelicula> listaPeliculas = servicePeliculas.buscarTodas();
		
		List<Banner> listaBanners = serviceBanner.buscarEstatus("Activo");
		
		//Se añaden los atributos de la pelicula al modelo para ser enviados al context
		model.addAttribute("listaNoticias", listaNoticias);
		model.addAttribute("peliculas", listaPeliculas);
		model.addAttribute("fechaBusqueda", sdf.format(new Date()));
		model.addAttribute("listaFecha", listaFechas);
		model.addAttribute("listaBanners", listaBanners);
		return "home";
	}
	
//	//Creamos el metodo getlista que devuelve una lista
//	private List<Pelicula> getLista(){
//		
//		//manejamos la exception
//		try {
//			
//			lista = new LinkedList<>();
//			//creamos un objeto pelicula 
//			Pelicula pelicula1 = new Pelicula();
//			// seteamos los atributos del objeto
//			pelicula1.setId(1);
//			pelicula1.setTitulo("Aliens");
//			pelicula1.setDuracion(136);
//			pelicula1.setClasificacion("C");
//			pelicula1.setGenero("ficcion");
//			pelicula1.setFechaEstreno(sdf.parse("20-02-2019"));
//			
//			//creamos un objeto pelicula
//			Pelicula pelicula2 = new Pelicula();
//			// seteamos los atributos del objeto
//			pelicula2.setId(1);
//			pelicula2.setTitulo("Aliens");
//			pelicula2.setDuracion(136);
//			pelicula2.setClasificacion("C");
//			pelicula2.setGenero("ficcion");
//			pelicula2.setFechaEstreno(sdf.parse("20-02-2019"));
//			
//			//creamos un objeto pelicula
//			Pelicula pelicula3 = new Pelicula();
//			// seteamos los atributos del objeto
//			pelicula3.setId(1);
//			pelicula3.setTitulo("Aliens");
//			pelicula3.setDuracion(136);
//			pelicula3.setClasificacion("C");
//			pelicula3.setGenero("ficcion");
//			pelicula3.setFechaEstreno(sdf.parse("20-02-2019"));
//			
//			// añadimos los objetos a la lista
//			lista.add(pelicula1);
//			lista.add(pelicula2);
//			lista.add(pelicula3);
//		} catch (ParseException e) {
//			System.out.println(e.getMessage());
//		}
//		// devolvemos la lista 
//		return lista;
//	}
	
	//mapeamos la solicitud para saber a quien va a responder la solicitud
	@RequestMapping(value = "/buscar", method=RequestMethod.POST)
	public String buscar(@RequestParam("fecha")Date fecha, Model model) {
		//verificamos que lleguen las fechas imprimiendolas en consola
		//System.out.println("Las fechas son: " + fecha);
		
		//se llena una lista con el metodo de la clase utileria
		List<String> listaFechas = Utileria.listaFecha(5);	
		System.out.println(fecha);
		// se llena una lista con el metodo getLista
		List<Pelicula> listaPeliculas = serviceHorarios.buscarPorFecha(fecha);
		//Se llena una lista con el metodo buscarTodos
		List<Banner> listaBanners = serviceBanner.buscarTodos();
		//Se llena una lista con el metodo buscarEstatus
		List<Noticia> listaNoticia = serviceNoticias.buscarEstatus("Activa");		
		//se lo mandamos a la vista a traves del modelo
		model.addAttribute("listaFecha", listaFechas);
		model.addAttribute("fechaBusqueda", sdf.format(fecha) );
		model.addAttribute("peliculas", listaPeliculas); 
		model.addAttribute("listaBanners", listaBanners); 
		model.addAttribute("listaNoticias", listaNoticia);
		return "home";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String mostrarLogin() {
		return "login";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
	}
}
