package mx.com.gmr.pulsogym.model.request;

import lombok.Data;

@Data
public class RequestPerfilAlumno {
	
	Long IdPerfilAlumno;
	Integer idUsuario;
	String nombre;
	String apellidoP;
	String apellidoM;
	Integer edad;
	char sexo;
	String telefono;
	Boolean activo;
}
