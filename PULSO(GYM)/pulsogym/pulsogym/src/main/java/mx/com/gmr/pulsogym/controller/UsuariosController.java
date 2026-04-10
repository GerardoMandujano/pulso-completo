package mx.com.gmr.pulsogym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.gmr.app.security.annotation.ActivarJwt;
import mx.com.gmr.pulsogym.model.Usuario;
import mx.com.gmr.pulsogym.model.request.RequestUsuario;
import mx.com.gmr.pulsogym.model.response.ResponseGenerico;
import mx.com.gmr.pulsogym.service.UsuarioService;



@RestController
@RequestMapping("api/v1/usuario")
public class UsuariosController {
	
	@Autowired
	UsuarioService service;
	

	@GetMapping("/")
	ResponseGenerico getUsuarios() {
		ResponseGenerico response = service.obtenerTodosUsuarios();
		return response;
	}
	
	
	@PostMapping("/")
	ResponseGenerico inserUsuario( @RequestBody  Usuario req) {
		ResponseGenerico response = service.registrarUsuario(req);
		return response;
	}
	
	@GetMapping("/eliminar/{correo}")
	ResponseGenerico desactivar(@PathVariable("correo")String correo) {
		ResponseGenerico response = service.desactivarUsuario(correo);
		return response;
	}
	
	
	@PatchMapping("/")
	ResponseGenerico actualizar(@RequestBody Usuario usuario) {
		ResponseGenerico response = service.actualizarUsuario(usuario);
		return response;
	}

}
