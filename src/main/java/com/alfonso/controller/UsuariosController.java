package com.alfonso.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alfonso.interfaces.IPerfilesService;
import com.alfonso.interfaces.IUsuariosService;
import com.alfonso.model.Perfil;
import com.alfonso.model.Usuarios;


@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private IUsuariosService servicesUsuario;
	
	
	@Autowired
	private IPerfilesService servicesPerfil;
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping(value="/create")
	public String crearUsuario(@ModelAttribute("usuario") Usuarios usuario) {
		return "usuarios/formUsuario";
	}
	
	@PostMapping(value="/save")
	public String guardar(@Valid @ModelAttribute("usuario") Usuarios usuario, BindingResult result,
			@RequestParam("perfil") String perfil, Model modelo) {
		
		// se recorre la lista de errores generada por el BindingResult en caso de
		// fallas con el DataBinding
		if(result.hasErrors()) {
			return "usuarios/formUsuario";
		}
//		for (ObjectError error : result.getAllErrors()) {	
//			System.out.println("Error: " + error.getDefaultMessage());
//			return "usuarios/formUsuario";
//		}
		
		// se crea un string y se almacena el password
		String pass = usuario.getPwd();
		// se encripta el password
		String passEncryp = encoder.encode(pass);
		// se setea el password encriptado
		usuario.setPwd(passEncryp);
		usuario.setActivo(1);
		// se guarda el usuario en la base de datos
		servicesUsuario.guardar(usuario);
		// se crea un perfil nuevo y se le setea la cuenta y el perfil
		Perfil perfilTmp = new Perfil();	
		perfilTmp.setCuenta(usuario.getCuenta());
		perfilTmp.setPerfil(perfil);
		// se guarda en la base de datos
		servicesPerfil.guardar(perfilTmp);
		
		System.out.println(usuario);
		System.out.println(perfil);
		return "redirect:/usuarios/index";
	}
	
	@GetMapping(value="/index")
	public String index(Model modelo) {
		List<Usuarios> usuarios= servicesUsuario.mostrarUsuarios();
		System.out.println(usuarios);
		modelo.addAttribute("listaUsuarios", usuarios);
		
		return "usuarios/listUsuarios";
	}
	
	@GetMapping(value="/demo-Bcryp")
	public String pruebaEncripta() {
		String password ="mari123";
		String encriptado = encoder.encode(password);
		System.out.println("Password encriptado: "+encriptado);
		return "usuarios/demo";
	} 
	
	@GetMapping(value="/edit/{id}")
	public String editar(@PathVariable("id") int id, Model modelo){
		Usuarios usuario = servicesUsuario.buscarId(id);
		modelo.addAttribute("usuario", usuario);
		return "usuarios/formUsuario";
	}
	
	
	
	
}
