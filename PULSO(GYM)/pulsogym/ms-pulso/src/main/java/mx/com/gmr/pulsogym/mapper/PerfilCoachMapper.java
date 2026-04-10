package mx.com.gmr.pulsogym.mapper;

import org.springframework.stereotype.Component;

import mx.com.gmr.pulsogym.model.PerfilCoach;
import mx.com.gmr.pulsogym.model.response.PerfilCoachResponse;

@Component
public class PerfilCoachMapper implements IPerfilCoachMapper {

	@Override
	public PerfilCoachResponse obtenerCoach(PerfilCoach coach) {
		
		PerfilCoachResponse coachDto = new PerfilCoachResponse();
		coachDto.setIdPerfilCoach(coach.getIdPerfilCoach());
		coachDto.setNombreGym(coach.getNombreGym());
		coachDto.setIdUsuario(coach.getIdUsuario());
		coachDto.setActivo(coach.getActivo());
		return coachDto;
	}
	
	 
}
