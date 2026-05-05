package mx.com.gmr.pulsogym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.gmr.pulsogym.model.PlantillaRutina;

@Repository
public interface IPlantillaRutinaRepository extends JpaRepository<PlantillaRutina, Long> {

	@Query("SELECT p FROM PlantillaRutina p WHERE p.idPerfilCoach = ?1 AND p.activo = true")
	List<PlantillaRutina> findByIdPerfilCoach(int idPerfilCoach);

	@Modifying
	@Transactional
	@Query("UPDATE PlantillaRutina SET activo = false WHERE idPlantillaRutina = ?1")
	Integer eliminarPlantilla(int idPlantillaRutina);
}
