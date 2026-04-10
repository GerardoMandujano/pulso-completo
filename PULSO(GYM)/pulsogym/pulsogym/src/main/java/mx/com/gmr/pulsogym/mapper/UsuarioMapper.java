package mx.com.gmr.pulsogym.mapper;

import org.springframework.stereotype.Component;

import mx.com.gmr.pulsogym.model.Usuario;
import mx.com.gmr.pulsogym.model.response.UsuarioResponse;
@Component
public class UsuarioMapper implements IUsuarioMapper {
	 
	 public UsuarioResponse obtenerUsuario(Usuario usuario) {
	        UsuarioResponse usuarioDto = new UsuarioResponse();
	        usuarioDto.setId(usuario.getIdUsuario());
	        usuarioDto.setCorreo(usuario.getCorreo());
	        usuarioDto.setRol(usuario.getRol());
	        usuarioDto.setContrasenia(usuario.getContrasenia());
	        return usuarioDto;
	    }
	 

}
