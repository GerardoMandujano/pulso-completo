package mx.com.gmr.pulsogym.exeption;

public class UsuarioExistenteException extends RuntimeException {
	
	public UsuarioExistenteException(String mensaje) {
		super(mensaje);
	}

}
