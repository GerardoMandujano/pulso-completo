package mx.com.gmr.pulsogym.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "onboarding_token")
public class OnboardinToken {

	 @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idOnboardinToken;
	 Integer idUsuario;
	 String token;
	 Boolean usado;
	 LocalDate fechaCreacion;
	 LocalDateTime fechaExpiracion;
	 Integer tiempoExpiracion;
	
}
