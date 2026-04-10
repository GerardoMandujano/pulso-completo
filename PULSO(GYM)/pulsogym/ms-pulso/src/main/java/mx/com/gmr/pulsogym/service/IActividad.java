package mx.com.gmr.pulsogym.service;

import mx.com.gmr.pulsogym.model.Actividad;
import mx.com.gmr.pulsogym.model.response.ResponseGenerico;

public interface IActividad {
	ResponseGenerico obtenerId(String idActividad);
	ResponseGenerico obtenerTodas(String tipo);
	ResponseGenerico registrar(Actividad actividad);
	ResponseGenerico eliminar(int id);
	ResponseGenerico actualizar(Actividad actividad);

}
