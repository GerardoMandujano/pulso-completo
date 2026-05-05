package mx.com.gmr.pulsogym.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.com.gmr.pulsogym.model.Actividad;
import mx.com.gmr.pulsogym.model.ActividadRutina;
import mx.com.gmr.pulsogym.model.DiaRutina;
import mx.com.gmr.pulsogym.model.PlantillaRutina;
import mx.com.gmr.pulsogym.model.request.ActividadPlantillaRequest;
import mx.com.gmr.pulsogym.model.request.AsignarActividadesPlantillaRequest;
import mx.com.gmr.pulsogym.model.request.CrearPlantillaRequest;
import mx.com.gmr.pulsogym.model.response.ResponseGenerico;
import mx.com.gmr.pulsogym.repository.IActividadRepository;
import mx.com.gmr.pulsogym.repository.IActividadRutinaRepository;
import mx.com.gmr.pulsogym.repository.IDiaRutinaRepository;
import mx.com.gmr.pulsogym.repository.IPlantillaRutinaRepository;
import mx.com.gmr.pulsogym.utils.Constantes;

@Slf4j
@Service
public class PlantillaRutinaService implements IPlantillaRutina {

	@Autowired
	IPlantillaRutinaRepository plantillaRepository;
	@Autowired
	IDiaRutinaRepository diaRutinaRepository;
	@Autowired
	IActividadRutinaRepository actividadRutinaRepository;
	@Autowired
	IActividadRepository actividadRepository;

	@Override
	public ResponseGenerico registrar(CrearPlantillaRequest request) {
		ResponseGenerico response = new ResponseGenerico();
		try {
			if (request == null || request.getIdPerfilCoach() == null || request.getNombre() == null
					|| request.getNombre().isBlank()) {
				response.setCodigo(400);
				response.setData(null);
				response.setMensaje("Datos de plantilla invalidos");
				return response;
			}

			PlantillaRutina plantilla = new PlantillaRutina();
			plantilla.setIdPerfilCoach(request.getIdPerfilCoach());
			plantilla.setNombre(request.getNombre().trim());
			plantilla.setActivo(true);
			PlantillaRutina plantillaDb = plantillaRepository.save(plantilla);

			if (request.getActividades() != null && !request.getActividades().isEmpty()) {
				String diaSemana = (request.getDiaSemana() == null || request.getDiaSemana().isBlank()) ? "LUNES"
						: request.getDiaSemana().trim().toUpperCase();

				DiaRutina diaRutina = new DiaRutina();
				diaRutina.setIdPlantillaRutina(plantillaDb.getIdPlantillaRutina());
				diaRutina.setDiaSemana(diaSemana);
				diaRutina.setActivo(true);
				diaRutina = diaRutinaRepository.save(diaRutina);

				for (ActividadPlantillaRequest actividadRequest : request.getActividades()) {
					if (actividadRequest.getIdActividad() == null || actividadRequest.getSeries() == null
							|| actividadRequest.getOrden() == null) {
						response.setCodigo(400);
						response.setData(null);
						response.setMensaje("Cada actividad debe incluir idActividad, series y orden");
						return response;
					}
					Optional<Actividad> actividadDb = actividadRepository.findById(actividadRequest.getIdActividad());
					if (actividadDb.isEmpty() || Boolean.FALSE.equals(actividadDb.get().getActivo())) {
						response.setCodigo(404);
						response.setData(null);
						response.setMensaje("Actividad no encontrada: " + actividadRequest.getIdActividad());
						return response;
					}

					ActividadRutina actividadRutina = new ActividadRutina();
					actividadRutina.setIdDiaRutina(diaRutina.getIdDiaRutina());
					actividadRutina.setIdActividad(actividadRequest.getIdActividad());
					actividadRutina.setSeries(actividadRequest.getSeries());
					actividadRutina.setRepeticiones(actividadRequest.getRepeticiones());
					actividadRutina.setDuracionMinutos(actividadRequest.getDuracionMinutos());
					actividadRutina.setDescanso(actividadRequest.getDescanso());
					actividadRutina.setOrden(actividadRequest.getOrden());
					actividadRutina.setActivo(true);
					actividadRutinaRepository.save(actividadRutina);
				}
			}

			response.setCodigo(201);
			response.setData(plantillaDb);
			response.setMensaje(Constantes.mensajeInsertadoOk);
		} catch (DataAccessException e) {
			String codigoError = UUID.randomUUID().toString();
			log.error("Error BD al registrar plantilla [{}]", codigoError, e);
			response.setCodigo(500);
			response.setData(null);
			response.setCodigoError(codigoError);
			response.setMensaje("Error de base de datos");
		} catch (Exception e) {
			String codigoError = UUID.randomUUID().toString();
			log.error("Error interno al registrar plantilla [{}]", codigoError, e);
			response.setCodigo(500);
			response.setData(null);
			response.setCodigoError(codigoError);
			response.setMensaje("Error interno del sistema");
		}
		return response;
	}

	@Override
	public ResponseGenerico obtenerId(String idPlantilla) {
		ResponseGenerico response = new ResponseGenerico();
		try {
			Long id = (long) Integer.parseInt(idPlantilla);
			Optional<PlantillaRutina> plantillaDb = plantillaRepository.findById(id);
			if (plantillaDb.isEmpty()) {
				response.setCodigo(404);
				response.setData(null);
				response.setMensaje("Plantilla no encontrada");
				return response;
			}

			Map<String, Object> data = new HashMap<>();
			data.put("plantilla", plantillaDb.get());
			data.put("actividades", actividadRutinaRepository.findByPlantilla(id));

			response.setCodigo(200);
			response.setData(data);
			response.setMensaje(Constantes.mensageOk);
		} catch (DataAccessException e) {
			String codigoError = UUID.randomUUID().toString();
			log.error("Error BD al consultar plantilla [{}]", codigoError, e);
			response.setCodigo(500);
			response.setData(null);
			response.setCodigoError(codigoError);
			response.setMensaje("Error de base de datos");
		} catch (Exception e) {
			String codigoError = UUID.randomUUID().toString();
			log.error("Error interno al consultar plantilla [{}]", codigoError, e);
			response.setCodigo(500);
			response.setData(null);
			response.setCodigoError(codigoError);
			response.setMensaje("Error interno del sistema");
		}
		return response;
	}

	@Override
	public ResponseGenerico obtenerPorCoach(String idCoach) {
		ResponseGenerico response = new ResponseGenerico();
		try {
			int idPerfilCoach = Integer.parseInt(idCoach);
			List<PlantillaRutina> plantillas = plantillaRepository.findByIdPerfilCoach(idPerfilCoach);
			response.setCodigo(200);
			response.setData(plantillas);
			response.setMensaje(Constantes.mensageOk);
		} catch (DataAccessException e) {
			String codigoError = UUID.randomUUID().toString();
			log.error("Error BD al consultar plantillas por coach [{}]", codigoError, e);
			response.setCodigo(500);
			response.setData(null);
			response.setCodigoError(codigoError);
			response.setMensaje("Error de base de datos");
		} catch (Exception e) {
			String codigoError = UUID.randomUUID().toString();
			log.error("Error interno al consultar plantillas por coach [{}]", codigoError, e);
			response.setCodigo(500);
			response.setData(null);
			response.setCodigoError(codigoError);
			response.setMensaje("Error interno del sistema");
		}
		return response;
	}

	@Override
	public ResponseGenerico eliminar(int idPlantilla) {
		ResponseGenerico response = new ResponseGenerico();
		try {
			Integer registros = plantillaRepository.eliminarPlantilla(idPlantilla);
			if (registros != null && registros > 0) {
				response.setCodigo(200);
				response.setData(true);
				response.setMensaje(Constantes.mensajeEliminadoOk);
			} else {
				response.setCodigo(404);
				response.setData(false);
				response.setMensaje("Plantilla no encontrada");
			}
		} catch (DataAccessException e) {
			String codigoError = UUID.randomUUID().toString();
			log.error("Error BD al eliminar plantilla [{}]", codigoError, e);
			response.setCodigo(500);
			response.setData(null);
			response.setCodigoError(codigoError);
			response.setMensaje("Error de base de datos");
		}
		return response;
	}

	@Override
	public ResponseGenerico agregarActividades(String idPlantilla, AsignarActividadesPlantillaRequest request) {
		ResponseGenerico response = new ResponseGenerico();
		try {
			Long idPlantillaRutina = (long) Integer.parseInt(idPlantilla);
			Optional<PlantillaRutina> plantillaDb = plantillaRepository.findById(idPlantillaRutina);
			if (plantillaDb.isEmpty() || Boolean.FALSE.equals(plantillaDb.get().getActivo())) {
				response.setCodigo(404);
				response.setData(null);
				response.setMensaje("Plantilla no encontrada");
				return response;
			}
			if (request == null || request.getDiaSemana() == null || request.getDiaSemana().isBlank()
					|| request.getActividades() == null || request.getActividades().isEmpty()) {
				response.setCodigo(400);
				response.setData(null);
				response.setMensaje("Datos invalidos para asignar actividades");
				return response;
			}

			String diaSemana = request.getDiaSemana().trim().toUpperCase();
			DiaRutina diaRutina = diaRutinaRepository
					.findByIdPlantillaRutinaAndDiaSemanaAndActivoTrue(idPlantillaRutina, diaSemana).orElseGet(() -> {
						DiaRutina nuevoDia = new DiaRutina();
						nuevoDia.setIdPlantillaRutina(idPlantillaRutina);
						nuevoDia.setDiaSemana(diaSemana);
						nuevoDia.setActivo(true);
						return diaRutinaRepository.save(nuevoDia);
					});

			for (ActividadPlantillaRequest actividadRequest : request.getActividades()) {
				if (actividadRequest.getIdActividad() == null || actividadRequest.getSeries() == null
						|| actividadRequest.getOrden() == null) {
					response.setCodigo(400);
					response.setData(null);
					response.setMensaje("Cada actividad debe incluir idActividad, series y orden");
					return response;
				}

				Optional<Actividad> actividadDb = actividadRepository.findById(actividadRequest.getIdActividad());
				if (actividadDb.isEmpty() || Boolean.FALSE.equals(actividadDb.get().getActivo())) {
					response.setCodigo(404);
					response.setData(null);
					response.setMensaje("Actividad no encontrada: " + actividadRequest.getIdActividad());
					return response;
				}

				ActividadRutina actividadRutina = new ActividadRutina();
				actividadRutina.setIdDiaRutina(diaRutina.getIdDiaRutina());
				actividadRutina.setIdActividad(actividadRequest.getIdActividad());
				actividadRutina.setSeries(actividadRequest.getSeries());
				actividadRutina.setRepeticiones(actividadRequest.getRepeticiones());
				actividadRutina.setDuracionMinutos(actividadRequest.getDuracionMinutos());
				actividadRutina.setDescanso(actividadRequest.getDescanso());
				actividadRutina.setOrden(actividadRequest.getOrden());
				actividadRutina.setActivo(true);
				actividadRutinaRepository.save(actividadRutina);
			}

			response.setCodigo(201);
			response.setData(actividadRutinaRepository.findByPlantilla(idPlantillaRutina));
			response.setMensaje(Constantes.mensajeInsertadoOk);
		} catch (DataAccessException e) {
			String codigoError = UUID.randomUUID().toString();
			log.error("Error BD al asignar actividades [{}]", codigoError, e);
			response.setCodigo(500);
			response.setData(null);
			response.setCodigoError(codigoError);
			response.setMensaje("Error de base de datos");
		} catch (Exception e) {
			String codigoError = UUID.randomUUID().toString();
			log.error("Error interno al asignar actividades [{}]", codigoError, e);
			response.setCodigo(500);
			response.setData(null);
			response.setCodigoError(codigoError);
			response.setMensaje("Error interno del sistema");
		}
		return response;
	}

	@Override
	public ResponseGenerico obtenerActividades(String idPlantilla) {
		ResponseGenerico response = new ResponseGenerico();
		try {
			Long idPlantillaRutina = (long) Integer.parseInt(idPlantilla);
			response.setCodigo(200);
			response.setData(actividadRutinaRepository.findByPlantilla(idPlantillaRutina));
			response.setMensaje(Constantes.mensageOk);
		} catch (DataAccessException e) {
			String codigoError = UUID.randomUUID().toString();
			log.error("Error BD al consultar actividades de plantilla [{}]", codigoError, e);
			response.setCodigo(500);
			response.setData(null);
			response.setCodigoError(codigoError);
			response.setMensaje("Error de base de datos");
		} catch (Exception e) {
			String codigoError = UUID.randomUUID().toString();
			log.error("Error interno al consultar actividades de plantilla [{}]", codigoError, e);
			response.setCodigo(500);
			response.setData(null);
			response.setCodigoError(codigoError);
			response.setMensaje("Error interno del sistema");
		}
		return response;
	}
}
