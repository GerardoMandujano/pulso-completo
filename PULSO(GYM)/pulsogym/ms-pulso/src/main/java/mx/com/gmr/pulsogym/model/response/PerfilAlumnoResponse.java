package mx.com.gmr.pulsogym.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PerfilAlumnoResponse {
	@JsonProperty("id")
	Long IdPerfilAlumno;
	Integer idUsuario;
	String nombre;
	String apellidoP;
	String apellidoM;
	String fechaNacimiento;
	char sexo;
	String telefono;
	String fecha_creacion;
	String correo;
	String frecuenciaEntrenamiento;
	String nivel;
	String statusOnboarding;
	String objetivo;
	Boolean activo;
}
