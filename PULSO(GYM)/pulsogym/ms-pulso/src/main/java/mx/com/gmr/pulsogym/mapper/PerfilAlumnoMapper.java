package mx.com.gmr.pulsogym.mapper;

import org.springframework.stereotype.Component;

import mx.com.gmr.pulsogym.model.PerfilAlumno;
import mx.com.gmr.pulsogym.model.response.PerfilAlumnoResponse;
@Component
public class PerfilAlumnoMapper implements IPerfilAlumnoMapper {

	@Override
	public PerfilAlumnoResponse obtenerAlumno(PerfilAlumno alumno) {
		PerfilAlumnoResponse perfilDto = new PerfilAlumnoResponse();
		perfilDto.setIdUsuario(alumno.getIdUsuario());
		perfilDto.setIdPerfilAlumno(alumno.getIdPerfilAlumno());
		perfilDto.setNombre(alumno.getNombre());
		perfilDto.setApellidoP(alumno.getApellidop());
		perfilDto.setApellidoM(alumno.getApellidom());
		perfilDto.setFechaNacimiento(alumno.getFechaNacimiento());
		perfilDto.setTelefono(alumno.getTelefono());
		perfilDto.setActivo(alumno.getActivo());
		perfilDto.setSexo(alumno.getSexo());
		perfilDto.setCorreo(alumno.getCorreo());
		perfilDto.setFrecuenciaEntrenamiento(alumno.getFrecuenciaEntrenamiento());
		perfilDto.setNivel(alumno.getNivel());
		perfilDto.setObjetivo(alumno.getObjetivo());
		perfilDto.setStatusOnboarding(alumno.getStatusOnboarding());
		
		return perfilDto;
	}

}
