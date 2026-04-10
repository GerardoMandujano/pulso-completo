package mx.com.gmr.pulsogym.service;

import mx.com.gmr.pulsogym.model.PerfilAlumno;

import mx.com.gmr.pulsogym.model.response.ResponseGenerico;

public interface IPerfilAlumno {
	
	ResponseGenerico obtenerAlumno(String id);
	ResponseGenerico obtenerTodosAlumnos(int page,int size);
	ResponseGenerico registrarAlumno(PerfilAlumno alumno);
	 ResponseGenerico actualizarAlumno(PerfilAlumno alumno);
}
