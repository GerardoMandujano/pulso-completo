package mx.com.gmr.pulsogym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.gmr.pulsogym.model.request.RequestUsuario;
import mx.com.gmr.pulsogym.model.response.ResponseGenerico;
import mx.com.gmr.pulsogym.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuario/auth")
public class AutController {
	@Autowired
	UsuarioService service;
	
	@PostMapping("/login")
	ResponseGenerico getUsuario(@RequestBody RequestUsuario req) {
		ResponseGenerico response = service.obtenerUsuario(req.getCorreo(), req.getContrasenia());
		return response;
	}

}
