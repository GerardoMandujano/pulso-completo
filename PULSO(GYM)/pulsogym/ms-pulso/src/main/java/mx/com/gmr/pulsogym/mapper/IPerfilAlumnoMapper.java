package mx.com.gmr.pulsogym.mapper;

import org.springframework.stereotype.Component;

import mx.com.gmr.pulsogym.model.PerfilAlumno;
import mx.com.gmr.pulsogym.model.PerfilCoach;
import mx.com.gmr.pulsogym.model.response.PerfilAlumnoResponse;
import mx.com.gmr.pulsogym.model.response.PerfilCoachResponse;
@Component
public interface IPerfilAlumnoMapper {
	
	 PerfilAlumnoResponse obtenerAlumno(PerfilAlumno alumno);

}
