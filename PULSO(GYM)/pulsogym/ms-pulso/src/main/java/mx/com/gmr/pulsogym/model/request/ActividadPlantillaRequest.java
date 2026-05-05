package mx.com.gmr.pulsogym.model.request;

import lombok.Data;

@Data
public class ActividadPlantillaRequest {
	Long idActividad;
	Integer series;
	Integer repeticiones;
	Integer duracionMinutos;
	Integer descanso;
	Integer orden;
}
