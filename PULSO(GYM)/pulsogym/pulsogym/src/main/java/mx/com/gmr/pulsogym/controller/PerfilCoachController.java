package mx.com.gmr.pulsogym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.gmr.pulsogym.model.response.ResponseGenerico;
import mx.com.gmr.pulsogym.service.PerfilCoachService;

@RestController
@RequestMapping("api/v1/coach")
public class PerfilCoachController {

	@Autowired
	PerfilCoachService coachService;
	
	@GetMapping("/{id}")
	public ResponseGenerico obtenerCoach(@PathVariable String id) {
		ResponseGenerico data=  new ResponseGenerico();
		data =coachService.obtenerCoach(id);
		return data;
	}
}
