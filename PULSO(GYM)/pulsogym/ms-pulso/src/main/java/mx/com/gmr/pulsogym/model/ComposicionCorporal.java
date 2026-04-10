package mx.com.gmr.pulsogym.model;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "composicion_corporal")
public class ComposicionCorporal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idComposicionCorporal;
	Integer idPerfilAlumno;
	int estatura;
	Double masaMuscular;
	Double peso;
	Double bmi;
	Double grasa;
	Double masaOsea;
	Double grasaVicersal;
	Double agua;
	@CreationTimestamp
	LocalDate fechaCreacion;
}

