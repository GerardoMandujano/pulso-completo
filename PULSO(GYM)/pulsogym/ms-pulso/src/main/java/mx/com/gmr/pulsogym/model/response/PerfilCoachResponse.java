package mx.com.gmr.pulsogym.model.response;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
public class PerfilCoachResponse {
	
	String nombreGym;
	Integer idUsuario;
	Boolean activo;
	Long idPerfilCoach;
	

}
