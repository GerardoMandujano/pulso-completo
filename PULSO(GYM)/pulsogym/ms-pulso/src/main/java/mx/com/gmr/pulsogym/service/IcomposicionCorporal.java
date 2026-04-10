package mx.com.gmr.pulsogym.service;


import mx.com.gmr.pulsogym.model.ComposicionCorporal;
import mx.com.gmr.pulsogym.model.response.ResponseGenerico;

public interface IcomposicionCorporal {
	
	ResponseGenerico registrar(ComposicionCorporal composicion);
	

}
