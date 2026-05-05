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
@Table(name = "dia_rutina")
public class DiaRutina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_dia_rutina")
	Long idDiaRutina;

	@Column(name = "id_plantilla_rutina")
	Long idPlantillaRutina;

	@Column(name = "dia_semana")
	String diaSemana;

	@CreationTimestamp
	@Column(name = "fecha_creacion", updatable = false)
	LocalDateTime fechaCreacion;

	Boolean activo;
}
