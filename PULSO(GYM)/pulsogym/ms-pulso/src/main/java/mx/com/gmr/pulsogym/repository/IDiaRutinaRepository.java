package mx.com.gmr.pulsogym.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.gmr.pulsogym.model.DiaRutina;

@Repository
public interface IDiaRutinaRepository extends JpaRepository<DiaRutina, Long> {
	Optional<DiaRutina> findByIdPlantillaRutinaAndDiaSemanaAndActivoTrue(Long idPlantillaRutina, String diaSemana);
}
