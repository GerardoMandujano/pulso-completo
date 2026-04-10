package mx.com.gmr.pulsogym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.gmr.pulsogym.model.Actividad;
import mx.com.gmr.pulsogym.model.response.ResponseGenerico;
import mx.com.gmr.pulsogym.service.IActividad;

@RestController
@RequestMapping("api/v1/actividad")
public class ActividadController {
	
	@Autowired
	IActividad actividadService;
	

	
	@GetMapping("/{tipo}/buscar")
	ResponseGenerico obtenerTodasTipo(@PathVariable("tipo") String tipo) {
		
		return actividadService.obtenerTodas(tipo);
	}
	
	@GetMapping("/{id}")
	ResponseGenerico obtener(@PathVariable("id") String id) {
	return actividadService.obtenerId(id);
	}
	
	@PostMapping("/")
	ResponseGenerico insertar(@RequestBody Actividad actividad) {
	return actividadService.registrar(actividad);
	}
	
	@PutMapping("/")
	ResponseGenerico actualizar(@RequestBody Actividad actividad) {
	return actividadService.actualizar(actividad);
	}
	
	@DeleteMapping("/{id}")
	ResponseGenerico eliminar(@PathVariable String id) {
	return actividadService.eliminar(Integer.parseInt(id));
	
	}

}
