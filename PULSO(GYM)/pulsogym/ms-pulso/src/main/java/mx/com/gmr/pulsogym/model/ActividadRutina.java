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
@Table(name = "actividad_rutina")
public class ActividadRutina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_actividad_rutina")
	Long idActividadRutina;

	@Column(name = "id_dia_rutina")
	Long idDiaRutina;

	@Column(name = "id_actividad")
	Long idActividad;

	Integer series;
	Integer repeticiones;

	@Column(name = "duracion_minutos")
	Integer duracionMinutos;

	Integer descanso;
	Integer orden;

	@CreationTimestamp
	@Column(name = "fecha_creacion", updatable = false)
	LocalDateTime fechaCreacion;

	Boolean activo;
}
