package mx.com.gmr.pulsogym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.com.gmr.pulsogym.model.ActividadRutina;

@Repository
public interface IActividadRutinaRepository extends JpaRepository<ActividadRutina, Long> {

	@Query("SELECT ar FROM ActividadRutina ar "
			+ "WHERE ar.activo = true AND ar.idDiaRutina IN ("
			+ "SELECT d.idDiaRutina FROM DiaRutina d WHERE d.activo = true AND d.idPlantillaRutina = ?1"
			+ ") ORDER BY ar.idDiaRutina, ar.orden")
	List<ActividadRutina> findByPlantilla(Long idPlantillaRutina);
}
