package mx.com.gmr.pulsogym.mapper;

import mx.com.gmr.pulsogym.model.Usuario;
import mx.com.gmr.pulsogym.model.response.UsuarioResponse;

public interface IUsuarioMapper  {
	 public UsuarioResponse obtenerUsuario(Usuario usuario);
}
