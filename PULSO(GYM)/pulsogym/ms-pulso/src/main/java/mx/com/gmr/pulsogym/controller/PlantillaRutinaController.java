package mx.com.gmr.pulsogym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.gmr.pulsogym.model.request.AsignarActividadesPlantillaRequest;
import mx.com.gmr.pulsogym.model.request.CrearPlantillaRequest;
import mx.com.gmr.pulsogym.model.response.ResponseGenerico;
import mx.com.gmr.pulsogym.service.IPlantillaRutina;

@RestController
@RequestMapping("api/v1/plantilla")
public class PlantillaRutinaController {

	@Autowired
	IPlantillaRutina plantillaService;

	@GetMapping("/{id}")
	ResponseGenerico obtener(@PathVariable("id") String id) {
		return plantillaService.obtenerId(id);
	}

	@GetMapping("/coach/{idCoach}")
	ResponseGenerico obtenerPorCoach(@PathVariable("idCoach") String idCoach) {
		return plantillaService.obtenerPorCoach(idCoach);
	}

	@PostMapping("/")
	ResponseGenerico insertar(@RequestBody CrearPlantillaRequest request) {
		return plantillaService.registrar(request);
	}

	@PostMapping("/{idPlantilla}/actividades")
	ResponseGenerico agregarActividades(@PathVariable("idPlantilla") String idPlantilla,
			@RequestBody AsignarActividadesPlantillaRequest request) {
		return plantillaService.agregarActividades(idPlantilla, request);
	}

	@GetMapping("/{idPlantilla}/actividades")
	ResponseGenerico obtenerActividades(@PathVariable("idPlantilla") String idPlantilla) {
		return plantillaService.obtenerActividades(idPlantilla);
	}

	@DeleteMapping("/{id}")
	ResponseGenerico eliminar(@PathVariable String id) {
		return plantillaService.eliminar(Integer.parseInt(id));
	}
}
