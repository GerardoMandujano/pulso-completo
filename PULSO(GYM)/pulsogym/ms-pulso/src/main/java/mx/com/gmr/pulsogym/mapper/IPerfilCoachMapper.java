package mx.com.gmr.pulsogym.mapper;

import mx.com.gmr.pulsogym.model.PerfilCoach;
import mx.com.gmr.pulsogym.model.response.PerfilCoachResponse;

public interface IPerfilCoachMapper {
	 PerfilCoachResponse obtenerCoach(PerfilCoach coach);
}
