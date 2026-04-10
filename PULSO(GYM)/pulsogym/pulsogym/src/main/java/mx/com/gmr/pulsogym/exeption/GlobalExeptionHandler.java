package mx.com.gmr.pulsogym.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import mx.com.gmr.pulsogym.model.response.ResponseGenerico;

@RestControllerAdvice
public class GlobalExeptionHandler {
	@ExceptionHandler(UsuarioExistenteException.class)
	public ResponseEntity<ResponseGenerico> handleUsuarioExistente(UsuarioExistenteException ex){
		ResponseGenerico response = new ResponseGenerico();
		response.setData(null);
		response.setCodigo(409);
		response.setMensaje(ex.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}

}
