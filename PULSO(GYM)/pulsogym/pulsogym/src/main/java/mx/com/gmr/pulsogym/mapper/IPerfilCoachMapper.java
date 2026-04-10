package mx.com.gmr.pulsogym.mapper;

import mx.com.gmr.pulsogym.model.PerfilCoach;
import mx.com.gmr.pulsogym.model.response.PerfilCoachResponse;
import mx.com.gmr.pulsogym.model.response.UsuarioResponse;

public interface IPerfilCoachMapper {
	 PerfilCoachResponse obtenerCoach(PerfilCoach coach);
}
