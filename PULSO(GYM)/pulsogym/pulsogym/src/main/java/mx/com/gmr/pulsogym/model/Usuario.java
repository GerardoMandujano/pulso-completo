package mx.com.gmr.pulsogym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import mx.com.gmr.pulsogym.utils.RolEnum;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idUsuario;
	String correo;
	String contrasenia;
	String rol;
	@JsonIgnore
	String fechaCreacion;
	Boolean activo;
	
	
	
	

}
