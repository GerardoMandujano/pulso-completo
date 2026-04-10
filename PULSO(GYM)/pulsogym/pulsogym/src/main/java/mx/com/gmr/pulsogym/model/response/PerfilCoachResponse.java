package mx.com.gmr.pulsogym.model.response;

import lombok.Data;

@Data
public class PerfilCoachResponse {
	
	Long idPerfilCoach;
	UsuarioResponse usuario;
	String nombreGym;

}
