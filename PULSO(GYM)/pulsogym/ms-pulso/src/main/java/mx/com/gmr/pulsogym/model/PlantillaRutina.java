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
@Table(name = "plantilla_rutina")
public class PlantillaRutina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_plantilla_rutina")
	Long idPlantillaRutina;

	@Column(name = "id_perfil_coauch")
	Integer idPerfilCoach;

	String nombre;

	@CreationTimestamp
	@Column(name = "fecha_creacion", updatable = false)
	LocalDateTime fechaCreacion;

	Boolean activo;
}
