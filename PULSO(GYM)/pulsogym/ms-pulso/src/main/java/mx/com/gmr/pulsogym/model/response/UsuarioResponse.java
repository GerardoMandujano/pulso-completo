package mx.com.gmr.pulsogym.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UsuarioResponse{
	
	@JsonProperty("id")
	Integer id;
	String correo;
	String contrasenia;
	String rol;
	Boolean activo;
	
	

}
