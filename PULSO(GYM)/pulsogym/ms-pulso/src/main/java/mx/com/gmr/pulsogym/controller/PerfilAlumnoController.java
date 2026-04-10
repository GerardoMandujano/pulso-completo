package mx.com.gmr.pulsogym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.gmr.pulsogym.model.OnboardinToken;
import mx.com.gmr.pulsogym.model.PerfilAlumno;
import mx.com.gmr.pulsogym.model.response.ResponseGenerico;
import mx.com.gmr.pulsogym.service.PerfilAlumnoService;

@RestController
@RequestMapping("api/v1/alumno")
public class PerfilAlumnoController {

	
	@Autowired
	PerfilAlumnoService service;
	
	@GetMapping("/{id}")
	ResponseGenerico obtenerAlumno(@PathVariable String id) {
		ResponseGenerico data= new ResponseGenerico();
		data =service.obtenerAlumno(id);
		return data;
	}
	
	@GetMapping("/")
	ResponseGenerico obtenerAlumnos(@RequestParam int page,@RequestParam int size) {
		ResponseGenerico data= new ResponseGenerico();
		data =service.obtenerTodosAlumnos(page,size);
		return data;
	}
	
	@PostMapping("/")
	ResponseEntity<ResponseGenerico> registrar(@RequestBody PerfilAlumno alumno) {
		ResponseGenerico data= new ResponseGenerico();
		data =service.registrarAlumno(alumno);
		return ResponseEntity.status(data.getCodigo()).body(data);
	}
	
	@PatchMapping("/")
	ResponseGenerico actualizar(@RequestBody PerfilAlumno alumno) {
		ResponseGenerico data= new ResponseGenerico();
		data =service.actualizarAlumno(alumno);
		return data;
	}
	
	@GetMapping("/cambio-contrasenia/{token}")
	public ResponseEntity<?>validarToken(@PathVariable String token){
		
	return service.validarToken(token);
	}
}
