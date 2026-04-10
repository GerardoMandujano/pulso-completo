package mx.com.gmr.pulsogym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.com.gmr.pulsogym.model.PerfilAlumno;

@Repository
public interface PerfilAlumnoRepository extends JpaRepository<PerfilAlumno, Long> {
	
	@Query("SELECT u FROM PerfilAlumno u WHERE activo=true")
	List<PerfilAlumno> findAllAndActivoTrue();

}
