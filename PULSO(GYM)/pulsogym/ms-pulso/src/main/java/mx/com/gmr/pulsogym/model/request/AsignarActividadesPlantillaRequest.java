package mx.com.gmr.pulsogym.model.request;

import java.util.List;

import lombok.Data;

@Data
public class AsignarActividadesPlantillaRequest {
	String diaSemana;
	List<ActividadPlantillaRequest> actividades;
}
