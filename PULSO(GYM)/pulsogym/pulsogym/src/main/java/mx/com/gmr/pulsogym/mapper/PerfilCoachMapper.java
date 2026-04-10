package mx.com.gmr.pulsogym.mapper;

import org.springframework.stereotype.Component;

import mx.com.gmr.pulsogym.model.PerfilCoach;
import mx.com.gmr.pulsogym.model.response.PerfilCoachResponse;
import mx.com.gmr.pulsogym.model.response.UsuarioResponse;

@Component
public class PerfilCoachMapper implements IPerfilCoachMapper {

	@Override
	public PerfilCoachResponse obtenerCoach(PerfilCoach coach) {
		
		PerfilCoachResponse coachDto = new PerfilCoachResponse();
		coachDto.setIdPerfilCoach(coach.getIdPerfilCoach());
		coachDto.setNombreGym(coach.getNombreGym());
	//	coachDto.setUsuario(coach.getUsuario());
	
		return coachDto;
	}
	
	 
}
