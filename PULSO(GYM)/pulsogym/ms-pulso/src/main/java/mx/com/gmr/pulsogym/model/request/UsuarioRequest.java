package mx.com.gmr.pulsogym.model.request;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UsuarioRequest{
	
	
	String correo;
	String contrasenia;
	String rol;
	Boolean activo;
	
	

}
