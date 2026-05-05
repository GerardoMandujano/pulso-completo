package mx.com.gmr.pulsogym.service;

import mx.com.gmr.pulsogym.model.request.AsignarActividadesPlantillaRequest;
import mx.com.gmr.pulsogym.model.request.CrearPlantillaRequest;
import mx.com.gmr.pulsogym.model.response.ResponseGenerico;

public interface IPlantillaRutina {
	ResponseGenerico obtenerId(String idPlantilla);
	ResponseGenerico obtenerPorCoach(String idCoach);
	ResponseGenerico registrar(CrearPlantillaRequest request);
	ResponseGenerico eliminar(int idPlantilla);
	ResponseGenerico agregarActividades(String idPlantilla, AsignarActividadesPlantillaRequest request);
	ResponseGenerico obtenerActividades(String idPlantilla);
}
