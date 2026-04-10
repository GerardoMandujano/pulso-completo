package mx.com.gmr.pulsogym.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="actividad")
public class Actividad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idActividad;
	@Column(name = "id_perfil_coach")
	Integer idPerfilCoach;
	String nombre;
	String tipo;
	@Column(name = "grupo_muscular")
	String grupoMuscular;
	String descripcion;
	@CreationTimestamp
	@Column(name = "fecha_creacion", updatable = false)
	private LocalDateTime fechaCreacion;
	Boolean activo;

}
