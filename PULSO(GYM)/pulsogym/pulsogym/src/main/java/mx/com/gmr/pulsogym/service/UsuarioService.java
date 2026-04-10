package mx.com.gmr.pulsogym.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.com.gmr.pulsogym.exeption.UsuarioExistenteException;
import mx.com.gmr.pulsogym.mapper.UsuarioMapper;
import mx.com.gmr.pulsogym.model.Usuario;
import mx.com.gmr.pulsogym.model.response.ResponseGenerico;
import mx.com.gmr.pulsogym.model.response.UsuarioResponse;
import mx.com.gmr.pulsogym.repository.UsuarioRepository;
import mx.com.gmr.pulsogym.utils.Constantes;

@Service
@Slf4j
public class UsuarioService implements IUsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	UsuarioMapper usuarioMapper;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	public ResponseGenerico obtenerUsuario(String correo, String contrasenia) {
		ResponseGenerico data = new ResponseGenerico();
		try {
			
			log.info("Inicia obtener usuario login");
			Optional<Usuario> usuariobD= usuarioRepository.findByCorreoAndActivoTrue(correo);
			Usuario usuario= usuariobD.get();
			UsuarioResponse usuarioResponse = usuarioMapper.obtenerUsuario(usuario);
			
			
			
			
			
			if(encoder.matches(contrasenia, usuario.getContrasenia())) {
				data.setData(usuarioResponse);
				data.setCodigo(Constantes.codigoOk);
				data.setMensaje(Constantes.mensageOk);
				log.info("finaliza obtener usuario");
			}else {
				
				data.setData(null);
				data.setCodigo(Constantes.codigoErrorIngreso);
				data.setMensaje(Constantes.mensajeNoAcceso);
				
			}
			
			
			return data;
		} catch (Exception e) {
			String codigoError = UUID.randomUUID().toString();
			data.setData(null);
			data.setCodigo(Constantes.codigoError);
			data.setMensaje(Constantes.mensageNoOk);
		      data.setCodigoError(codigoError);		}
		return data;
	}

	@Override
	public ResponseGenerico registrarUsuario(Usuario usuario) {
		ResponseGenerico data = new ResponseGenerico();
			

			log.info("Inicia registrar usuario");

			if(usuario.getContrasenia()!=null) {
				String hash = encoder.encode(usuario.getContrasenia());
				usuario.setContrasenia(hash);
			}
	  Optional<Usuario>	 usaurioExistente=	usuarioRepository.findByCorreoAndActivoTrue(usuario.getCorreo());
			if(!usaurioExistente.isEmpty()) {
				log.info("USAURIO EXISTENTE");
				
				throw new UsuarioExistenteException(Constantes.mensajeUsuarioExiste);

			}else {
				Usuario usuarioDb = usuarioRepository.save(usuario);
				UsuarioResponse usuarioResponse = usuarioMapper.obtenerUsuario(usuarioDb);
				data.setData(usuarioResponse);
				data.setCodigo(Constantes.codigoOk);
				data.setMensaje(Constantes.mensajeInsertadoOk);
				log.info("Finaliza registrar usuario");
			}
			
			log.info("REGRESANDO DATA"+data);
			return data;	


			
		 
		
	}

	@Override
	public ResponseGenerico desactivarUsuario(String correo) {
		ResponseGenerico data = new ResponseGenerico();
//		try {
//			Usuario usuario = usuarioRepository.findByCorreoAndContrasenia(correo)
//					.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
//
//			usuario.setActivo(false);
//
//			usuarioRepository.save(usuario);
//			data.setData(true);
//			data.setCodigo(Constantes.codigoOk);
//			data.setMensaje(Constantes.mensajeEliminadoOk);
//
//		} catch (Exception e) {
//			String codigoError = UUID.randomUUID().toString();
//			log.error("ERROR: " + codigoError + " " + e.getMessage());
//			data.setData(null);
//			data.setCodigo(500);
//			data.setCogidoError(codigoError);
//			data.setMensaje(Constantes.mensajeNoDesactivado);
//		}
//		
		return data;

	}

	@Override
	public ResponseGenerico actualizarUsuario(Usuario usuario) {
		ResponseGenerico data = new ResponseGenerico();

	    try {
	    	
	    	

	        Usuario usuariodb = usuarioRepository.findById(usuario.getIdUsuario())
	                .orElseThrow(() -> new RuntimeException("Usuario no existe"));

	        // validar correo único
	        Optional<Usuario> existente = usuarioRepository.findByCorreo(usuario.getCorreo());

	        if (existente.isPresent() &&
	            !existente.get().getIdUsuario().equals(usuario.getIdUsuario())) {
	            throw new RuntimeException("El correo ya está en uso");
	        }

	   
	     // actualizar solo si viene valor
	        if (usuario.getCorreo() != null) {
	            usuariodb.setCorreo(usuario.getCorreo());
	        }

	        if (usuario.getContrasenia() != null) {
	            usuariodb.setContrasenia(usuario.getContrasenia());
	        }

	        if (usuario.getRol() != null) {
	            usuariodb.setRol(usuario.getRol());
	        }

	        if (usuario.getActivo() != null) {
	            usuariodb.setActivo(usuario.getActivo());
	        }

	        usuarioRepository.save(usuariodb);

	        UsuarioResponse usuarioResponse = usuarioMapper.obtenerUsuario(usuariodb);

	        data.setData(usuarioResponse);
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

	@Override
	public ResponseGenerico obtenerTodosUsuarios() {
		ResponseGenerico data = new ResponseGenerico();

		List<Usuario> lista=usuarioRepository.findAllAndActivoTrue();
		
		List<UsuarioResponse> listResponse =  new LinkedList<>();
		UsuarioResponse usuarioResponse = new UsuarioResponse();
		for (Usuario usuario:lista) {
			
			
			 usuarioResponse = usuarioMapper.obtenerUsuario(usuario);
			 usuarioResponse.setRol(usuario.getRol());
			 listResponse.add(usuarioResponse);
		}
	
		data.setCodigo(200);
		data.setData(listResponse);
		data.setMensaje(Constantes.mensageOk);
		
	
		return data;
	}

}
