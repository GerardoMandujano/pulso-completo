package mx.com.gmr.pulsogym.model.response;

import lombok.Data;

@Data
public class ResponseGenerico {
	
	Object data;
	String mensaje;
	Integer codigo;
	String codigoError;

}
