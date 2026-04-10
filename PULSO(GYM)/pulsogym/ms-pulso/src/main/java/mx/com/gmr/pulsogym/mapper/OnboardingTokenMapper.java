package mx.com.gmr.pulsogym.mapper;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class OnboardingTokenMapper {
	
	
	 Long idOnboardinToken;
	 Integer idUsuario;
	 String token;
	 Boolean usado;
	 String fechaCreacion;
	 Integer tiempoEpiracion;

}
