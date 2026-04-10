package mx.com.gmr.pulsogym.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import mx.com.gmr.pulsogym.model.request.UsuarioRequest;
import mx.com.gmr.pulsogym.model.response.ResponseGenerico;


@FeignClient(name ="usuario-service",url = "${ms-usuario.path}")
public interface UsuarioFeingRepository {

	
	@PostMapping("/usuario/")
	ResponseEntity<ResponseGenerico> registrarUsuario(@RequestBody UsuarioRequest req) ;
}
