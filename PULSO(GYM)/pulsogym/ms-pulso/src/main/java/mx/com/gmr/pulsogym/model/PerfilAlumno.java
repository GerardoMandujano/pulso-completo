package mx.com.gmr.pulsogym.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="perfil_alumno")
public class PerfilAlumno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long IdPerfilAlumno;
	Integer idUsuario;
	Integer idPerfilCoach;
	String nombre;
	
	String apellidop;
	
	String apellidom;
	String fechaNacimiento;
	char sexo;
	String telefono;
	String correo;
	String frecuenciaEntrenamiento;
	String nivel;
	@CreationTimestamp
	LocalDate fechaCreacion;
	
	@UpdateTimestamp
	LocalDate fechaActualizacion;
	String statusOnboarding;
	String objetivo;
	Boolean activo;
	
}
