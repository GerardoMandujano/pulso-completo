package mx.com.gmr.pulsogym.service;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import mx.com.gmr.pulsogym.model.Usuario;
import mx.com.gmr.pulsogym.model.response.ResponseGenerico;


@Repository
public interface IUsuarioService {
	ResponseGenerico obtenerTodosUsuarios();
	ResponseGenerico registrarUsuario(Usuario usuario);
	ResponseGenerico obtenerUsuario(String correo,String contrasenia);
	 ResponseGenerico desactivarUsuario(String correo);
	 ResponseGenerico actualizarUsuario(Usuario usuario);
	
	
	
	

}
