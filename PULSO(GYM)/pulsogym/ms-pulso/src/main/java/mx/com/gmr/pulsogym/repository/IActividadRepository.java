package mx.com.gmr.pulsogym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.gmr.pulsogym.model.Actividad;

@Repository
public interface IActividadRepository extends JpaRepository<Actividad, Long> {
	
	@Query("SELECT a FROM Actividad a WHERE a.idPerfilCoach = ?1AND activo= true")
	List<Actividad> findByIdPerfilCoach(int id);
	
    @Modifying
	@Transactional
	@Query("UPDATE Actividad  SET activo = false where idActividad = ?1")
	Integer eliminarActividad(int id);
    
	@Query("SELECT a FROM Actividad a WHERE a.tipo = :tipo")
	List<Actividad> findByTipo(String tipo);

    
}
