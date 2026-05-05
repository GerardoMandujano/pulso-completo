package mx.com.gmr.pulsogym.model.request;

import java.util.List;

import lombok.Data;

@Data
public class CrearPlantillaRequest {
	Integer idPerfilCoach;
	String nombre;
	String diaSemana;
	List<ActividadPlantillaRequest> actividades;
}
