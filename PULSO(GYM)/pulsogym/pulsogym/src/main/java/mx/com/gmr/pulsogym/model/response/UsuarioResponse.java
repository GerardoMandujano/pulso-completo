package mx.com.gmr.pulsogym.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import mx.com.gmr.pulsogym.utils.RolEnum;

@Data
public class UsuarioResponse {
	private Long id;
	private String correo;
	@JsonIgnore
	private String contrasenia;
	private String rol;
	

}
