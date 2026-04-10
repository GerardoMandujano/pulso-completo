package mx.com.gmr.pulsogym.service;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmr.libs.CorreoService;

import feign.FeignException;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import mx.com.gmr.pulsogym.mapper.IPerfilAlumnoMapper;
import mx.com.gmr.pulsogym.model.OnboardinToken;
import mx.com.gmr.pulsogym.model.PerfilAlumno;
import mx.com.gmr.pulsogym.model.request.UsuarioRequest;
import mx.com.gmr.pulsogym.model.response.PerfilAlumnoResponse;
import mx.com.gmr.pulsogym.model.response.ResponseGenerico;
import mx.com.gmr.pulsogym.model.response.UsuarioResponse;
import mx.com.gmr.pulsogym.repository.OnboardingTokenRepository;
import mx.com.gmr.pulsogym.repository.PerfilAlumnoRepository;
import mx.com.gmr.pulsogym.repository.UsuarioFeingRepository;
import mx.com.gmr.pulsogym.utils.Constantes;
import mx.com.gmr.pulsogym.utils.RolEnum;

@Slf4j
@Service
public class PerfilAlumnoService implements IPerfilAlumno {
	
	@Autowired
	PerfilAlumnoRepository alumnoRepository;
	
	@Autowired
	IPerfilAlumnoMapper alumnoMapper;
	
	@Autowired
	UsuarioFeingRepository usaurioFeing;
	
	private ResponseGenerico  response ;
	
	@Autowired
	OnboardingTokenRepository onboardinTokenRepository;
	
	@Value("${mail.key}")
	private String key;
	
	@Value("${mail.origen}")
	private String correOrigen;
	
	
	
	@Override
	public ResponseGenerico obtenerAlumno(String id) {
		ResponseGenerico  response = new ResponseGenerico();
		
		try {
			Long idAlumno = (long) Integer.parseInt(id);
			Optional<PerfilAlumno> alumnoDb = alumnoRepository.findById(idAlumno);
			PerfilAlumno alumno = alumnoDb.get();
			response.setData(alumnoMapper.obtenerAlumno(alumno));
			response.setMensaje(Constantes.mensageOk);
			response.setCodigo(Constantes.codigoOk);
		} catch (Exception e) {
			response.setData(null);
			response.setMensaje(Constantes.mensageNoOk);
			response.setCodigo(Constantes.codigoError);
		}
		
		// TODO Auto-generated method stub
		return response;
	}

	@Override
	public ResponseGenerico obtenerTodosAlumnos(int page,int size) {
		ResponseGenerico  response = new ResponseGenerico();
		try {
			Page <PerfilAlumno> pageAlumnos =  alumnoRepository.findAll(PageRequest.of(page, size));
			List<PerfilAlumno>listAlumnos=pageAlumnos.getContent();
			List<PerfilAlumnoResponse>listResponse = new LinkedList<>();
			
			PerfilAlumnoResponse alumnoResponse = new PerfilAlumnoResponse();
			
		
			
			
			for (PerfilAlumno alumno:listAlumnos) {
				
				alumnoResponse = alumnoMapper.obtenerAlumno(alumno);
				listResponse.add(alumnoResponse);
			}
		
			response.setCodigo(200);
			response.setData(listResponse);
			response.setMensaje(Constantes.mensageOk);
			
			return response;
		

		} catch (Exception e) {
			response.setCodigo(500);
			response.setData(null);
			response.setMensaje(Constantes.mensageNoOk);
		}
		
		
		
		return null;
	}

	@Override
	public ResponseGenerico registrarAlumno(PerfilAlumno alumno) {
	
		response= new ResponseGenerico();
		try {
			
			log.info("Inicia registrar alumno {}" ,alumno);
			
			if( null==alumno.getNombre()|| null==alumno.getCorreo()) {
				log.info("valida alumno {}" ,alumno);
				response.setCodigo(400);
				response.setData(null);
				response.setMensaje(Constantes.mensajeNoInsertado);
				return response;
			}
			
			UsuarioRequest usuarioReq = new UsuarioRequest();
			usuarioReq.setCorreo(alumno.getCorreo());
			usuarioReq.setRol(RolEnum.ALUMNO.toString());
			usuarioReq.setActivo(true);
			log.info("Enviando usuario {}" ,usuarioReq);
			
			ResponseEntity<ResponseGenerico> responseUsuario = usaurioFeing.registrarUsuario(usuarioReq);
			log.info("recibiendo usaurio {}" ,responseUsuario);
			
			if(responseUsuario.getBody()==null||responseUsuario.getBody().getCodigo()!=200) {
				throw new RuntimeException("ERROR AL CREAR USAURIO");
			}if(responseUsuario.getBody().getCodigo()== 409) {
				throw new RuntimeException("USUARIO EXISTENTE");
			}
			
			Object  data =  responseUsuario.getBody().getData();
			ObjectMapper mapper = new ObjectMapper();
			UsuarioResponse usuario = mapper.convertValue(data, UsuarioResponse.class);
			
			alumno.setIdUsuario(usuario.getId());
			alumno.setFechaCreacion(LocalDateTime.now().toLocalDate());	
			OnboardinToken token = new OnboardinToken();
			token.setIdUsuario(usuario.getId());
			token.setFechaCreacion(LocalDateTime.now().toLocalDate());
			token.setUsado(false);
			token.setTiempoExpiracion(5);
			token.setFechaExpiracion(LocalDateTime.now().plusMinutes(token.getTiempoExpiracion()));
			token.setToken(UUID.randomUUID().toString());
			onboardinTokenRepository.save(token);
			
			
			 // Variables para reemplazar en la plantilla
            Map<String, Object> contextVariables = new HashMap<>();
            contextVariables.put("nombre", alumno.getNombre()+ " "+ alumno.getApellidop()+" "+alumno.getApellidom());
            contextVariables.put("urlFront", "http://localhost:8085/alumno/cambio-contrasenia/"+token.getToken());
         
            // Enviar el correo
            CorreoService.sendEmail("smtp.gmail.com", "587", correOrigen, key, "gerardo.mandujano.itt@gmail.com",
                    "Creacion de cuenta", "correoCambioContraseña", contextVariables, null);
            
            alumno.setActivo(true);
			PerfilAlumno alumnoDb = alumnoRepository.save(alumno);

			PerfilAlumnoResponse alumnoResponse = alumnoMapper.obtenerAlumno(alumnoDb);

			
			log.info("Finaliza registrar alumno");
			
			
			response.setCodigo(200);
			response.setData(alumnoResponse);
			response.setMensaje(Constantes.mensajeInsertadoOk);
			
		} catch (FeignException e) {
			
			if(e.status()==400) {
				response.setCodigo(400);
				response.setMensaje("Datos invalidos");
			}
			if(e.status()==500) {
				response.setCodigo(500);
			}
			if(e.status()==409) {
				response.setCodigo(409);
			}
				
			response.setData(null);
			response.setMensaje(Constantes.mensajeNoInsertado);
		}
		
		catch (Exception e) {
			
			response.setCodigo(500);
			response.setData(null);
			response.setMensaje(Constantes.mensajeNoInsertado);
		}
		return response;
	}

	@Override
	public ResponseGenerico actualizarAlumno(PerfilAlumno alumno) {
		ResponseGenerico data = new ResponseGenerico();

	    try {
	        PerfilAlumno alumnoService = alumnoRepository.findById(alumno.getIdPerfilAlumno())
	                .orElseThrow(() -> new RuntimeException("Alumno no existe"));

	        alumnoRepository.save(alumno);
	        PerfilAlumnoResponse alumnoResponse = alumnoMapper.obtenerAlumno(alumnoService);
	        data.setData(alumnoResponse);
	        data.setCodigo(Constantes.codigoOk);
	        data.setMensaje(Constantes.mensajeInsertadoOk);

	    } catch (Exception e) {
	        String codigoError = UUID.randomUUID().toString();
	        log.error("ERROR: " + codigoError + " " + e.getMessage());

	        data.setData(null);
	        data.setCodigo(500);
	        data.setCodigoError(codigoError);
	        data.setMensaje(e.getMessage());
	    }

		return data;
	}
	
	public ResponseEntity<?> validarToken(String token){
		
		OnboardinToken tokenDb = onboardinTokenRepository.findByToken(token);
		
		if(tokenDb==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Token invalido");
		}
		if(tokenDb.getUsado()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token ya utilizado");
		}
		
		if(tokenDb.getFechaExpiracion().isBefore(LocalDateTime.now())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token expirado");
		}
		
		tokenDb.setUsado(true);
		onboardinTokenRepository.save(tokenDb);
		
		String urlFront= "http://localhost:8100/reset-password?token="+token;
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create(urlFront));
		
		
		return new ResponseEntity<>(headers,HttpStatus.FOUND);
	}

}
