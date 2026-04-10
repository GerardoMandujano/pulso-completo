package mx.com.gmr.pulsogym.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.com.gmr.pulsogym.model.Actividad;
import mx.com.gmr.pulsogym.model.response.ResponseGenerico;
import mx.com.gmr.pulsogym.repository.IActividadRepository;
import mx.com.gmr.pulsogym.utils.Constantes;


@Slf4j
@Service
public class ActividadService implements IActividad {
	
	
	@Autowired
	IActividadRepository actividadRepository;
	ResponseGenerico response;

	@Override
	public ResponseGenerico registrar(Actividad actividad) {

	    ResponseGenerico response = new ResponseGenerico();

	    try {

	        if (actividad == null) {
	            throw new IllegalArgumentException("Actividad requerida");
	        }

	        Actividad actividadDb = actividadRepository.save(actividad);

	        if (actividadDb == null) {
	            throw new RuntimeException("No se pudo registrar la actividad");
	        }

	        response.setCodigo(201);
	        response.setData(actividadDb);
	        response.setMensaje(Constantes.mensajeInsertadoOk);

	    } catch (DataIntegrityViolationException e) {

	        String codigoError = UUID.randomUUID().toString();

	        log.error("Error de integridad registrando actividad [{}]", codigoError, e);

	        response.setCodigo(400);
	        response.setData(null);
	        response.setCodigoError(codigoError);
	        response.setMensaje("Datos inválidos o duplicados");

	    } catch (DataAccessException e) {

	        String codigoError = UUID.randomUUID().toString();

	        log.error("Error de base de datos [{}]", codigoError, e);

	        response.setCodigo(500);
	        response.setData(null);
	        response.setCodigoError(codigoError);
	        response.setMensaje("Error de base de datos");

	    } catch (Exception e) {

	        String codigoError = UUID.randomUUID().toString();

	        log.error("Error interno [{}]", codigoError, e);

	        response.setCodigo(500);
	        response.setData(null);
	        response.setCodigoError(codigoError);
	        response.setMensaje("Error interno del sistema");
	    }

	    return response;
	}

	@Override
	public ResponseGenerico eliminar(int id) {
		response= new ResponseGenerico();
	
		try {
			Integer actividadDb = actividadRepository.eliminarActividad(id);
			if(actividadDb>0) {
				response.setCodigo(200);
				response.setData(true);
				response.setMensaje(Constantes.mensajeEliminadoOk);
			}else {
				 response.setCodigo(404);
				    response.setMensaje("Actividad no encontrada");
				 
			}
			
		} catch (DataAccessException  e) {
			String codigoError=UUID.randomUUID().toString();
			response.setCodigo(500);
			response.setData(null);
			response.setMensaje(Constantes.mensajeNoInsertado);
			response.setCodigoError(codigoError);
			log.error("ERROR: " +codigoError+" |" +e.getMessage());
		}
		return response;
	}

	@Override
	public ResponseGenerico actualizar(Actividad actividad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseGenerico obtenerTodas(String tipo) {
		 ResponseGenerico response = new ResponseGenerico();

		    try {
		    	  List<Actividad> actividadDb=null;
		    	if(tipo.equalsIgnoreCase("todo")) {
				     
		    		actividadDb = actividadRepository.findAll();

		    	}else {
		    		 actividadDb = actividadRepository.findByTipo(tipo);
		    	}


		        response.setCodigo(200);
		        response.setData(actividadDb);
		        response.setMensaje(Constantes.mensageOk);

		    

		    } catch (DataAccessException e) {

		        String codigoError = UUID.randomUUID().toString();

		        log.error("Error de base de datos [{}]", codigoError, e);

		        response.setCodigo(500);
		        response.setData(null);
		        response.setCodigoError(codigoError);
		        response.setMensaje("Error de base de datos");

		    } catch (Exception e) {

		        String codigoError = UUID.randomUUID().toString();

		        log.error("Error interno [{}]", codigoError, e);

		        response.setCodigo(500);
		        response.setData(null);
		        response.setCodigoError(codigoError);
		        response.setMensaje("Error interno del sistema");
		    }

		    return response;
	}

	@Override
	public ResponseGenerico obtenerId(String idActividad) {
		 ResponseGenerico response = new ResponseGenerico();

		    try {
		    	Long id = (long) Integer.parseInt(idActividad);
		      Optional<Actividad> actividadDb = actividadRepository.findById(id);


		        response.setCodigo(200);
		        response.setData(actividadDb);
		        response.setMensaje(Constantes.mensageOk);

		    

		    } catch (DataAccessException e) {

		        String codigoError = UUID.randomUUID().toString();

		        log.error("Error de base de datos [{}]", codigoError, e);

		        response.setCodigo(500);
		        response.setData(null);
		        response.setCodigoError(codigoError);
		        response.setMensaje("Error de base de datos");

		    } catch (Exception e) {

		        String codigoError = UUID.randomUUID().toString();

		        log.error("Error interno [{}]", codigoError, e);

		        response.setCodigo(500);
		        response.setData(null);
		        response.setCodigoError(codigoError);
		        response.setMensaje("Error interno del sistema");
		    }

		    return response;
	}

}
