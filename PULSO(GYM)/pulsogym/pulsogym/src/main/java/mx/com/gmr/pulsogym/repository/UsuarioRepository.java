package mx.com.gmr.pulsogym.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import mx.com.gmr.pulsogym.model.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByCorreoAndActivoTrue(String correo);
	
	
	public  Optional<Usuario> findByCorreo(String correo);
	
	@Query("SELECT u FROM Usuario u WHERE activo=true")
	public List<Usuario> findAllAndActivoTrue();
	
	


}
