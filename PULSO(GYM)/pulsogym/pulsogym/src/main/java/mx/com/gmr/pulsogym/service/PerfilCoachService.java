package mx.com.gmr.pulsogym.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.gmr.pulsogym.mapper.PerfilCoachMapper;
import mx.com.gmr.pulsogym.mapper.UsuarioMapper;
import mx.com.gmr.pulsogym.model.PerfilCoach;
import mx.com.gmr.pulsogym.model.Usuario;
import mx.com.gmr.pulsogym.model.response.PerfilCoachResponse;
import mx.com.gmr.pulsogym.model.response.ResponseGenerico;
import mx.com.gmr.pulsogym.model.response.UsuarioResponse;
import mx.com.gmr.pulsogym.repository.PerfilCoachRepository;
import mx.com.gmr.pulsogym.repository.UsuarioRepository;
import mx.com.gmr.pulsogym.utils.Constantes;

@Service
public class PerfilCoachService implements IPerfilCoach{

	@Autowired
	PerfilCoachRepository coachRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	@Autowired
	PerfilCoachMapper coachMapper;
	
	@Autowired
	UsuarioMapper usaurioMapper;
	
	@Override
	public ResponseGenerico obtenerCoach(String id) {
		ResponseGenerico data = new ResponseGenerico();
		Long idd=(long) Integer.parseInt(id);
		Optional<PerfilCoach> perfilcoach= coachRepository.findById(idd);
		
		data.setData(perfilcoach);
		data.setCodigo(200);
		data.setMensaje(Constantes.mensageOk);
		
		return data;
	}

}
